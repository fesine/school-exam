package cn.edu.hebau.exam.po;

import com.fesine.dao.BasePo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


/**
 * @description: t_exam_score多表关联对应的实体类
 * @author: Fesine
 * @createTime:2018/05/08
 * @update:修改内容
 * @author: Fesine
 * @updateTime:2018/05/08
 */
@Data
public class ExamScoreUnionPo extends BasePo {
	private static final long serialVersionUID = 1L;
	private Integer id;
	/**
	 * 考试编号       db_column: exam_id
	 */
	private Integer examId;
	/**
	 * 学号       db_column: stu_no
	 */
	private String stuNo;
	/**
	 * 学生姓名       db_column: stu_name
	 */
	private String stuName;
	/**
	 * 成绩       db_column: score
	 */
	private Integer score;
	/**
	 * 考试年级id       db_column: grade_id
	 */
	private Integer gradeId;
	/**
	 * 班级号       db_column: classroom_id
	 */
	private Integer classroomId;
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
	/**
	 * 年级名称
	 */
	private String gradeName;
	/**
	 * 班级名称       db_column: classroom_name
	 */
	private String classroomName;
	/**
	 * 课程名称
	 */
	private String courseName;
	/**
	 * 语文成绩
	 */
	private Integer chinese;
	/**
	 * 数学成绩
	 */
	private Integer math;
	/**
	 * 英语成绩
	 */
	private Integer english;
	/**
	 * 物理成绩
	 */
	private Integer physics;
	/**
	 * 化学成绩
	 */
	private Integer chemistry;
	/**
	 * 总成绩
	 */
	private int sum;
	/**
	 * 平均成绩
	 */
	private BigDecimal avg;

	/**
	 * 排名
	 */
	private int rowNo;

	

}

