package com.atguigu.gmall.pms.service.impl;

import com.atguigu.core.bean.PageVo;
import com.atguigu.core.bean.Query;
import com.atguigu.core.bean.QueryCondition;
import com.atguigu.gmall.pms.dao.AttrAttrgroupRelationDao;
import com.atguigu.gmall.pms.dao.AttrDao;
import com.atguigu.gmall.pms.entity.AttrAttrgroupRelationEntity;
import com.atguigu.gmall.pms.entity.AttrEntity;
import com.atguigu.gmall.pms.service.AttrService;
import com.atguigu.gmall.pms.vo.AttrVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {
    @Resource
    private AttrAttrgroupRelationDao relationDao;
    @Override
    public PageVo queryPage(QueryCondition params) {
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                new QueryWrapper<AttrEntity>()
        );

        return new PageVo(page);
    }

    @Override
    public PageVo queryAttrByCidOrTypePage(QueryCondition condition, Long cid, Integer type) {
        QueryWrapper<AttrEntity> wrapper = new QueryWrapper<>();
        if (type != null) {
            wrapper.eq("attr_type",type);
        }
        wrapper.eq("catelog_id",cid);

        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(condition),
                wrapper
        );

        return new PageVo(page);
    }

    @Override
    public void saveAttrVO(AttrVO attrVO) {
        //新增attr
        this.save(attrVO);
        Long attrId = attrVO.getAttrId();


        //新增relation
        AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
        relationEntity.setAttrGroupId(attrVO.getAttrGroupId());
        relationEntity.setAttrId(attrId);
        relationDao.insert(relationEntity);
    }

}