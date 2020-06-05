package org.xavier.service;

import org.xavier.dao.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private GoodsMapper goodsMapper;
}