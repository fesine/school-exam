package cn.edu.hebau.exam.po;
import com.fesine.dao.BasePo;


/**
 * @description: t_sys_code表对应的实体类
 * @author: Fesine
 * @createTime:2017/10/25
 * @update:修改内容
 * @author: Fesine
 * @updateTime:2017/10/25
 */
public class SysCodePo extends BasePo {
	private static final long serialVersionUID = 1L;
	//alias
	public static final String TABLE_ALIAS = "SysCode";
	public static final String ALIAS_ID = "主键";
	public static final String ALIAS_CODE_ID = "代码编号";
	public static final String ALIAS_VALUE = "代码值";
	public static final String ALIAS_TEXT = "代码文本";
	public static final String ALIAS_LEAF = "是否是叶子";
	public static final String ALIAS_PARENT_ID = "父节点编号 默认0 根结点";
	public static final String ALIAS_GRADE = "权限级别 0登录权限，1管理员权限，2系统管理员权限";
	public static final String ALIAS_ORDER_NO = "排序字段";
	public static final String ALIAS_REMARK1 = "备用字段1";
	

	//columns START
    /**
     * 主键       db_column: id 
     */ 	
	private Integer id;
    /**
     * 代码编号       db_column: code_id 
     */ 	
	private String codeId;
    /**
     * 代码值       db_column: value 
     */ 	
	private String value;
    /**
     * 代码文本       db_column: text 
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
     * 权限级别 0登录权限，1管理员权限，2系统管理员权限       db_column: grade 
     */ 	
	private Integer grade;
    /**
     * 排序字段       db_column: order_no 
     */ 	
	private Integer orderNo;
    /**
     * 备用字段1       db_column: remark1 
     */ 	
	private String remark1;
	//columns END

	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getCodeId() {
		return this.codeId;
	}
	
	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	
	public String getValue() {
		return this.value;
	}
	
	public void setValue(String value) {
		this.value = value;
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

	
	public Integer getGrade() {
		return this.grade;
	}
	
	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	
	public Integer getOrderNo() {
		return this.orderNo;
	}
	
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	
	public String getRemark1() {
		return this.remark1;
	}
	
	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}


	

}

