package com.kevin.mirror.DB;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 16/6/20.
 */
public class DBUtils {
    private LiteOrm liteOrm=LiteOrmSington.getInstance();
    //条件查询
    public <T>ArrayList<T> showQuery(Class<T> clazz, String where, String value){

        QueryBuilder<T> list=new QueryBuilder<>(clazz).whereEquals(where,value);

        return liteOrm.query(list);
    }
    //查询全部
    public <T>List<T> queryAll(Class<T> clazz){

        List<T> list=liteOrm.query(clazz);

        return list;
    }
    //插入
    public void insertDB(Object o) {
        liteOrm.insert(o);
    }
    //删除单个
    public <T>void delete(List<T> o) {
        liteOrm.delete(o);
    }
    //删除全部
    public <T>void deleteAll(Class<T> clazz){
        liteOrm.delete(clazz);
    }
    //update
    public void updateQuery(String goodsId,String goodsUrl,String name,
                          String price,String area,String brand  ){
        QueryBuilder list=new QueryBuilder<>(DbBean.class).whereEquals(DbBean.GOODID,goodsId);
        if (liteOrm.query(list).size()>0){
            liteOrm.delete(liteOrm.query(list));
            insertDB(new DbBean(goodsId,goodsUrl,name,price,area,brand));
        }else {
            insertDB(new DbBean(goodsId,goodsUrl,name,price,area,brand));
        }
    }
}
