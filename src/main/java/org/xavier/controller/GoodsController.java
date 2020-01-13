package org.xavier.controller;

import org.xavier.dao.GoodsMapper;
import org.xavier.domain.po.Goods;
import org.xavier.service.GoodsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述信息：<br/>
 *
 * @author Xavier
 * @version 1.0
 * @date 20-1-13
 * @since Jdk 1.8
 */
@RestController
public class GoodsController {
    @Autowired
    private GoodsServiceImpl goodsService;
    @Autowired
    private GoodsMapper goodsMapper;

    @GetMapping(value = "/goods/{goodsId}")
    public Object queryArticleSummary(@PathVariable("goodsId") String goodsId) {
        return goodsMapper.selectById(goodsId);
    }

    @GetMapping(value = "/goods/reduce/{goodsId}")
    public Object reduceStuck(@PathVariable("goodsId") Integer goodsId) {
        Goods currentGoods = goodsMapper.selectById(goodsId);
        return goodsMapper.reduceStockById(goodsId, currentGoods.getCurrentStock());
    }
}