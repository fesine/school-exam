package cn.edu.hebau.exam.po;
import com.fesine.dao.BasePo;


/**
 * @description: t_grade表对应的实体类
 * @author: Fesine
 * @createTime:2018/05/08
 * @update:修改内容
 * @author: Fesine
 * @updateTime:2018/05/08
 */
public class GradePo extends BasePo {
	private static final long serialVersionUID = 1L;
	//alias
	public static final String TABLE_ALIAS = "Grade";
	public static final String ALIAS_ID = "主键";
	public static final String ALIAS_GRADE_NAME = "年级名称";
	

	//columns START
    /**
     * 主键       db_column: id 
     */ 	
	private Integer id;
    /**
     * 年级名称       db_column: grade_name 
     */ 	
	private String gradeName;
	//columns END

	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getGradeName() {
		return this.gradeName;
	}
	
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}


	

}

