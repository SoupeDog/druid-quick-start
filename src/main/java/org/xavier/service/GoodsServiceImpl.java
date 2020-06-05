package org.xavier.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xavier.dao.GoodsMapper;
import org.xavier.domain.po.Goods;

/**
 * 描述信息：<br/>
 *
 * @author Xavier
 * @version 1.0
 * @date 20-1-13
 * @since Jdk 1.8
 */
@Service
public class GoodsServiceImpl {
    @Autowired
    GoodsMapper goodsMapper;

    @Transactional
    public Boolean mockTransactionRollback(Integer goodsId1, Integer goodsId2) {
        Boolean resultFlag = reduceGoodsByGoodsId(goodsId1);
        if (!resultFlag) {
            throw new RuntimeException("减少库存失败 goodsId1:" + goodsId1);
        }
        resultFlag = reduceGoodsByGoodsId(goodsId2);
        if (!resultFlag) {
            throw new RuntimeException("减少库存失败 goodsId2:" + goodsId2);
        }
        return resultFlag;
    }

    public Boolean reduceGoodsByGoodsId(Integer goodsId) {
        Goods goods = goodsMapper.selectById(goodsId);
        if (goods == null) {
            throw new RuntimeException("Goods(" + goodsId + ") was not found.");
        }
        Integer affectedRow = goodsMapper.reduceStockById(goodsId, goods.getCurrentStock());
        Boolean result = affectedRow == 1;
        return result;
    }


}