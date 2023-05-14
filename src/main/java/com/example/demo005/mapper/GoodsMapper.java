package com.example.demo005.mapper;

import com.example.demo005.domain.Goods;
import org.apache.ibatis.annotations.*;

@Mapper
public interface GoodsMapper {
    //商品sql

    //模糊 查询所有商品
    @Select("SELECT * FROM goods_list WHERE g_id like '${search}' or g_titleName like '${search}' or g_goodsType like '${search}'")
    public Goods[] querySearchGoods(String search);

    //修改商品信息
    @Update("UPDATE goods_list SET g_titleName=#{goods.g_titleName},g_price=#{goods.g_price},g_number=#{goods.g_number},g_goodsType=#{goods.g_goodsType},g_picture=#{goods.g_picture} where g_id=#{goods.g_id}")
    public int updateGoods(@Param("goods") Goods goods);

    //添加商品
    @Insert("INSERT INTO goods_list(g_titleName,g_price,g_createTime,g_number,g_goodsType,g_status,g_picture) VALUES (#{goods.g_titleName},#{goods.g_price},#{goods.g_createTime},#{goods.g_number},#{goods.g_goodsType},#{goods.g_status},#{goods.g_picture})")
    public int insertGoods(@Param("goods") Goods goods);

    //删除商品
    @Delete("DELETE FROM goods_list WHERE g_id = #{g_id}")
    public int deleteGoods(int g_id);
}
