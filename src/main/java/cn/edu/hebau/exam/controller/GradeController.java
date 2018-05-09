package cn.edu.hebau.exam.controller;

import cn.edu.hebau.exam.exception.ExamException;
import cn.edu.hebau.exam.po.GradePo;
import cn.edu.hebau.exam.service.GradeService;
import com.fesine.commons.entity.Result;
import com.fesine.commons.enums.ResultEnum;
import com.fesine.commons.util.ResultUtils;
import com.fesine.dao.model.Order;
import com.fesine.dao.model.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
public class GradeController {
    @Autowired
    private GradeService service;


    @PostMapping("/grade")
    public Result add(GradePo po) {
        int i = service.save(po);
        if (i != 1) {
            throw new ExamException(ResultEnum.INVALID_REQUEST);
        }
        return ResultUtils.success(ResultEnum.CREATED,po);
    }

    @PutMapping("/grade/{id}")
    public Result update(@PathVariable Integer id,GradePo po) {
        int i = service.update(po);
        if (i != 1) {
            throw new ExamException(ResultEnum.INVALID_REQUEST);
        }
        return ResultUtils.success(ResultEnum.CREATED,po);
    }

    @DeleteMapping("/grade/{id}")
    public Result delete(@PathVariable Integer id) {
        GradePo po = new GradePo();
        po.setId(id);
        int i = service.delete(po);
        if (i != 1) {
            throw new ExamException(ResultEnum.INVALID_REQUEST);
        }
        return ResultUtils.success(ResultEnum.DELETED,po);
    }

    @DeleteMapping("/grades/{ids}")
    public Result deleteInfos(@PathVariable String ids) {
        String[] idsArr = ids.split(",");
        GradePo po;
        try {
            for (String s : idsArr) {
                po = new GradePo();
                po.setId(Integer.parseInt(s));
                service.delete(po);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new ExamException(ResultEnum.INVALID_REQUEST);
        }
        return ResultUtils.success(ResultEnum.DELETED, ids);
    }

    @GetMapping("/grade")
    public Result getCode(GradePo po) {
        return ResultUtils.success(service.get(po));
    }

    @GetMapping("/grades")
    public Result list(GradePo po
            , @RequestParam(defaultValue = "1") Integer page
            , @RequestParam(defaultValue = "10") Integer limit) {
        po.addOrderBy(Order.asc("id"));
        po.addOrderBy(Order.asc("grade_name"));
        QueryResult<GradePo> list = service.listPage(po, page, limit);
        return ResultUtils.success(list);
    }


}