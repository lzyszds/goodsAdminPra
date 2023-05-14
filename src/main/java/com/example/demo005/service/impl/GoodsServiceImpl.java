package com.example.demo005.service.impl;

import com.example.demo005.domain.Goods;
import com.example.demo005.mapper.GoodsMapper;
import com.example.demo005.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public int insertGoods(Goods goods) {
        return goodsMapper.insertGoods(goods);
    }

    @Override
    public int updateGoods(Goods goods) {
        return goodsMapper.updateGoods(goods);
    }

    @Override
    public Goods[] querySearchGoods(String search) {
        return goodsMapper.querySearchGoods(search);
    }

    @Override
    public int deleteGoods(int g_id) {
        return goodsMapper.deleteGoods(g_id);
    }
}
