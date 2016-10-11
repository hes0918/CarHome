package com.lanou3g.dllo.athm.model.db;

import android.util.Log;

import com.lanou3g.dllo.athm.controler.app.CarApp;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;

import java.util.List;

/**
 * Created by dllo on 16/10/9.
 * 数据库操作单例类
 */
public class LiteOrmInstance {
    private static LiteOrmInstance liteOrmInstance;

    /**
     * 数据库名字
     */
    private static final String DB_NAME = "car_home.db";
    /**
     * LiteOrm对象
     */
    private LiteOrm liteOrm;

    private LiteOrmInstance() {
        liteOrm = LiteOrm.newSingleInstance(CarApp.getContext(), DB_NAME);
    }

    public static LiteOrmInstance getLiteOrmInstance() {
        if (liteOrmInstance == null) {
            synchronized (LiteOrmInstance.class) {
                if (liteOrmInstance == null) {
                    liteOrmInstance = new LiteOrmInstance();
                }
            }
        }
        return liteOrmInstance;
    }

    /********************* 增********************/
    //插入一条数据
    public void insert(CarHomeBean collectBean) {
        if (liteOrm != null) {
            liteOrm.insert(collectBean);
        }
    }

    //插入集合
    public void insert(List<CarHomeBean> collectBeanList) {
        if (liteOrm != null) {
            liteOrm.insert(collectBeanList);
        }
    }

    /********************* 查********************/
    //查询所有
    public List<CarHomeBean> queryAll() {
        return liteOrm.query(CarHomeBean.class);
    }

    //如果有多张表
    public <T> List<T> queryData(Class<T> clz){
        return liteOrm.query(clz);
    }

    //根据条件查询
    //1.根据title(如果换成用户名, 查该用户存的)查询
    public List<CarHomeBean> queryById(int webUrlId){
        Log.d("LiteOrmInstance", "id:" + webUrlId);
        QueryBuilder<CarHomeBean> qb = new QueryBuilder<>(CarHomeBean.class);
        qb.where("webUrlId = ?",webUrlId);
        return liteOrm.query(qb);
    }

    //根据标题查询数据库
    //start, end 限制条数, 如查出100条, 限制想要多少条
    public List<CarHomeBean> queryByTitle(String title, int start, int end){
        QueryBuilder<CarHomeBean> qb = new QueryBuilder<>(CarHomeBean.class);
        qb.where("title = ?", new String[]{title});
        //但是, end需要判断, 不能大于end
        qb.limit(start, end);
        return liteOrm.query(qb);
    }

    /********************* 删********************/
    //按条件删
    public void deleteById(int webUrlId) {
        WhereBuilder wb = new WhereBuilder(CarHomeBean.class, "webUrlId = ?", new Integer[]{webUrlId});
        liteOrm.delete(wb);
    }
    //删除所有
    public void deleteAll(){
        liteOrm.deleteAll(CarHomeBean.class);
    }

}
