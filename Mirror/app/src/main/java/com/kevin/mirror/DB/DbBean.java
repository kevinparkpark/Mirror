package com.kevin.mirror.DB;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by kevin on 16/6/25.
 */
public class DbBean {
    public static final String GOODID= "goodId";
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    int id;
    @Column(GOODID)
    String goodsId;
    String goodsUrl;
    String goodsName,goodsPrice,productArea,brand;

    public DbBean(String goodsId, String goodsUrl, String goodsName, String goodsPrice,
                  String productArea, String brand) {
        this.goodsId = goodsId;
        this.goodsUrl = goodsUrl;
        this.goodsName = goodsName;
        this.goodsPrice = goodsPrice;
        this.productArea = productArea;
        this.brand = brand;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsUrl() {
        return goodsUrl;
    }

    public void setGoodsUrl(String goodsUrl) {
        this.goodsUrl = goodsUrl;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getProductArea() {
        return productArea;
    }

    public void setProductArea(String productArea) {
        this.productArea = productArea;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
