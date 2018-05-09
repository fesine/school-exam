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
import com.fesine.commons.util.DateUtils;
import com.fesine.commons.util.ResultUtils;
import com.fesine.dao.model.Order;
import com.fesine.dao.model.QueryResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
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

    private static final String dateFormStr = "yyyy年MM月dd日";

    public static Map<Integer, String> gradeMap = new HashMap<>();
    public static Map<Integer, String> classroomMap = new HashMap<>();



    @PostMapping("/student")
    public Result add(StudentPo po, @RequestParam String dateStr) {
        try {
            po.setStuNo(this.genStuNo());
            Date date = DateUtils.formatString(dateStr, dateFormStr);
            po.setStuBirthday(date);
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

    @PutMapping("/student/{id}")
    public Result update(@PathVariable Integer id, StudentPo po, @RequestParam String dateStr) {
        try {
            Date date = DateUtils.formatString(dateStr, dateFormStr);
            po.setStuBirthday(date);
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

    @DeleteMapping("/student/{id}")
    public Result delete(@PathVariable Integer id) {
        StudentPo po = new StudentPo();
        po.setId(id);
        int i = service.delete(po);
        if (i != 1) {
            throw new ExamException(ResultEnum.INVALID_REQUEST);
        }
        return ResultUtils.success(ResultEnum.DELETED, po);
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
        if (StringUtils.isNotEmpty(po.getStuNo())) {
            po.setStuNo("%".concat(po.getStuNo()).concat("%"));
        }else{
            po.setStuNo(null);
        }
        if (StringUtils.isNotEmpty(po.getStuName())) {
            po.setStuName("%".concat(po.getStuName()).concat("%"));
        } else {
            po.setStuName(null);
        }
        QueryResult<StudentPo> list = service.listPage(po, page, limit);
        Map<String, Object> map = new HashMap<>();
        if (list.getTotalRecord() == 0) {
            map.put("totalRecord", 0);
            map.put("resultList", null);
            return ResultUtils.success(map);
        }
        map.put("totalRecord", list.getTotalRecord());
        if(gradeMap.size() == 0){
            List<GradePo> gradePoList = gradeService.listAll(new GradePo());
            gradeMap = gradePoList.stream().collect(Collectors.toMap
                    (GradePo::getId, GradePo::getGradeName));
        }
        if(classroomMap.size() == 0 ){
            List<ClassroomPo> classroomList = classroomService.listAll(new ClassroomPo());
            classroomMap = classroomList.stream().collect(Collectors.toMap
                    (ClassroomPo::getId, ClassroomPo::getClassroomName));
        }
        List<StudentDto> studentDtoList = list.getResultList().stream().map(temp -> {
            StudentDto dto = new StudentDto();
            BeanUtils.copyProperties(temp, dto);
            dto.setGradeName(gradeMap.get(dto.getGradeId()));
            dto.setClassroomName(classroomMap.get(dto.getClassroomId()));
            return dto;
        }).collect(Collectors.toList());
        map.put("resultList", studentDtoList);
        return ResultUtils.success(map);
    }

    /**
     * 2018000001
     *
     * @return
     */
    private String genStuNo() {
        StudentPo po = new StudentPo();
        po.addOrderBy(Order.desc("stu_no"));
        QueryResult<StudentPo> result = service.listPage(po, 1, 1);
        int num;
        if (result.getTotalRecord() == 0) {
            num = 1;
        } else {
            String stuNo = (result.getResultList().get(0).getStuNo()).substring(4);
            num = Integer.parseInt(stuNo) + 1;
        }
        String str = String.format("%06d", num);
        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));
        return year + str;
    }


}