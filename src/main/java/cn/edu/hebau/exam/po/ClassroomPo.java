package cn.edu.hebau.exam.po;
import com.fesine.dao.BasePo;


/**
 * @description: t_classroom表对应的实体类
 * @author: Fesine
 * @createTime:2018/05/08
 * @update:修改内容
 * @author: Fesine
 * @updateTime:2018/05/08
 */
public class ClassroomPo extends BasePo {
	private static final long serialVersionUID = 1L;
	//alias
	public static final String TABLE_ALIAS = "Classroom";
	public static final String ALIAS_ID = "主键";
	public static final String ALIAS_CLASSROOM_NAME = "班级名称";
	

	//columns START
    /**
     * 主键       db_column: id 
     */ 	
	private Integer id;
    /**
     * 班级名称       db_column: classroom_name 
     */ 	
	private String classroomName;
	//columns END

	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getClassroomName() {
		return this.classroomName;
	}
	
	public void setClassroomName(String classroomName) {
		this.classroomName = classroomName;
	}


	

}

