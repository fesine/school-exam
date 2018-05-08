package cn.edu.hebau.exam.po;
import com.fesine.dao.BasePo;
import java.util.Date;

/**
 * @description: t_sys_user表对应的实体类
 * @author: Fesine
 * @createTime:2018/05/08
 * @update:修改内容
 * @author: Fesine
 * @updateTime:2018/05/08
 */
public class SysUserPo extends BasePo {
	private static final long serialVersionUID = 1L;
	//alias
	public static final String TABLE_ALIAS = "SysUser";
	public static final String ALIAS_ID = "主键";
	public static final String ALIAS_USERNAME = "用户名";
	public static final String ALIAS_PASSWORD = "密码";
	public static final String ALIAS_NICK_NAME = "昵称";
	public static final String ALIAS_TYPE = "权限类型  0: 普通管理员 1:权限管理员  2:超级管理员";
	public static final String ALIAS_EMAIL = "邮箱";
	public static final String ALIAS_CELL = "手机号码";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_LAST_UPDATE_TIME = "最后更新时间";
	

	//columns START
    /**
     * 主键       db_column: id 
     */ 	
	private Integer id;
    /**
     * 用户名       db_column: username 
     */ 	
	private String username;
    /**
     * 密码       db_column: password 
     */ 	
	private String password;
    /**
     * 昵称       db_column: nickName 
     */ 	
	private String nickName;
    /**
     * 权限类型  0: 普通管理员 1:权限管理员  2:超级管理员       db_column: type 
     */ 	
	private Integer type;
    /**
     * 邮箱       db_column: email 
     */ 	
	private String email;
    /**
     * 手机号码       db_column: cell 
     */ 	
	private String cell;
    /**
     * 创建时间       db_column: create_time 
     */ 	
	private Date createTime;
    /**
     * 最后更新时间       db_column: last_update_time 
     */ 	
	private Date lastUpdateTime;
	//columns END

	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getNickName() {
		return this.nickName;
	}
	
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	
	public Integer getType() {
		return this.type;
	}
	
	public void setType(Integer type) {
		this.type = type;
	}

	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	
	public String getCell() {
		return this.cell;
	}
	
	public void setCell(String cell) {
		this.cell = cell;
	}

	
	public Date getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	
	public Date getLastUpdateTime() {
		return this.lastUpdateTime;
	}
	
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}


	

}

