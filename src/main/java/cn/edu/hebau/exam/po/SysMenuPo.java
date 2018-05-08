package cn.edu.hebau.exam.po;
import com.fesine.dao.BasePo;


/**
 * @description: t_sys_menu表对应的实体类
 * @author: Fesine
 * @createTime:2017/10/15
 * @update:修改内容
 * @author: Fesine
 * @updateTime:2017/10/15
 */
public class SysMenuPo extends BasePo {
	private static final long serialVersionUID = 1L;
	//alias
	public static final String TABLE_ALIAS = "SysMenu";
	public static final String ALIAS_ID = "主键";
	public static final String ALIAS_MENU_ID = "菜单编号";
	public static final String ALIAS_TEXT = "菜单文本";
	public static final String ALIAS_LEAF = "是否是叶子";
	public static final String ALIAS_PARENT_ID = "父节点编号 默认0 根结点";
	public static final String ALIAS_ORDER_NO = "排序字段，默认100";
	public static final String ALIAS_GRADE = "权限级别 0登录权限，1管理员权限，2系统管理员权限";
	public static final String ALIAS_FUN_VIEW_XTYPE = "视图别名";
	public static final String ALIAS_FUN_CONTROLLER = "控制器类名";
	public static final String ALIAS_FUN_VIEW_NAME = "视图类名";
	public static final String ALIAS_ICON_CLS = "图标";
	public static final String ALIAS_URL = "跳转链接";
	public static final String ALIAS_REMARK1 = "备用字段1";
	public static final String ALIAS_REMARK2 = "备用字段2";
	public static final String ALIAS_REMARK3 = "备用字段3";
	

	//columns START
    /**
     * 主键       db_column: id 
     */ 	
	private Integer id;
    /**
     * 菜单编号       db_column: menu_id 
     */ 	
	private String menuId;
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
     * 排序字段，默认100       db_column: order_no 
     */ 	
	private Integer orderNo;
    /**
     * 权限级别 0登录权限，1管理员权限，2系统管理员权限       db_column: grade 
     */ 	
	private Integer grade;
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
     * 备用字段1       db_column: remark1 
     */ 	
	private String remark1;
    /**
     * 备用字段2       db_column: remark2 
     */ 	
	private String remark2;
    /**
     * 备用字段3       db_column: remark3 
     */ 	
	private String remark3;
	//columns END

	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getMenuId() {
		return this.menuId;
	}
	
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	
	public String getText() {
		return this.text;
	}
	
	public void setText(String text) {
		this.text = text;
	}

	
	public Boolean getLeaf() {
		return this.leaf;
	}
	
	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}

	
	public Integer getParentId() {
		return this.parentId;
	}
	
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	
	public Integer getOrderNo() {
		return this.orderNo;
	}
	
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	
	public Integer getGrade() {
		return this.grade;
	}
	
	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	
	public String getFunViewXtype() {
		return this.funViewXtype;
	}
	
	public void setFunViewXtype(String funViewXtype) {
		this.funViewXtype = funViewXtype;
	}

	
	public String getFunController() {
		return this.funController;
	}
	
	public void setFunController(String funController) {
		this.funController = funController;
	}

	
	public String getFunViewName() {
		return this.funViewName;
	}
	
	public void setFunViewName(String funViewName) {
		this.funViewName = funViewName;
	}

	
	public String getIconCls() {
		return this.iconCls;
	}
	
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	
	public String getUrl() {
		return this.url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

	
	public String getRemark1() {
		return this.remark1;
	}
	
	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	
	public String getRemark2() {
		return this.remark2;
	}
	
	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	
	public String getRemark3() {
		return this.remark3;
	}
	
	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}


	

}

