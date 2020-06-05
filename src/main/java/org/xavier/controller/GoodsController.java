package org.xavier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.xavier.dao.GoodsMapper;
import org.xavier.service.GoodsServiceImpl;

import java.util.ArrayList;

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
    private GoodsMapper goodsMapper;
    @Autowired
    private GoodsServiceImpl goodsService;

    @GetMapping(value = "/goods/{goodsId}")
    public Object queryArticleSummary(@PathVariable("goodsId") String goodsId) {
        return goodsMapper.selectById(goodsId);
    }

    @GetMapping(value = "/goods/reduce/{goodsIdList}")
    public Object reduceStuck(@PathVariable("goodsIdList") ArrayList<Integer> goodsIdList)  {
        return goodsService.mockTransactionRollback(goodsIdList.get(0), goodsIdList.get(1));
    }


}