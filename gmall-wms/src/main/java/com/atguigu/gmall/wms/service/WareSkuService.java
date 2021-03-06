package com.atguigu.gmall.wms.service;

import com.atguigu.core.bean.PageVo;
import com.atguigu.core.bean.QueryCondition;
import com.atguigu.gmall.wms.entity.WareSkuEntity;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 商品库存
 *
 * @author lby
 * @email lxf@atguigu.com
 * @date 2020-01-02 16:15:15
 */
public interface WareSkuService extends IService<WareSkuEntity> {

    PageVo queryPage(QueryCondition params);


}

