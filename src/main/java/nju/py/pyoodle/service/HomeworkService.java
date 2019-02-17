package nju.py.pyoodle.service;

import nju.py.pyoodle.util.Response;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * @Author: py
 * @Date: 2019/2/16 下午2:49
 * @Version 1.0
 */
@Service
public interface HomeworkService {
    Response<Boolean> addHw(String courseName, String hwName, String description, LocalDate ddl);
}
