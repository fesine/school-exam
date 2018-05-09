package cn.edu.hebau.exam.controller;

import cn.edu.hebau.exam.exception.ExamException;
import cn.edu.hebau.exam.po.ClassroomPo;
import cn.edu.hebau.exam.service.ClassroomService;
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
public class ClassroomController {
    @Autowired
    private ClassroomService service;


    @PostMapping("/classroom")
    public Result add(ClassroomPo po) {
        int i = service.save(po);
        if (i != 1) {
            throw new ExamException(ResultEnum.INVALID_REQUEST);
        }
        return ResultUtils.success(ResultEnum.CREATED,po);
    }

    @PutMapping("/classroom/{id}")
    public Result update(@PathVariable Integer id,ClassroomPo po) {
        int i = service.update(po);
        if (i != 1) {
            throw new ExamException(ResultEnum.INVALID_REQUEST);
        }
        return ResultUtils.success(ResultEnum.CREATED,po);
    }

    @DeleteMapping("/classroom/{id}")
    public Result delete(@PathVariable Integer id) {
        ClassroomPo po = new ClassroomPo();
        po.setId(id);
        int i = service.delete(po);
        if (i != 1) {
            throw new ExamException(ResultEnum.INVALID_REQUEST);
        }
        return ResultUtils.success(ResultEnum.DELETED,po);
    }

    @DeleteMapping("/classrooms/{ids}")
    public Result deleteInfos(@PathVariable String ids) {
        String[] idsArr = ids.split(",");
        ClassroomPo po;
        try {
            for (String s : idsArr) {
                po = new ClassroomPo();
                po.setId(Integer.parseInt(s));
                service.delete(po);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new ExamException(ResultEnum.INVALID_REQUEST);
        }
        return ResultUtils.success(ResultEnum.DELETED, ids);
    }

    @GetMapping("/classroom")
    public Result getCode(ClassroomPo po) {
        return ResultUtils.success(service.get(po));
    }

    @GetMapping("/classrooms")
    public Result list(ClassroomPo po
            , @RequestParam(defaultValue = "1") Integer page
            , @RequestParam(defaultValue = "10") Integer limit) {
        po.addOrderBy(Order.asc("classroom_name"));
        QueryResult<ClassroomPo> list = service.listPage(po, page, limit);
        return ResultUtils.success(list);
    }


}