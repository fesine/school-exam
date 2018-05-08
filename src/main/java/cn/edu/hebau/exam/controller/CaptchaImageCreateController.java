package cn.edu.hebau.exam.controller;

import com.fesine.commons.util.CheckCodeCreateUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * @description: 生成验证码
 * @author: Fesine
 * @createTime:2017/3/22 20:23
 * @update:修改内容
 * @author: Fesine
 * @updateTime:2017/3/22 20:23
 */
@Controller
@RequestMapping("/v1")
public class CaptchaImageCreateController {
    private static final Logger logger = Logger.getLogger(CaptchaImageCreateController.class);


    @ApiOperation(value = "获取验证码", httpMethod = "GET")
    @GetMapping("/checkCode")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws
            IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            session = request.getSession();
        }
        String token = CheckCodeCreateUtil.createCode(response);
        session.setAttribute("captchaToken", token);
        //UserInfoController.initTime = System.currentTimeMillis();
        logger.info("当前的SessionID=" + session.getId() + "，验证码=" + token);
    }

}
