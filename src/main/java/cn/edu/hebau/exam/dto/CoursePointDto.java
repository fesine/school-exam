package cn.edu.hebau.exam.dto;

import lombok.Data;

/**
 * @description: 类描述
 * @author: Fesine
 * @createTime:2017/10/14 17:30
 * @update:修改内容
 * @author: Fesine
 * @updateTime:2017/10/14 17:30
 */
@Data
public class CoursePointDto {

    private Integer id;
    /**
     * 考试年级id       db_column: grade_id
     */
    private Integer gradeId;
    /**
     * 考试课程       db_column: course_id
     */
    private Integer courseId;
    /**
     * 年级名称
     */
    private String gradeName;
    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 知识点       db_column: course_point
     */
    private String coursePoint;

}
