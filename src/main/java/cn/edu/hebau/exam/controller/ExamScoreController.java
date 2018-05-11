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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    @PostMapping("/examScore")
    public Result add(ExamScorePo po) {
        ExamScorePo addPo = new ExamScorePo();
        addPo.setExamId(po.getExamId());
        addPo.setStuNo(po.getStuNo());
        addPo = service.get(addPo);
        if (addPo != null) {
            throw new ExamException(ResultEnum.INVALID_REQUEST.getCode(), "成绩已存在!");
        }
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
     *
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
        if (StringUtils.isEmpty(po.getStuNo())) {
            po.setStuNo(null);
        }
        if (StringUtils.isEmpty(po.getStuName())) {
            po.setStuName(null);
        }
        //examId stuNo score
        QueryResult<ExamScoreUnionPo> list = unionService.listPage(po, page, limit);
        return ResultUtils.success(list);
    }

    @GetMapping("/studentScores")
    public Result studentScores(ExamScoreUnionPo po
            , @RequestParam(defaultValue = "1") Integer page
            , @RequestParam(defaultValue = "10") Integer limit) {
        if (null == po.getGradeId()) {
            throw new ExamException(ResultEnum.INVALID_REQUEST.getCode(),"请选择年级查询!");
        }
        if (StringUtils.isEmpty(po.getStuNo())) {
            po.setStuNo(null);
        }
        if (StringUtils.isEmpty(po.getStuName())) {
            po.setStuName(null);
        }
        //examId stuNo score
        QueryResult<ExamScoreUnionPo> list = unionService.selectListByStuNo(ExamScoreUnionPo.class
                .getName() + ".selectListByStuNo", po, page, limit);
        List<ExamScoreUnionPo> listPo = list.getResultList();
        List<ExamScoreUnionPo> newPoList = new ArrayList<>();
        for (int i = 0; i < listPo.size(); i++) {
            ExamScoreUnionPo newPo = new ExamScoreUnionPo();
            BeanUtils.copyProperties(listPo.get(i), newPo);
            //获取年级排名 （page-1）*limit+i+1
            newPo.setRowNo((page-1)*limit+i+1);
            newPo.setAvg(newPo.getAvg().setScale(2, BigDecimal.ROUND_HALF_UP));
            newPoList.add(newPo);
        }
        list.setResultList(newPoList);
        return ResultUtils.success(list);
    }

    /**
     * 获取学生综合排名信息
     * @param po
     * @return
     */
    @GetMapping("/studentScoresByStuNo")
    public Result studentScoresByStuNo(ExamScoreUnionPo po) {
        //年级排名
        int gradeNo=1;
        //班级排名
        int classroomNo=1;
        int type = 2;
        String msg = "各科成绩较平衡，请继续保持!";
        ExamScoreUnionPo queryPo = new ExamScoreUnionPo();
        queryPo.setGradeId(po.getGradeId());
        List<ExamScoreUnionPo> allList = unionService.selectListByStuNo(ExamScoreUnionPo.class
                .getName() + ".selectListByStuNo",queryPo);
        ExamScoreUnionPo checkPo;
        for (int i = 0; i < allList.size(); i++) {
            checkPo = allList.get(i);
            if(!checkPo.getStuNo().equals(po.getStuNo())){
                gradeNo++;
                if (checkPo.getClassroomId().equals(po.getClassroomId())) {
                    classroomNo++;
                }
            } else {
                //是当前学生成绩，分析成绩
                int chinese = checkPo.getChinese();
                int math = checkPo.getMath();
                int english = checkPo.getEnglish();
                boolean chineseFlag = false;
                boolean mathFlag = false;
                boolean englishFlag = false;
                if (chinese < 60) {
                    chineseFlag = true;
                }
                if (math < 60) {
                    mathFlag = true;
                }
                if (english < 60) {
                    englishFlag = true;
                }
                if (chineseFlag && mathFlag && englishFlag) {
                    msg = "该生全科成绩较差，建议认真学习!";
                    type = 3;
                }else if (chineseFlag && mathFlag) {
                    msg = "该生语文、数学成绩较差，建议加强语文、数学知识学习!";
                    type = 3;
                }else if (chineseFlag && englishFlag) {
                    msg = "该生语文、英语成绩较差，建议加强语文、英语知识学习!";
                    type = 3;
                }else if (mathFlag && englishFlag) {
                    msg = "该生数学、英语成绩较差，建议加强数学、英语知识学习!";
                    type = 3;
                } else if (chineseFlag) {
                    msg = "该生语文成绩较差，建议加强语文知识学习!";
                } else if (mathFlag) {
                    msg = "该生数学成绩较差，建议加强数学知识学习!";
                } else if (englishFlag) {
                    msg = "该生英语成绩较差，建议加强英语知识学习!";
                } else {
                    type = 1;
                }
                break;
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("gradeNo", "第"+gradeNo+"名");
        map.put("classroomNo", "第" + classroomNo + "名");
        map.put("msg", msg);
        map.put("type", type);
        return ResultUtils.success(map);
    }

}