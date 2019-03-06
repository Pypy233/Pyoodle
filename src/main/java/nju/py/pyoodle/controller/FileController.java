package nju.py.pyoodle.controller;

import nju.py.pyoodle.service.CourseBaseService;
import nju.py.pyoodle.util.FileUtil;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: py
 * @Date: 2019/2/11 下午4:04
 * @Version 1.0
 */
@RestController
public class FileController {
    private final static String DOWNLOAD_PATH = "/Users/py/Downloads/";


    private final CourseBaseService courseBaseService;

    private final Logger logger = LoggerFactory.getLogger(FileController.class);

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "/Users/py/J2EEStrorage/";

    @Autowired
    public FileController(CourseBaseService courseBaseService) {
        this.courseBaseService = courseBaseService;
    }


    // Multiple file upload
    @PostMapping("/api/upload/multiPPT")
    public ResponseEntity<?> uploadFileMulti(@RequestParam("courseName") String courseName,
                                             @RequestParam("files") MultipartFile[] uploadfiles) {
        //courseBaseService.saveCourseBase(courseName, user);
        System.out.println("ppt   " + courseName);
        String prefix = courseName + "/ppt";
        logger.debug("Multiple file upload!");

        String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename())
                .filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));

        if ( StringUtils.isEmpty(uploadedFileName) ) {
            return new ResponseEntity("please select a file!", HttpStatus.OK);
        }

        try {

            saveUploadedFiles(prefix, Arrays.asList(uploadfiles));

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Successfully uploaded - "
                + uploadedFileName, HttpStatus.OK);

    }

    @PostMapping("/api/upload/multiHW")
    public ResponseEntity<?> uploadFileMultiHW(@RequestParam("courseName") String courseName,
                                               @RequestParam("files") MultipartFile[] uploadfiles) {

        String prefix = courseName + "/hw";
        logger.debug("Multiple file upload!");

        String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename())
                .filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));

        if ( StringUtils.isEmpty(uploadedFileName) ) {
            return new ResponseEntity("please select a file!", HttpStatus.OK);
        }

        try {

            saveUploadedFiles(prefix, Arrays.asList(uploadfiles));

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Successfully uploaded - "
                + uploadedFileName, HttpStatus.OK);

    }

    @PostMapping("/api/upload/pic")
    public ResponseEntity<?> uploadPicture(@RequestParam("courseName") String courseName,
                                           @RequestParam("files") MultipartFile[] uploadfiles) {

        System.out.println("pic" + courseName);
        System.out.println(uploadfiles[0].getName());
        String prefix = courseName + "/pic";
        logger.debug("Multiple file upload!");

        String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename())
                .filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));

        if ( StringUtils.isEmpty(uploadedFileName) ) {
            return new ResponseEntity("please select a file!", HttpStatus.OK);
        }

        try {

            saveUploadedFiles(prefix, Arrays.asList(uploadfiles));

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Successfully uploaded - "
                + uploadedFileName, HttpStatus.OK);

    }


    //save file
    private void saveUploadedFiles(String prefix, List<MultipartFile> files) throws IOException {
        FileUtil.createFolder(prefix);

        for (MultipartFile file : files) {

            if ( file.isEmpty() ) {
                continue; //next pls
            }

            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + prefix + "/" + file.getOriginalFilename());
            Files.write(path, bytes);

        }

    }

    @RequestMapping(path = "/download", method = RequestMethod.GET)
    public ResponseEntity<Resource> download(HttpServletRequest request, HttpServletResponse response, String fileName) throws IOException {

        File file = new File(DOWNLOAD_PATH + fileName);
        if ( file.exists() ) {

            //get the mimetype
            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
            if ( mimeType == null ) {
                //unknown mimetype so set the mimetype to application/octet-stream
                mimeType = "application/octet-stream";
            }

            response.setContentType(mimeType);

            response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));

            response.setContentLength((int) file.length());

            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

            FileCopyUtils.copy(inputStream, response.getOutputStream());
        }
        return new ResponseEntity<>(null);

    }

    @RequestMapping(value = "/files/{file_name}", method = RequestMethod.GET)
    public void getFile(
            @PathVariable("file_name") String fileName, String courseName, String type,
            HttpServletResponse response) {
        try {
            String path = UPLOADED_FOLDER + courseName + "/" + type + "/" + fileName;
            System.out.println(path);
            InputStream is = new FileInputStream(path);
            // copy it to response's OutputStream
            IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException ex) {
            logger.info("Error writing file to output stream. Filename was '{}'", fileName, ex);
            throw new RuntimeException("IOError writing file to output stream");
        }

    }
}
