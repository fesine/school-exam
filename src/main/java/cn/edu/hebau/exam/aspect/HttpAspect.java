package cn.edu.hebau.exam.aspect;

import cn.edu.hebau.exam.exception.ExamException;
import cn.edu.hebau.exam.po.SysUserPo;
import com.alibaba.fastjson.JSON;
import com.fesine.commons.enums.ResultEnum;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: aop功能实现
 * 添加@Aspect注解
 * @Component注解 添加到spring容器中
 * @author: Fesine
 * @createTime:2017/9/26 20:07
 * @update:修改内容
 * @author: Fesine
 * @updateTime:2017/9/26 20:07
 */
@Aspect
@Component
public class HttpAspect {

    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    @Value("${server.authFlag}")
    private boolean authFlag;

    /**
     * 拦截Controller所有方法所有参数
     * 排除验证码获取操作
     */
    @Pointcut("execution(public * cn.edu.hebau.exam.controller.*.*(..)) " +
            "&& !execution(public * cn.edu.hebau.exam.controller.CaptchaImageCreateController.*(..))"+
            "&& !execution(public * cn.edu.hebau.exam.controller.SysUserController.logout(..))"+
            "&& !execution(public * cn.edu.hebau.exam.controller.SysUserController.login(..))")
    public void log() {

    }

    /**
     * 执行方法前处理
     */
    @Before("log()")
    public void doBefore(JoinPoint jp) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //url
        logger.info("url={}",request.getRequestURL());
        //请求method
        logger.info("method={}",request.getMethod());
        //ip
        logger.info("ip={}",request.getRemoteHost());
        //类方法
        //jp.getSignature().getDeclaringTypeName()-->类
        //jp.getSignature().getName()-->方法名
        logger.info("class_method={}.{}", jp.getSignature().getDeclaringTypeName(),jp
                .getSignature().getName());
        //参数
        logger.info("args={}",jp.getArgs());
        SysUserPo userPo = ((SysUserPo) request.getSession().getAttribute("user"));
        //登录验证
        if (authFlag) {
            if(null == userPo){
                throw new ExamException(ResultEnum.UNAUTHORIZED);
            } else {
                logger.info("username --> {}", userPo.getUsername());
            }
        }
    }

    /**
     * 执行完方法后处理
     */
    //@After("log()")
    //public void doAfter() {
    //    logger.info("2222222");
    //}

    @AfterReturning(returning = "object", pointcut = "log()")
    public void afterReturning(Object object) {
        logger.info("object={}", JSON.toJSONStringWithDateFormat(object,"yyyy-MM-dd HH:mm:ss"));
    }
}
