package cn.edu.hebau.exam.dto;

import lombok.Data;

import java.util.Date;

/**
 * @description: 类描述
 * @author: Fesine
 * @createTime:2017/10/14 17:30
 * @update:修改内容
 * @author: Fesine
 * @updateTime:2017/10/14 17:30
 */
@Data
public class ExamScoreDto {

    private Integer id;
    /**
     * 考试编号       db_column: exam_id
     */
    private Integer examId;
    /**
     * 学号       db_column: stu_no
     */
    private String stuNo;
    /**
     * 学生姓名       db_column: stu_name
     */
    private String stuName;
    /**
     * 成绩       db_column: score
     */
    private Integer score;
    /**
     * 考试年级id       db_column: grade_id
     */
    private Integer gradeId;
    /**
     * 班级号       db_column: classroom_id
     */
    private Integer classroomId;
    /**
     * 考试课程       db_column: course_id
     */
    private Integer courseId;
    /**
     * 开始时间       db_column: start_time
     */
    private Date startTime;
    /**
     * 结束时间       db_column: end_time
     */
    private Date endTime;
    /**
     * 年级名称
     */
    private String gradeName;
    /**
     * 班级名称       db_column: classroom_name
     */
    private String classroomName;
    /**
     * 课程名称
     */
    private String courseName;

}
