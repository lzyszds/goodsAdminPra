package com.example.demo005.service;

import com.example.demo005.domain.Goods;

public interface GoodsService {
    int insertGoods(Goods goods);

    int updateGoods(Goods goods);

    Goods[] querySearchGoods(String search);

    int deleteGoods(int g_id);
}
