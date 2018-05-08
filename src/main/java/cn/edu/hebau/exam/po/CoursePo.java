package cn.edu.hebau.exam.po;
import com.fesine.dao.BasePo;


/**
 * @description: t_course表对应的实体类
 * @author: Fesine
 * @createTime:2018/05/08
 * @update:修改内容
 * @author: Fesine
 * @updateTime:2018/05/08
 */
public class CoursePo extends BasePo {
	private static final long serialVersionUID = 1L;
	//alias
	public static final String TABLE_ALIAS = "Course";
	public static final String ALIAS_ID = "主键";
	public static final String ALIAS_GRADE_ID = "年级号";
	public static final String ALIAS_COURSE_NAME = "课程名称";
	

	//columns START
    /**
     * 主键       db_column: id 
     */ 	
	private Integer id;
    /**
     * 年级号       db_column: grade_id 
     */ 	
	private Integer gradeId;
    /**
     * 课程名称       db_column: course_name 
     */ 	
	private String courseName;
	//columns END

	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	
	public Integer getGradeId() {
		return this.gradeId;
	}
	
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	
	public String getCourseName() {
		return this.courseName;
	}
	
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}


	

}

