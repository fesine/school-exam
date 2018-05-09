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
public class ExamDto {

    private Integer id;
    /**
     * 考试类型 1:闭卷 2:开卷       db_column: exam_type
     */
    private Integer examType;
    /**
     * 考试年级id       db_column: grade_id
     */
    private Integer gradeId;
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
     * 课程名称
     */
    private String courseName;

}
