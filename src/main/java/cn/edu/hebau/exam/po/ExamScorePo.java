package cn.edu.hebau.exam.po;
import com.fesine.dao.BasePo;


/**
 * @description: t_exam_score表对应的实体类
 * @author: Fesine
 * @createTime:2018/05/08
 * @update:修改内容
 * @author: Fesine
 * @updateTime:2018/05/08
 */
public class ExamScorePo extends BasePo {
	private static final long serialVersionUID = 1L;
	//alias
	public static final String TABLE_ALIAS = "ExamScore";
	public static final String ALIAS_ID = "主键";
	public static final String ALIAS_STU_NO = "学号";
	public static final String ALIAS_EXAM_ID = "考试编号";
	public static final String ALIAS_SCORE = "成绩";
	

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
     * 考试编号       db_column: exam_id 
     */ 	
	private Integer examId;
    /**
     * 成绩       db_column: score 
     */ 	
	private Integer score;
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

	
	public Integer getExamId() {
		return this.examId;
	}
	
	public void setExamId(Integer examId) {
		this.examId = examId;
	}

	
	public Integer getScore() {
		return this.score;
	}
	
	public void setScore(Integer score) {
		this.score = score;
	}


	

}

