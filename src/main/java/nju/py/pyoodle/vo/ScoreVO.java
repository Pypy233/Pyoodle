package nju.py.pyoodle.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nju.py.pyoodle.domain.Score;

/**
 * @Author: py
 * @Date: 2019/3/17 下午4:02
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreVO {
    private String studentName;

    private String studentNum;

    private int score;

    public ScoreVO(Score score) {
        this.studentName = score.getStudent().getName();
        this.studentNum = score.getStudent().getStudentNumber();
        this.score = score.getVal();
    }
}
