package cn.edu.hebau.exam.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 类描述
 * @author: Fesine
 * @createTime:2018/5/9
 * @update:修改内容
 * @author: Fesine
 * @updateTime:2018/5/9
 */
@Data
public class StudentDto implements Serializable {
    private static final long serialVersionUID = 2298369053037819205L;

    //columns START
    /**
     * 主键       db_column: id
     */
    private Integer id;
    /**
     * 学号       db_column: stu_no
     */
    private String stuNo;
    /**
     * 学生姓名       db_column: stu_name
     */
    private String stuName;
    /**
     * 性别 0:未知 1:男 2:女       db_column: stu_sex
     */
    private Integer stuSex;
    /**
     * 出生日期 1990-01-10       db_column: stu_birthday
     */
    private Date stuBirthday;
    /**
     * 手机号码       db_column: stu_cell
     */
    private String stuCell;
    /**
     * 地址信息       db_column: stu_address
     */
    private String stuAddress;
    /**
     * 电子邮箱       db_column: stu_email
     */
    private String stuEmail;
    /**
     * 年级号       db_column: grade_id
     */
    private Integer gradeId;
    /**
     * 班级号       db_column: classroom_id
     */
    private Integer classroomId;
    /**
     * 创建时间       db_column: create_time
     */
    private Date createTime;
    /**
     * 最后更新时间       db_column: last_update_time
     */
    private Date lastUpdateTime;
    //columns END

    /**
     * 年级名称
     */
    private String gradeName;

    /**
     * 班级名称
     */
    private String classroomName;

}
