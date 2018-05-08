package cn.edu.hebau.exam.service.impl;

import cn.edu.hebau.exam.po.SysMenuPo;
import cn.edu.hebau.exam.service.SysMenuService;
import com.fesine.service.impl.BaseServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 菜单实现类
 * @author: Fesine
 * @createTime:2017/10/14 16:15
 * @update:修改内容
 * @author: Fesine
 * @updateTime:2017/10/14 16:15
 */
@Service
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenuPo> implements SysMenuService {
    @Override
    public int save(SysMenuPo po) {
        SysMenuPo parPo = new SysMenuPo();
        parPo.setId(po.getParentId());
        parPo = daoService.selectOne(parPo);
        if (parPo != null) {
            //父节点是叶子节点，需要更新为非叶子节点
            if (parPo.getLeaf()) {
                parPo.setLeaf(false);
                daoService.update(parPo);
            }
        }
        return daoService.insert(po);
    }


    @Override
    public int delete(SysMenuPo po) {
        //删除菜单时，需要先删除子节点
        SysMenuPo poTemp = new SysMenuPo();
        poTemp.setId(po.getId());
        poTemp = daoService.selectOne(poTemp);
        if (poTemp == null) {
            return 0;
        }
        Integer pid = poTemp.getParentId();
        //判断菜单是否是叶子
        if (!poTemp.getLeaf()){
            this.deleteByPid(poTemp.getId());
        }
        int i = daoService.delete(po);
        //同时判断父节点是否有子节点，如果没有，则更新父节点为叶子节点
        poTemp = new SysMenuPo();
        poTemp.setParentId(pid);
        if (daoService.count(poTemp) == 0) {
            poTemp = new SysMenuPo();
            poTemp.setId(pid);
            poTemp.setLeaf(true);
            daoService.update(poTemp);
        }
        return i;
    }



    @Override
    public List<SysMenuPo> listMenu(SysMenuPo po) {
        return daoService.selectList(SysMenuPo.class.getName()+".selectListAll",po);
    }

    private void deleteByPid(Integer pid) {
        SysMenuPo po = new SysMenuPo();
        po.setParentId(pid);
        List<SysMenuPo> list = this.listAll(po);
        //有子节点，则先删除子节点
        if (CollectionUtils.isNotEmpty(list)) {
            for (SysMenuPo menuPo : list) {
                deleteByPid(menuPo.getParentId());
            }
        }
        //删除自己
        po = new SysMenuPo();
        po.setId(pid);
        daoService.delete(po);
    }
}
