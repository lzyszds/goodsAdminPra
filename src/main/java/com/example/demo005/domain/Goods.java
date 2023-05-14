package com.example.demo005.domain;

import com.example.demo005.utill.DateTimeUtil;

import java.util.Date;

public class Goods {
    private int g_id;
    private String g_titleName;
    private double g_price;
    private int g_number;
    private int g_goodsType;

    private int g_status;
    private String g_picture;
    private Date g_createTime;

    public int getG_id() {
        return g_id;
    }

    public void setG_id(int g_id) {
        this.g_id = g_id;
    }

    public String getG_titleName() {
        return g_titleName;
    }

    public void setG_titleName(String g_titleName) {
        this.g_titleName = g_titleName;
    }

    public double getG_price() {
        return g_price;
    }

    public void setG_price(double g_price) {
        this.g_price = g_price;
    }

    public int getG_number() {
        return g_number;
    }

    public void setG_number(int g_number) {
        this.g_number = g_number;
    }

    public int getG_goodsType() {
        return g_goodsType;
    }

    public void setG_goodsType(int g_goodsType) {
        this.g_goodsType = g_goodsType;
    }

    public int getG_status() {
        return g_status;
    }

    public void setG_status(int g_status) {
        this.g_status = g_status;
    }

    public String getG_picture() {
        return g_picture;
    }

    public void setG_picture(String g_picture) {
        this.g_picture = g_picture;
    }

    public String getG_createTime() {
        return DateTimeUtil.dateToStr(g_createTime);
    }

    public void setG_createTime(Date g_createTime) {
        this.g_createTime = g_createTime;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "g_id=" + g_id +
                ", g_titleName='" + g_titleName + '\'' +
                ", g_price=" + g_price +
                ", g_number=" + g_number +
                ", g_goodsType=" + g_goodsType +
                ", g_status=" + g_status +
                ", g_picture='" + g_picture + '\'' +
                ", g_createTime=" + g_createTime +
                '}';
    }
}

