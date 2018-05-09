package cn.edu.hebau.exam.controller;
import cn.edu.hebau.exam.dto.CoursePointDto;
import cn.edu.hebau.exam.exception.ExamException;
import cn.edu.hebau.exam.po.CoursePo;
import cn.edu.hebau.exam.po.CoursePointPo;
import cn.edu.hebau.exam.po.GradePo;
import cn.edu.hebau.exam.service.CoursePointService;
import cn.edu.hebau.exam.service.CourseService;
import cn.edu.hebau.exam.service.GradeService;
import com.fesine.commons.entity.Result;
import com.fesine.commons.enums.ResultEnum;
import com.fesine.commons.util.ResultUtils;
import com.fesine.dao.model.QueryResult;
import org.apache.commons.lang.StringUtils;
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
public class CoursePointController {
    @Autowired
    private CoursePointService service;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private CourseService courseService;


    public static Map<Integer, Integer> courseGradeMap = new HashMap<>();


    @PostMapping("/coursePoint")
    public Result add(CoursePointPo po) {
        try {
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

    @PutMapping("/coursePoint/{id}")
    public Result update(@PathVariable Integer id, CoursePointPo po) {
        try {
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

    @DeleteMapping("/coursePoint/{id}")
    public Result delete(@PathVariable Integer id) {
        CoursePointPo po = new CoursePointPo();
        po.setId(id);
        int i = service.delete(po);
        if (i != 1) {
            throw new ExamException(ResultEnum.INVALID_REQUEST);
        }
        return ResultUtils.success(ResultEnum.DELETED, po);
    }

    @DeleteMapping("/coursePoints/{ids}")
    public Result deleteInfos(@PathVariable String ids) {
        String[] idsArr = ids.split(",");
        CoursePointPo po;
        try {
            for (String s : idsArr) {
                po = new CoursePointPo();
                po.setId(Integer.parseInt(s));
                service.delete(po);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new ExamException(ResultEnum.INVALID_REQUEST);
        }
        return ResultUtils.success(ResultEnum.DELETED, ids);
    }

    @GetMapping("/coursePoint")
    public Result getCode(CoursePointPo po) {
        return ResultUtils.success(service.get(po));
    }

    @GetMapping("/coursePoints")
    public Result list(CoursePointPo po
            , @RequestParam(defaultValue = "1") Integer page
            , @RequestParam(defaultValue = "10") Integer limit) {
        if (StringUtils.isNotEmpty(po.getCoursePoint())) {
            po.setCoursePoint("%".concat(po.getCoursePoint()).concat("%"));
        } else {
            po.setCoursePoint(null);
        }
        QueryResult<CoursePointPo> list = service.listPage(po, page, limit);
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
        if(ExamController.courseMap.size() == 0 ){
            List<CoursePo> courseList = courseService.listAll(new CoursePo());
            ExamController.courseMap = courseList.stream().collect(Collectors.toMap
                    (CoursePo::getId, CoursePo::getCourseName));
            if(courseGradeMap.size() == 0){
                courseGradeMap = courseList.stream().collect(Collectors.toMap
                        (CoursePo::getId, CoursePo::getGradeId));
            }
        }
        List<CoursePointDto> coursePointDtoList = list.getResultList().stream().map(temp -> {
            CoursePointDto dto = new CoursePointDto();
            BeanUtils.copyProperties(temp, dto);
            //根据课程号获取课程名
            dto.setCourseName(ExamController.courseMap.get(dto.getCourseId()));
            dto.setGradeId(courseGradeMap.get(dto.getCourseId()));
            dto.setGradeName(StudentController.gradeMap.get(dto.getGradeId()));
            return dto;
        }).collect(Collectors.toList());
        map.put("resultList", coursePointDtoList);
        return ResultUtils.success(map);
    }

}