package cn.edu.hebau.exam.service.impl;

import cn.edu.hebau.exam.po.ExamScoreUnionPo;
import cn.edu.hebau.exam.service.ExamScoreUnionService;
import com.fesine.dao.model.QueryResult;
import com.fesine.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 类描述
 * @author: Fesine
 * @createTime:2018/5/10
 * @update:修改内容
 * @author: Fesine
 * @updateTime:2018/5/10
 */
@Service
public class ExamScoreUnionServiceImpl extends BaseServiceImpl<ExamScoreUnionPo> implements ExamScoreUnionService {
    @Override
    public QueryResult<ExamScoreUnionPo> selectListByStuNo(String mapper, ExamScoreUnionPo po,
                                                           Integer pageIndex, Integer pageSize) {
        return daoService.selectQueryResult(mapper,po,pageIndex,pageSize);
    }

    @Override
    public List<ExamScoreUnionPo> selectListByStuNo(String mapper, ExamScoreUnionPo po) {
        return daoService.selectList(mapper,po);
    }
}
