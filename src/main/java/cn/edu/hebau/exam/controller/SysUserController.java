package cn.edu.hebau.exam.controller;

import cn.edu.hebau.exam.exception.ExamException;
import cn.edu.hebau.exam.po.SysUserPo;
import cn.edu.hebau.exam.service.SysUserService;
import com.fesine.commons.entity.Result;
import com.fesine.commons.enums.ResultEnum;
import com.fesine.commons.util.CryptographyUtil;
import com.fesine.commons.util.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 管理员模块
 * @author: Fesine
 * @createTime:2018/5/8
 * @update:修改内容
 * @author: Fesine
 * @updateTime:2018/5/8
 */
@RestController
@RequestMapping("/v1")
public class SysUserController {
    private static final Logger logger = LoggerFactory.getLogger(SysUserController.class);

    @Autowired
    private SysUserService userService;

    @PostMapping("/user/login")
    public Result login(HttpServletRequest request, SysUserPo po) {
        Map<String, Object> map = new HashMap<>();
        String captchaToken = ((String) request.getSession().getAttribute("captchaToken"))
                .toLowerCase();
        String checkCode = request.getParameter("checkCode").toLowerCase();
        logger.info("----->captchaToken:" + captchaToken + ",checkCode:" + checkCode);
        //不区分大小写
        if (!captchaToken.equals(checkCode.toLowerCase())) {
            return ResultUtils.error(405, "验证码错误!");
        }
        //验证用户名和密码
        String username = po.getUsername();
        String password = po.getPassword();
        SysUserPo tempPo = new SysUserPo();
        tempPo.setUsername(username);
        tempPo.setPassword(CryptographyUtil.md5(password, "fesine"));
        tempPo = userService.get(tempPo);
        if (null == tempPo) {
            return ResultUtils.error(405, "用户名或密码错误!");
        }
        map.put("user", tempPo);
        return ResultUtils.success(map);
    }

    @GetMapping("/user/logout")
    public Result logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return ResultUtils.success();
    }

    @GetMapping("/users")
    public Result list(SysUserPo po) {
        List<SysUserPo> list = userService.listAll(po);
        return ResultUtils.success(list);
    }

    @GetMapping("/user/{id}")
    public Result get(@PathVariable("id") Integer id) {
        Map<String, Object> map = new HashMap<>();
        SysUserPo po = new SysUserPo();
        po.setId(id);
        po = userService.get(po);
        if (po != null) {
            map.put("user", po);
        }
        return ResultUtils.success(map);
    }

    /**
     * 添加管理员信息
     *
     * @param po
     * @return
     */
    @PostMapping("/user")
    public Result save(SysUserPo po) {
        int i = userService.save(po);
        if (i == 1) {
            return ResultUtils.success(ResultEnum.CREATED, po);
        }
        throw new ExamException(ResultEnum.INVALID_REQUEST);
    }

    /**
     * 修改管理员信息
     *
     * @param po
     * @return
     */
    @PutMapping("/user/{id}")
    public Result update(@PathVariable Integer id, SysUserPo po) {
        int i = userService.update(po);
        if (i == 1) {
            return ResultUtils.success(ResultEnum.CREATED, po);
        }
        throw new ExamException(ResultEnum.INVALID_REQUEST);
    }


    /**
     * 删除管理员信息
     *
     * @param id
     * @return
     */
    @DeleteMapping("/user/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        SysUserPo po = new SysUserPo();
        po.setId(id);
        int i = userService.delete(po);
        if (i == 1) {
            return ResultUtils.success(ResultEnum.DELETED, id);
        }
        throw new ExamException(ResultEnum.INVALID_REQUEST);
    }

    @DeleteMapping("/users/{ids}")
    public Result deleteInfos(@PathVariable String ids) {
        String[] idsArr = ids.split(",");
        SysUserPo po;
        try {
            for (String s : idsArr) {
                po = new SysUserPo();
                po.setId(Integer.parseInt(s));
                userService.delete(po);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new ExamException(ResultEnum.INVALID_REQUEST);
        }
        return ResultUtils.success(ResultEnum.DELETED, ids);
    }
}
