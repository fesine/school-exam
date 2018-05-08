package cn.edu.hebau.exam.dto;

import lombok.Data;

import java.util.List;

/**
 * @description: 类描述
 * @author: Fesine
 * @createTime:2017/10/14 17:30
 * @update:修改内容
 * @author: Fesine
 * @updateTime:2017/10/14 17:30
 */
@Data
public class SysMenuDto {

    /**
     * 菜单编号       db_column: id
     */
    private String id;
    /**
     * 菜单文本       db_column: text
     */
    private String text;
    /**
     * 是否是叶子       db_column: leaf
     */
    private Boolean leaf;
    /**
     * 父节点编号 默认0 根结点       db_column: parent_id
     */
    private Integer parentId;
    /**
     * 视图别名       db_column: fun_view_xtype
     */
    private String funViewXtype;
    /**
     * 控制器类名       db_column: fun_controller
     */
    private String funController;
    /**
     * 视图类名       db_column: fun_view_name
     */
    private String funViewName;
    /**
     * 图标       db_column: icon_cls
     */
    private String iconCls;
    /**
     * 跳转链接       db_column: url
     */
    private String url;
    /**
     * 权限级别 0登录权限，1管理员权限，2系统管理员权限       db_column: grade
     */
    private Integer grade;

    private List<SysMenuDto> children;

}
