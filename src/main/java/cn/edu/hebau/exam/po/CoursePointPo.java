package cn.edu.hebau.exam.po;
import com.fesine.dao.BasePo;


/**
 * @description: t_course_point表对应的实体类
 * @author: Fesine
 * @createTime:2018/05/08
 * @update:修改内容
 * @author: Fesine
 * @updateTime:2018/05/08
 */
public class CoursePointPo extends BasePo {
	private static final long serialVersionUID = 1L;
	//alias
	public static final String TABLE_ALIAS = "CoursePoint";
	public static final String ALIAS_ID = "主键";
	public static final String ALIAS_COURSE_ID = "课程号";
	public static final String ALIAS_COURSE_POINT = "知识点";
	

	//columns START
    /**
     * 主键       db_column: id 
     */ 	
	private Integer id;
    /**
     * 课程号       db_column: course_id 
     */ 	
	private Integer courseId;
    /**
     * 知识点       db_column: course_point 
     */ 	
	private String coursePoint;
	//columns END

	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	
	public Integer getCourseId() {
		return this.courseId;
	}
	
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	
	public String getCoursePoint() {
		return this.coursePoint;
	}
	
	public void setCoursePoint(String coursePoint) {
		this.coursePoint = coursePoint;
	}


	

}

