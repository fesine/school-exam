package cn.edu.hebau.exam.controller;

import cn.edu.hebau.exam.dto.ExamDto;
import cn.edu.hebau.exam.exception.ExamException;
import cn.edu.hebau.exam.po.CoursePo;
import cn.edu.hebau.exam.po.ExamPo;
import cn.edu.hebau.exam.po.GradePo;
import cn.edu.hebau.exam.service.CourseService;
import cn.edu.hebau.exam.service.ExamService;
import cn.edu.hebau.exam.service.GradeService;
import com.fesine.commons.entity.Result;
import com.fesine.commons.enums.ResultEnum;
import com.fesine.commons.util.DateUtils;
import com.fesine.commons.util.ResultUtils;
import com.fesine.dao.model.Order;
import com.fesine.dao.model.QueryResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
public class ExamController {
    @Autowired
    private ExamService service;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private CourseService courseService;

    private static final String dateFormStr = "yyyy-MM-dd HH:mm:ss";

    public static Map<Integer, String> courseMap = new HashMap<>();



    @PostMapping("/exam")
    public Result add(ExamPo po, @RequestParam String startDateStr, @RequestParam String endDateStr) {
        try {
            Date startTime = DateUtils.formatString(startDateStr, dateFormStr);
            Date endTime = DateUtils.formatString(endDateStr, dateFormStr);
            po.setStartTime(startTime);
            po.setEndTime(endTime);
            int i = service.save(po);
            if (i != 1) {
                throw new ExamException(ResultEnum.INVALID_REQUEST);
            }
            return ResultUtils.success(ResultEnum.CREATED, po);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExamException(ResultEnum.INVALID_REQUEST);
        }
    }

    @PutMapping("/exam/{id}")
    public Result update(@PathVariable Integer id, ExamPo po, @RequestParam String startDateStr,
                         @RequestParam String endDateStr) {
        try {
            Date startTime = DateUtils.formatString(startDateStr, dateFormStr);
            Date endTime = DateUtils.formatString(endDateStr, dateFormStr);
            po.setStartTime(startTime);
            po.setEndTime(endTime);
            int i = service.update(po);
            if (i != 1) {
                throw new ExamException(ResultEnum.INVALID_REQUEST);
            }
            return ResultUtils.success(ResultEnum.CREATED, po);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExamException(ResultEnum.INVALID_REQUEST);
        }
    }

    @DeleteMapping("/exam/{id}")
    public Result delete(@PathVariable Integer id) {
        ExamPo po = new ExamPo();
        po.setId(id);
        int i = service.delete(po);
        if (i != 1) {
            throw new ExamException(ResultEnum.INVALID_REQUEST);
        }
        return ResultUtils.success(ResultEnum.DELETED, po);
    }

    @DeleteMapping("/exams/{ids}")
    public Result deleteInfos(@PathVariable String ids) {
        String[] idsArr = ids.split(",");
        ExamPo po;
        try {
            for (String s : idsArr) {
                po = new ExamPo();
                po.setId(Integer.parseInt(s));
                service.delete(po);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new ExamException(ResultEnum.INVALID_REQUEST);
        }
        return ResultUtils.success(ResultEnum.DELETED, ids);
    }

    @GetMapping("/exam")
    public Result getCode(ExamPo po) {
        return ResultUtils.success(service.get(po));
    }

    @GetMapping("/exams")
    public Result list(ExamPo po
            , @RequestParam(defaultValue = "1") Integer page
            , @RequestParam(defaultValue = "10") Integer limit) {
        po.addOrderBy(Order.asc("grade_id"));
        po.addOrderBy(Order.desc("start_time"));
        QueryResult<ExamPo> list = service.listPage(po, page, limit);
        Map<String, Object> map = new HashMap<>();
        if (list.getTotalRecord() == 0) {
            map.put("totalRecord", 0);
            map.put("resultList", null);
            return ResultUtils.success(map);
        }
        map.put("totalRecord", list.getTotalRecord());
        if(StudentController.gradeMap.size() == 0){
            List<GradePo> gradePoList = gradeService.listAll(new GradePo());
            StudentController.gradeMap = gradePoList.stream().collect(Collectors.toMap
                    (GradePo::getId, GradePo::getGradeName));
        }
        if(courseMap.size() == 0 ){
            List<CoursePo> courseList = courseService.listAll(new CoursePo());
            courseMap = courseList.stream().collect(Collectors.toMap
                    (CoursePo::getId, CoursePo::getCourseName));
        }
        List<ExamDto> examDtoList = list.getResultList().stream().map(temp -> {
            ExamDto dto = new ExamDto();
            BeanUtils.copyProperties(temp, dto);
            dto.setGradeName(StudentController.gradeMap.get(dto.getGradeId()));
            dto.setCourseName(courseMap.get(dto.getCourseId()));
            return dto;
        }).collect(Collectors.toList());
        map.put("resultList", examDtoList);
        return ResultUtils.success(map);
    }

}