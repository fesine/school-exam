package cn.edu.hebau.exam.controller;

import cn.edu.hebau.exam.dto.StudentDto;
import cn.edu.hebau.exam.exception.ExamException;
import cn.edu.hebau.exam.po.ClassroomPo;
import cn.edu.hebau.exam.po.GradePo;
import cn.edu.hebau.exam.po.StudentPo;
import cn.edu.hebau.exam.service.ClassroomService;
import cn.edu.hebau.exam.service.GradeService;
import cn.edu.hebau.exam.service.StudentService;
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
public class StudentController {
    @Autowired
    private StudentService service;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private ClassroomService classroomService;


    @PostMapping("/student")
    public Result add(StudentPo po) {
        int i = service.save(po);
        if (i != 1) {
            throw new ExamException(ResultEnum.INVALID_REQUEST);
        }
        return ResultUtils.success(ResultEnum.CREATED,po);
    }

    @PutMapping("/student/{id}")
    public Result update(@PathVariable Integer id,StudentPo po) {
        int i = service.update(po);
        if (i != 1) {
            throw new ExamException(ResultEnum.INVALID_REQUEST);
        }
        return ResultUtils.success(ResultEnum.CREATED,po);
    }

    @DeleteMapping("/student/{id}")
    public Result delete(@PathVariable Integer id) {
        StudentPo po = new StudentPo();
        po.setId(id);
        int i = service.delete(po);
        if (i != 1) {
            throw new ExamException(ResultEnum.INVALID_REQUEST);
        }
        return ResultUtils.success(ResultEnum.DELETED,po);
    }

    @DeleteMapping("/students/{ids}")
    public Result deleteInfos(@PathVariable String ids) {
        String[] idsArr = ids.split(",");
        StudentPo po;
        try {
            for (String s : idsArr) {
                po = new StudentPo();
                po.setId(Integer.parseInt(s));
                service.delete(po);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new ExamException(ResultEnum.INVALID_REQUEST);
        }
        return ResultUtils.success(ResultEnum.DELETED, ids);
    }

    @GetMapping("/student")
    public Result getCode(StudentPo po) {
        return ResultUtils.success(service.get(po));
    }

    @GetMapping("/students")
    public Result list(StudentPo po
            , @RequestParam(defaultValue = "1") Integer page
            , @RequestParam(defaultValue = "10") Integer limit) {
        po.addOrderBy(Order.asc("stu_no"));
        QueryResult<StudentPo> list = service.listPage(po, page, limit);
        Map<String, Object> map = new HashMap<>();
        map.put("totalRecord", list.getTotalRecord());
        List<GradePo> gradePoList = gradeService.listAll(new GradePo());
        List<ClassroomPo> classroomList = classroomService.listAll(new ClassroomPo());
        Map<Integer,String> gradeMap = gradePoList.stream().collect(Collectors.toMap
                (GradePo::getId, GradePo::getGradeName));
        Map<Integer,String> classroomMap = classroomList.stream().collect(Collectors.toMap
                (ClassroomPo::getId, ClassroomPo::getClassroomName));
        List<StudentDto> studentDtoList = list.getResultList().stream().map(temp ->{
            StudentDto dto = new StudentDto();
            BeanUtils.copyProperties(temp, dto);
            dto.setGradeName(gradeMap.get(dto.getGradeId()));
            dto.setClassroomName(classroomMap.get(dto.getClassroomId()));
            return dto;
        }).collect(Collectors.toList());
        map.put("resultList", studentDtoList);
        return ResultUtils.success(map);
    }


}