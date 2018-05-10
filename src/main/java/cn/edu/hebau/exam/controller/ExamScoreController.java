package cn.edu.hebau.exam.controller;

import cn.edu.hebau.exam.exception.ExamException;
import cn.edu.hebau.exam.po.ExamScorePo;
import cn.edu.hebau.exam.po.ExamScoreUnionPo;
import cn.edu.hebau.exam.service.ExamScoreService;
import cn.edu.hebau.exam.service.ExamScoreUnionService;
import com.fesine.commons.entity.Result;
import com.fesine.commons.enums.ResultEnum;
import com.fesine.commons.util.ResultUtils;
import com.fesine.dao.model.Order;
import com.fesine.dao.model.QueryResult;
import org.apache.commons.lang.StringUtils;
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
public class ExamScoreController {
    @Autowired
    private ExamScoreService service;

    @Autowired
    private ExamScoreUnionService unionService;


    private static final String dateFormStr = "yyyy-MM-dd HH:mm:ss";





    @PostMapping("/examScore")
    public Result add(ExamScorePo po) {
            int i = service.save(po);
            if (i != 1) {
                throw new ExamException(ResultEnum.INVALID_REQUEST);
            }
            return ResultUtils.success(ResultEnum.CREATED, po);
    }

    @PutMapping("/examScore/{id}")
    public Result update(@PathVariable Integer id, ExamScorePo po) {
            int i = service.update(po);
            if (i != 1) {
                throw new ExamException(ResultEnum.INVALID_REQUEST);
            }
            return ResultUtils.success(ResultEnum.CREATED, po);
    }

    @DeleteMapping("/examScore/{id}")
    public Result delete(@PathVariable Integer id) {
        ExamScorePo po = new ExamScorePo();
        po.setId(id);
        int i = service.delete(po);
        if (i != 1) {
            throw new ExamException(ResultEnum.INVALID_REQUEST);
        }
        return ResultUtils.success(ResultEnum.DELETED, po);
    }

    @DeleteMapping("/examScores/{ids}")
    public Result deleteInfos(@PathVariable String ids) {
        String[] idsArr = ids.split(",");
        ExamScorePo po;
        try {
            for (String s : idsArr) {
                po = new ExamScorePo();
                po.setId(Integer.parseInt(s));
                service.delete(po);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new ExamException(ResultEnum.INVALID_REQUEST);
        }
        return ResultUtils.success(ResultEnum.DELETED, ids);
    }

    @GetMapping("/examScore")
    public Result getCode(ExamScorePo po) {
        return ResultUtils.success(service.get(po));
    }

    /**
     * 获取分数
     * @param po
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/examScores")
    public Result list(ExamScoreUnionPo po
            , @RequestParam(defaultValue = "1") Integer page
            , @RequestParam(defaultValue = "10") Integer limit) {
        po.addOrderBy(Order.desc("sc.exam_id"));
        po.addOrderBy(Order.asc("sc.stu_no"));
        if(StringUtils.isEmpty(po.getStuNo())){
            po.setStuNo(null);
        }
        if(StringUtils.isEmpty(po.getStuName())){
            po.setStuName(null);
        }
        //examId stuNo score
        QueryResult<ExamScoreUnionPo> list = unionService.listPage(po, page, limit);
        return ResultUtils.success(list);
    }

}