package nju.py.pyoodle.controller;

import nju.py.pyoodle.service.CourseBaseService;
import nju.py.pyoodle.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
public class FileUploader {
    @Autowired
    private CourseBaseService courseBaseService;

    private final Logger logger = LoggerFactory.getLogger(FileUploader.class);

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "/Users/py/J2EEStrorage/";


    // Multiple file upload
    @PostMapping("/api/upload/multiPPT")
    public ResponseEntity<?> uploadFileMulti(String courseName, String user,
            @RequestParam("files") MultipartFile[] uploadfiles) {
        courseBaseService.saveCourseBase(courseName, user);
        System.out.println("-------------" + courseName);
        String prefix = courseName + "/ppt";
        logger.debug("Multiple file upload!");

        String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename())
                .filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));

        if (StringUtils.isEmpty(uploadedFileName)) {
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
    public ResponseEntity<?> uploadFileMultiHW(String courseName,
                                             @RequestParam("files") MultipartFile[] uploadfiles) {

        String prefix = courseName + "/hw";
        logger.debug("Multiple file upload!");

        String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename())
                .filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));

        if (StringUtils.isEmpty(uploadedFileName)) {
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
    public ResponseEntity<?> uploadPicture(String courseName,
                                               @RequestParam("files") MultipartFile[] uploadfiles) {

        String prefix = courseName + "/pic";
        logger.debug("Multiple file upload!");

        String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename())
                .filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));

        if (StringUtils.isEmpty(uploadedFileName)) {
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

            if (file.isEmpty()) {
                continue; //next pls
            }

            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + prefix + "/" + file.getOriginalFilename());
            Files.write(path, bytes);

        }

    }
}
