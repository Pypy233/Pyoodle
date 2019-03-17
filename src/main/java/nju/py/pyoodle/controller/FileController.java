package nju.py.pyoodle.controller;

import nju.py.pyoodle.dao.CourseDAO;
import nju.py.pyoodle.dao.UserDAO;
import nju.py.pyoodle.domain.Course;
import nju.py.pyoodle.domain.User;
import nju.py.pyoodle.service.CourseBaseService;
import nju.py.pyoodle.util.FileUtil;
import java.io.*;

import nju.py.pyoodle.util.ZipUtil;
import org.apache.commons.io.FileUtils;
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
    private final static String HW_PATH = "/Users/py/Downloads/hw_site/";

    private final CourseDAO courseDAO;
    private final UserDAO userDAO;
    private final CourseBaseService courseBaseService;

    private final Logger logger = LoggerFactory.getLogger(FileController.class);

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "/Users/py/J2EEStrorage/";

    @Autowired
    public FileController(CourseBaseService courseBaseService, CourseDAO courseDAO, UserDAO userDAO) {
        this.courseBaseService = courseBaseService;
        this.courseDAO = courseDAO;
        this.userDAO = userDAO;
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
        System.out.println("---------Uploading HW");
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

    @PostMapping("/api/upload/hw")
    public ResponseEntity<?> uploadStudentHws(@RequestParam("courseName") String courseName, @RequestParam("hwName") String hwName,
                                              @RequestParam("studentNum") String studentNum,
                                              @RequestParam("files") MultipartFile[] uploadfiles) {
    try {

            String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename())
                    .filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));

            if ( StringUtils.isEmpty(uploadedFileName) ) {
                return new ResponseEntity("please select a file!", HttpStatus.OK);
            }

                saveHw(courseName, hwName, studentNum, Arrays.asList(uploadfiles));
                return new ResponseEntity("Successfully uploaded - "
                        + uploadedFileName, HttpStatus.OK);

        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }



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

    private void saveHw(String courseName, String hwName, String studentNum, List<MultipartFile> files) {
        try {

            String filePath = UPLOADED_FOLDER + "hwStorage/" + courseName + "/" + hwName + "/" + studentNum + "/";
            FileUtils.forceDelete(new File(filePath));
            FileUtil.createFolderWithoutPrefix(filePath);

            for (MultipartFile file : files) {

                if ( file.isEmpty() ) {
                    continue; //next pls
                }

                byte[] bytes = file.getBytes();
                Path path = Paths.get(filePath + file.getOriginalFilename());
                Files.write(path, bytes);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

//    @RequestMapping(path = "/download", method = RequestMethod.GET)
//    public ResponseEntity<Resource> download(HttpServletRequest request, HttpServletResponse response, String courseName, String hwName) throws IOException {
//
//        String prefix = "/Users/py/J2EEStrorage/hwStorage/" + courseName + "/" + hwName;
//
//        if ( file.exists() ) {
//
//            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
//            if ( mimeType == null ) {
//                mimeType = "application/octet-stream";
//            }
//
//            response.setContentType(mimeType);
//
//            response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));
//
//            response.setContentLength((int) file.length());
//
//            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
//
//            FileCopyUtils.copy(inputStream, response.getOutputStream());
//        }
//        return new ResponseEntity<>(null);
//
//    }

    @RequestMapping(value = "/files/{courseName}/{hwName}", method = RequestMethod.GET)
    public void getFile(
            @PathVariable("courseName") String courseName, @PathVariable("hwName") String hwName,
            HttpServletResponse response) {
        try {
            String prefixPath = "/Users/py/J2EEStrorage/hwStorage/" + courseName + "/" + hwName;
            System.out.println(prefixPath);
            ZipUtil.zipfile(courseName, hwName);
            InputStream is = new FileInputStream("/Users/py/1.zip");
            IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException ex) {
            //logger.info("Error writing file to output stream. Filename was '{}'", ex);
            ex.printStackTrace();
            throw new RuntimeException("IOError writing file to output stream");
        }

    }

    @GetMapping("/downloadAll")
    @ResponseBody
    public void downloadAllHws(String courseName, String hwName, HttpServletResponse response) {
        String prefixPath = "/Users/py/Downloads/j2ee作业/" + courseName + "/" + hwName + "/";
        FileUtil.createHwFolder(prefixPath);

        Course course = courseDAO.getCourseByName(courseName);
        List<User> studentList = course.getStudents();
        for(User user: studentList) {
            String studentNumber = user.getStudentNumber();
            String eachFolder = prefixPath + studentNumber + "/";
            FileUtil.createHwFolder(eachFolder);
            // TODO

        }
    }

    public static void main(String[] args) {
        FileUtil.createHwFolder("/Users/py/J2EEStrorage/hwStorage/2019-02-27 数据结构/链表/161250096/");
    }

}
