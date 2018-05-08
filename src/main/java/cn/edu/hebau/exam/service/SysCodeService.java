package cn.edu.hebau.exam.service;

import cn.edu.hebau.exam.po.SysCodePo;
import com.fesine.service.IBaseService;

import java.util.List;

/**
 * @description: 系统代码接口类
 * @author: Fesine
 * @createTime:2017/10/14 16:12
 * @update:修改内容
 * @author: Fesine
 * @updateTime:2017/10/14 16:12
 */
public interface SysCodeService extends IBaseService<SysCodePo> {
    List<SysCodePo> listCode(SysCodePo po);
}
