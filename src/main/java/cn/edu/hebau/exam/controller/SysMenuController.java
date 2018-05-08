package cn.edu.hebau.exam.controller;

import cn.edu.hebau.exam.dto.SysMenuDto;
import cn.edu.hebau.exam.exception.ExamException;
import cn.edu.hebau.exam.po.SysMenuPo;
import cn.edu.hebau.exam.po.SysUserPo;
import cn.edu.hebau.exam.service.SysMenuService;
import com.fesine.commons.entity.Result;
import com.fesine.commons.enums.ResultEnum;
import com.fesine.commons.util.ResultUtils;
import com.fesine.dao.model.Order;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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
public class SysMenuController {
    @Autowired
    private SysMenuService service;


    @PostMapping("/menu")
    public Result add(SysMenuPo po) {
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

    @PutMapping("/menu/{id}")
    public Result update(@PathVariable Integer id,SysMenuPo po) {
        int i = service.update(po);
        if (i != 1) {
            throw new ExamException(ResultEnum.INVALID_REQUEST);
        }
        return ResultUtils.success(ResultEnum.CREATED,po);
    }

    @DeleteMapping("/menu/{id}")
    public Result delete(@PathVariable Integer id) {
        SysMenuPo po = new SysMenuPo();
        po.setId(id);
        int i = service.delete(po);
        if (i != 1) {
            throw new ExamException(ResultEnum.INVALID_REQUEST);
        }
        return ResultUtils.success(ResultEnum.DELETED,po);
    }

    @DeleteMapping("/menus/{ids}")
    public Result deleteInfos(@PathVariable String ids) {
        String[] idsArr = ids.split(",");
        SysMenuPo po;
        try {
            for (String s : idsArr) {
                po = new SysMenuPo();
                po.setId(Integer.parseInt(s));
                service.delete(po);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new ExamException(ResultEnum.INVALID_REQUEST);
        }
        return ResultUtils.success(ResultEnum.DELETED, ids);
    }

    @GetMapping("/menu")
    public Result getMenu(SysMenuPo po) {
        return ResultUtils.success(service.get(po));
    }

    @GetMapping("/menus")
    public Result getList(SysMenuPo po) {
        po.addOrderBy(Order.asc("parent_id"));
        po.addOrderBy(Order.asc("order_no"));
        return ResultUtils.success(service.listMenu(po));
    }

    @GetMapping("/menus/{id}")
    public Result listMenu(HttpServletRequest request,@PathVariable Integer id) {
        Integer grade = ((SysUserPo)request.getSession().getAttribute("user")).getGrade();
        if (null !=request.getSession().getAttribute("menuList")){
            return ResultUtils.success(request.getSession().getAttribute("menuList"));
        }
        List<SysMenuDto> dtoList = listByPid(id, grade);
        request.getSession().setAttribute("menuList", dtoList);
        return ResultUtils.success(dtoList);
    }

    private List<SysMenuDto> listByPid(Integer pid,Integer grade) {
        List<SysMenuDto> dtoList = new ArrayList<>();
        SysMenuDto dto;
        SysMenuPo po = new SysMenuPo();
        po.setParentId(pid);
        po.setGrade(grade);
        po.addOrderBy(Order.asc("order_no"));
        //根节点
        List<SysMenuPo> list = service.listMenu(po);
        if (CollectionUtils.isNotEmpty(list)) {
            for (SysMenuPo menuPo : list) {
                dto = new SysMenuDto();
                //添加字段属性
                BeanUtils.copyProperties(menuPo, dto);
                dto.setId(menuPo.getMenuId());
                //查看子节点
                List<SysMenuDto> subDtoList = listByPid(menuPo.getId(),grade);
                dto.setChildren(subDtoList);
                dtoList.add(dto);
            }
        }

        return dtoList;
    }
}