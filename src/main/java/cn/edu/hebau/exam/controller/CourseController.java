package cn.edu.hebau.exam.controller;

import cn.edu.hebau.exam.dto.CourseDto;
import cn.edu.hebau.exam.exception.ExamException;
import cn.edu.hebau.exam.po.CoursePo;
import cn.edu.hebau.exam.po.GradePo;
import cn.edu.hebau.exam.service.CourseService;
import cn.edu.hebau.exam.service.GradeService;
import com.fesine.commons.entity.Result;
import com.fesine.commons.enums.ResultEnum;
import com.fesine.commons.util.ResultUtils;
import com.fesine.dao.model.Order;
import com.fesine.dao.model.QueryResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
public class CourseController {
    @Autowired
    private CourseService service;

    @Autowired
    private GradeService gradeService;


    @PostMapping("/course")
    public Result add(CoursePo po) {
        int i = service.save(po);
        if (i != 1) {
            throw new ExamException(ResultEnum.INVALID_REQUEST);
        }
        return ResultUtils.success(ResultEnum.CREATED,po);
    }

    @PutMapping("/course/{id}")
    public Result update(@PathVariable Integer id,CoursePo po) {
        int i = service.update(po);
        if (i != 1) {
            throw new ExamException(ResultEnum.INVALID_REQUEST);
        }
        return ResultUtils.success(ResultEnum.CREATED,po);
    }

    @DeleteMapping("/course/{id}")
    public Result delete(@PathVariable Integer id) {
        CoursePo po = new CoursePo();
        po.setId(id);
        int i = service.delete(po);
        if (i != 1) {
            throw new ExamException(ResultEnum.INVALID_REQUEST);
        }
        return ResultUtils.success(ResultEnum.DELETED,po);
    }

    @DeleteMapping("/courses/{ids}")
    public Result deleteInfos(@PathVariable String ids) {
        String[] idsArr = ids.split(",");
        CoursePo po;
        try {
            for (String s : idsArr) {
                po = new CoursePo();
                po.setId(Integer.parseInt(s));
                service.delete(po);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new ExamException(ResultEnum.INVALID_REQUEST);
        }
        return ResultUtils.success(ResultEnum.DELETED, ids);
    }

    @GetMapping("/course")
    public Result getCode(CoursePo po) {
        return ResultUtils.success(service.get(po));
    }

    @GetMapping("/courses")
    public Result list(CoursePo po
            , @RequestParam(defaultValue = "1") Integer page
            , @RequestParam(defaultValue = "10") Integer limit) {
        po.addOrderBy(Order.asc("grade_id"));
        po.addOrderBy(Order.asc("course_name"));
        QueryResult<CoursePo> list = service.listPage(po, page, limit);
        Map<String, Object> map = new HashMap<>();
        map.put("totalRecord", list.getTotalRecord());
        List<GradePo> gradePoList = gradeService.listAll(new GradePo());
        Map<Integer,String> gradeMap = gradePoList.stream().collect(Collectors.toMap
                (GradePo::getId, GradePo::getGradeName));
        List<CourseDto> courseDtoList = list.getResultList().stream().map(temp ->{
            CourseDto dto = new CourseDto();
            BeanUtils.copyProperties(temp, dto);
            dto.setGradeName(gradeMap.get(dto.getGradeId()));
            return dto;
        }).collect(Collectors.toList());
        map.put("resultList", courseDtoList);
        return ResultUtils.success(map);
    }


}