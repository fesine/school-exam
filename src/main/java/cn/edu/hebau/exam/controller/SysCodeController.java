package cn.edu.hebau.exam.controller;

import cn.edu.hebau.exam.exception.ExamException;
import cn.edu.hebau.exam.po.SysCodePo;
import cn.edu.hebau.exam.service.SysCodeService;
import com.fesine.commons.entity.Result;
import com.fesine.commons.enums.ResultEnum;
import com.fesine.commons.util.ResultUtils;
import com.fesine.dao.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 类描述
 * @author: Fesine
 * @createTime:2017/10/14 14:19
 * @update:修改内容
 * @author: Fesine
 * @updateTime:2017/10/14 14:19
 */
@RestController
@RequestMapping("v1")
public class SysCodeController {
    @Autowired
    private SysCodeService service;


    @PostMapping("/code")
    public Result add(SysCodePo po) {
        //如果是根节点，需要设置父节点为0
        if (po.getParentId() == null) {
            po.setParentId(0);
        }
        int i = service.save(po);
        if (i != 1) {
            throw new ExamException(ResultEnum.INVALID_REQUEST);
        }
        return ResultUtils.success(ResultEnum.CREATED,po);
    }

    @PutMapping("/code/{id}")
    public Result update(@PathVariable Integer id,SysCodePo po) {
        int i = service.update(po);
        if (i != 1) {
            throw new ExamException(ResultEnum.INVALID_REQUEST);
        }
        return ResultUtils.success(ResultEnum.CREATED,po);
    }

    @DeleteMapping("/code/{id}")
    public Result delete(@PathVariable Integer id) {
        SysCodePo po = new SysCodePo();
        po.setId(id);
        int i = service.delete(po);
        if (i != 1) {
            throw new ExamException(ResultEnum.INVALID_REQUEST);
        }
        return ResultUtils.success(ResultEnum.DELETED,po);
    }

    @DeleteMapping("/codes/{ids}")
    public Result deleteInfos(@PathVariable String ids) {
        String[] idsArr = ids.split(",");
        SysCodePo po;
        try {
            for (String s : idsArr) {
                po = new SysCodePo();
                po.setId(Integer.parseInt(s));
                service.delete(po);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new ExamException(ResultEnum.INVALID_REQUEST);
        }
        return ResultUtils.success(ResultEnum.DELETED, ids);
    }

    @GetMapping("/code")
    public Result getCode(SysCodePo po) {
        return ResultUtils.success(service.get(po));
    }

    @GetMapping("/codes")
    public Result getList(SysCodePo po) {
        po.addOrderBy(Order.asc("parent_id"));
        po.addOrderBy(Order.asc("order_no"));
        return ResultUtils.success(service.listCode(po));
    }

    /**
     * 根据权限查询
     * @param parentCode
     * @param request
     * @return
     */
    @GetMapping("/codes/{parentCode}")
    public Result getListByParent(@PathVariable String parentCode, HttpServletRequest request) {
        Integer grade = (Integer) request.getSession().getAttribute("grade");
        SysCodePo po = new SysCodePo();
        po.setCodeId(parentCode);
        po = service.get(po);
        Integer pid;
        if (po != null) {
            pid = po.getId();
            po = new SysCodePo();
            po.setParentId(pid);
            po.setGrade(grade);
            return ResultUtils.success(service.listAll(po));
        }
        return ResultUtils.error(ResultEnum.NOT_FOUND.getCode(), ResultEnum.NOT_FOUND.getMsg());
    }

}