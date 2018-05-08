package cn.edu.hebau.exam.po;
import com.fesine.dao.BasePo;
import java.util.Date;

/**
 * @description: t_exam表对应的实体类
 * @author: Fesine
 * @createTime:2018/05/08
 * @update:修改内容
 * @author: Fesine
 * @updateTime:2018/05/08
 */
public class ExamPo extends BasePo {
	private static final long serialVersionUID = 1L;
	//alias
	public static final String TABLE_ALIAS = "Exam";
	public static final String ALIAS_ID = "主键";
	public static final String ALIAS_EXAM_TYPE = "考试类型 1:闭卷 2:开卷";
	public static final String ALIAS_GRADE_ID = "考试年级id";
	public static final String ALIAS_COURSE_ID = "考试课程";
	public static final String ALIAS_START_TIME = "开始时间";
	public static final String ALIAS_END_TIME = "结束时间";
	

	//columns START
    /**
     * 主键       db_column: id 
     */ 	
	private Integer id;
    /**
     * 考试类型 1:闭卷 2:开卷       db_column: exam_type 
     */ 	
	private Integer examType;
    /**
     * 考试年级id       db_column: grade_id 
     */ 	
	private Integer gradeId;
    /**
     * 考试课程       db_column: course_id 
     */ 	
	private Integer courseId;
    /**
     * 开始时间       db_column: start_time 
     */ 	
	private Date startTime;
    /**
     * 结束时间       db_column: end_time 
     */ 	
	private Date endTime;
	//columns END

	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	
	public Integer getExamType() {
		return this.examType;
	}
	
	public void setExamType(Integer examType) {
		this.examType = examType;
	}

	
	public Integer getGradeId() {
		return this.gradeId;
	}
	
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	
	public Integer getCourseId() {
		return this.courseId;
	}
	
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	
	public Date getStartTime() {
		return this.startTime;
	}
	
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	
	public Date getEndTime() {
		return this.endTime;
	}
	
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}


	

}

