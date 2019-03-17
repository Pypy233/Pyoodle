package nju.py.pyoodle.service;

import nju.py.pyoodle.domain.Homework;
import nju.py.pyoodle.util.Response;
import nju.py.pyoodle.vo.FileItemVO;
import nju.py.pyoodle.vo.HomeworkVO;
import nju.py.pyoodle.vo.HwComplexVO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * @Author: py
 * @Date: 2019/2/16 下午2:49
 * @Version 1.0
 */
@Service
public interface HomeworkService {
    Response<Boolean> addHw(String name, String courseName, int size, String type, String ddl, String description);

    Response<Boolean> downloadHws(String courseName, String hwName);

    Response<HomeworkVO> getHwByCourseAndName(String courseName, String hwName);

    Response<List<HwComplexVO>> listHw(String userName);
}
