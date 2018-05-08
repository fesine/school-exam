package cn.edu.hebau.exam.handle;

import cn.edu.hebau.exam.exception.ExamException;
import com.fesine.commons.entity.Result;
import com.fesine.commons.enums.ResultEnum;
import com.fesine.commons.util.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @description: 异常统一处理类
 * @author: Fesine
 * @createTime:2017/9/26 21:43
 * @update:修改内容
 * @author: Fesine
 * @updateTime:2017/9/26 21:43
 */
@ControllerAdvice
public class ExceptionHandle {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ResponseBody
    //指定捕获的异常类型
    @ExceptionHandler(value = Exception.class)
    public Result handle(Exception e) {
        if (e instanceof ExamException) {
            ExamException ce = (ExamException) e;
            return ResultUtils.error(ce.getCode(), ce.getMessage());
        }
        logger.error("【系统异常】", e);
        return ResultUtils.error(ResultEnum.INTERNAL_SERVER_ERROR.getCode(), ResultEnum.INTERNAL_SERVER_ERROR
                .getMsg());
    }
}
