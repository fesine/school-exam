package cn.edu.hebau.exam.exception;


import com.fesine.commons.enums.ResultEnum;

/**
 * @description: 自定义异常类，继承RuntimeException，spring才会进行事务回滚
 * @author: Fesine
 * @createTime:2017/8/30 21:43
 * @update:修改内容
 * @author: Fesine
 * @updateTime:2017/8/30 21:43
 */
public class ExamException extends RuntimeException {
    private static final long serialVersionUID = 1246866093608034923L;

    /**
     * 异常码
     */
    private Integer code;

    public ExamException(ResultEnum rs) {
        super(rs.getMsg());
        this.code = rs.getCode();
    }

    public ExamException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
