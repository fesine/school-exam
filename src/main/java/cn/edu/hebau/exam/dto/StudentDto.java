package cn.edu.hebau.exam.dto;

import cn.edu.hebau.exam.po.StudentPo;
import lombok.Data;

/**
 * @description: 类描述
 * @author: Fesine
 * @createTime:2018/5/9
 * @update:修改内容
 * @author: Fesine
 * @updateTime:2018/5/9
 */
@Data
public class StudentDto extends StudentPo {
    private static final long serialVersionUID = 2298369053037819205L;

    /**
     * 年级名称
     */
    private String gradeName;

    /**
     * 班级名称
     */
    private String classroomName;

}
