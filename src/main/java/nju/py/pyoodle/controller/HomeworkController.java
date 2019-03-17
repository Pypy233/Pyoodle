package nju.py.pyoodle.controller;

import nju.py.pyoodle.dao.UserDAO;
import nju.py.pyoodle.domain.Homework;
import nju.py.pyoodle.domain.User;
import nju.py.pyoodle.service.HomeworkService;
import nju.py.pyoodle.util.DateUtil;
import nju.py.pyoodle.util.FileUtil;
import nju.py.pyoodle.util.Response;
import nju.py.pyoodle.vo.FileItemVO;
import nju.py.pyoodle.vo.HomeworkVO;
import nju.py.pyoodle.vo.HwComplexVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: py
 * @Date: 2019/2/16 下午3:03
 * @Version 1.0
 */
@RestController
@RequestMapping("/hw")
public class HomeworkController {
    private final HomeworkService homeworkService;

    private final UserDAO userDAO;

    @Autowired
    public HomeworkController(HomeworkService homeworkService, UserDAO userDAO) {
        this.homeworkService = homeworkService;
        this.userDAO = userDAO;
    }


    @PostMapping("/save")
    @ResponseBody
    public Response<Boolean> addHw(String name, String courseName, int size, String type, String ddl, String description) {
        return homeworkService.addHw(name, courseName, size, type, ddl, description);
    }

    @GetMapping("/downloadAll")
    @ResponseBody
    public Response<Boolean> downloadHws(String courseName, String hwName) {
        return homeworkService.downloadHws(courseName, hwName);
    }

    @GetMapping("/name")
    @ResponseBody
    public Response<HomeworkVO> getHwByCourseAndName(String courseName, String hwName) {
        return homeworkService.getHwByCourseAndName(courseName, hwName);
    }

    @GetMapping("/download")
    @ResponseBody
    public Response<FileItemVO> getHwPath(String courseName, String hwName, String userName) {
        try {
            User user = userDAO.getUserByName(userName);
            String path = FileUtil.getHwPath(courseName, hwName, user.getStudentNumber());
            FileItemVO itemVO = new FileItemVO();
            String s = "/Users/py";
            itemVO.setPath(path.substring(s.length()));
            itemVO.setName(path.split("/")[path.split("/").length - 1]);
            return new Response<>(true, itemVO);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new Response<>(false, "Fail to get hw path...");
        }
    }

    @RequestMapping("/ls")
    @ResponseBody
    public Response<List<HwComplexVO>> listHw(String userName) {
        return homeworkService.listHw(userName);
    }

}
