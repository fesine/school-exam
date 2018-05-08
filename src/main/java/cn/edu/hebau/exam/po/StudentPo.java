package cn.edu.hebau.exam.po;
import com.fesine.dao.BasePo;
import java.util.Date;

/**
 * @description: t_student表对应的实体类
 * @author: Fesine
 * @createTime:2018/05/08
 * @update:修改内容
 * @author: Fesine
 * @updateTime:2018/05/08
 */
public class StudentPo extends BasePo {
	private static final long serialVersionUID = 1L;
	//alias
	public static final String TABLE_ALIAS = "Student";
	public static final String ALIAS_ID = "主键";
	public static final String ALIAS_STU_NO = "学号";
	public static final String ALIAS_STU_NAME = "学生姓名";
	public static final String ALIAS_STU_SEX = "性别 0:未知 1:男 2:女";
	public static final String ALIAS_STU_BIRTHDAY = "出生日期 1990-01-10";
	public static final String ALIAS_STU_CELL = "手机号码";
	public static final String ALIAS_STU_ADDRESS = "地址信息";
	public static final String ALIAS_STU_EMAIL = "电子邮箱";
	public static final String ALIAS_GRADE_ID = "年级号";
	public static final String ALIAS_CLASSROOM_ID = "班级号";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_LAST_UPDATE_TIME = "最后更新时间";
	

	//columns START
    /**
     * 主键       db_column: id 
     */ 	
	private Integer id;
    /**
     * 学号       db_column: stu_no 
     */ 	
	private String stuNo;
    /**
     * 学生姓名       db_column: stu_name 
     */ 	
	private String stuName;
    /**
     * 性别 0:未知 1:男 2:女       db_column: stu_sex 
     */ 	
	private Integer stuSex;
    /**
     * 出生日期 1990-01-10       db_column: stu_birthday 
     */ 	
	private Date stuBirthday;
    /**
     * 手机号码       db_column: stu_cell 
     */ 	
	private String stuCell;
    /**
     * 地址信息       db_column: stu_address 
     */ 	
	private String stuAddress;
    /**
     * 电子邮箱       db_column: stu_email 
     */ 	
	private String stuEmail;
    /**
     * 年级号       db_column: grade_id 
     */ 	
	private Integer gradeId;
    /**
     * 班级号       db_column: classroom_id 
     */ 	
	private Integer classroomId;
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

	
	public String getStuNo() {
		return this.stuNo;
	}
	
	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}

	
	public String getStuName() {
		return this.stuName;
	}
	
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	
	public Integer getStuSex() {
		return this.stuSex;
	}
	
	public void setStuSex(Integer stuSex) {
		this.stuSex = stuSex;
	}

	
	public Date getStuBirthday() {
		return this.stuBirthday;
	}
	
	public void setStuBirthday(Date stuBirthday) {
		this.stuBirthday = stuBirthday;
	}

	
	public String getStuCell() {
		return this.stuCell;
	}
	
	public void setStuCell(String stuCell) {
		this.stuCell = stuCell;
	}

	
	public String getStuAddress() {
		return this.stuAddress;
	}
	
	public void setStuAddress(String stuAddress) {
		this.stuAddress = stuAddress;
	}

	
	public String getStuEmail() {
		return this.stuEmail;
	}
	
	public void setStuEmail(String stuEmail) {
		this.stuEmail = stuEmail;
	}

	
	public Integer getGradeId() {
		return this.gradeId;
	}
	
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	
	public Integer getClassroomId() {
		return this.classroomId;
	}
	
	public void setClassroomId(Integer classroomId) {
		this.classroomId = classroomId;
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

