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
public class CourseDto {

    /**
     * 编号       db_column: id
     */
    private int id;
    /**
     * 年级编号
     */
    private int gradeId;
    /**
     * 年级名称
     */
    private String gradeName;
    /**
     * 课程名称
     */
    private String courseName;

}
