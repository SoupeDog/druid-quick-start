package org.xavier.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.xavier.domain.po.Goods;


/**
 * 描述信息：<br/>
 *
 * @author Xavier
 * @version 1.0
 * @date 2019/9/2
 * @since Jdk 1.8
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {
    /**
     * 为特定商品减少库存
     *
     * @param goodsId      商品唯一标识
     * @param currentStock 当前库存
     * @return 受影响行
     */
    @Update("UPDATE goods SET currentStock=currentStock-1 WHERE goodsId=#{goodsId} AND currentStock=#{currentStock}")
    Integer reduceStockById(@Param("goodsId") Integer goodsId, @Param("currentStock") Integer currentStock);
}