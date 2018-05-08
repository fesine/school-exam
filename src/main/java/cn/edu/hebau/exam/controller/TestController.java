package cn.edu.hebau.exam.controller;

import cn.edu.hebau.exam.po.SysUserPo;
import cn.edu.hebau.exam.service.SysUserService;
import com.fesine.commons.entity.Result;
import com.fesine.commons.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 类描述
 * @author: Fesine
 * @createTime:2018/5/8
 * @update:修改内容
 * @author: Fesine
 * @updateTime:2018/5/8
 */
@RestController
@RequestMapping("/v1")
public class TestController {

    @Autowired
    private SysUserService userService;

    @GetMapping("/hello")
    public Result hello(){
        Map<String,Object> map = new HashMap<>();
        map.put("success",true);
        map.put("time",new Date());
        map.put("age",10);
        map.put("resp","你好！");
        return ResultUtils.success(map);
    }

    @GetMapping("/user")
    public Result getUsers(){
        List<SysUserPo> list = userService.listAll(new SysUserPo());
        return ResultUtils.success(list);
    }
}
