package cn.edu.hebau.exam.service;

import cn.edu.hebau.exam.po.ExamScoreUnionPo;
import com.fesine.dao.model.QueryResult;
import com.fesine.service.IBaseService;

import java.util.List;

/**
 * @description: t_exam_score表对应的服务接口
 * @author: Fesine
 * @createTime:2018/05/08
 * @update:修改内容
 * @author: Fesine
 * @updateTime:2018/05/08
 */
public interface ExamScoreUnionService extends IBaseService<ExamScoreUnionPo> {

    /**
     * 学生成绩汇总
     * @param mapper
     * @param po
     * @param pageIndex
     * @param pageSize
     * @return
     */
    QueryResult<ExamScoreUnionPo> selectListByStuNo(String mapper, ExamScoreUnionPo po, Integer
            pageIndex, Integer pageSize);

    /**
     * 获取当前年级所有成绩信息
     * @param mapper
     * @param po
     * @return
     */
    List<ExamScoreUnionPo> selectListByStuNo(String mapper, ExamScoreUnionPo po);

}

