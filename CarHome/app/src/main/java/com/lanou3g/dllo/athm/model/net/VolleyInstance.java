package com.lanou3g.dllo.athm.model.net;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lanou3g.dllo.athm.controler.app.CarApp;

/**
 * Created by dllo on 16/9/14.
 * Volley使用单例类
 */
public class VolleyInstance {
    /**
     * 什么是单例?
     * 单独的实例 保证整个工程中该类的对象只有一个
     * 栈内存只有一个 堆内存只存在一块内存空间
     *
     * 例如网络,数据库的操作类 多个界面都需要使用该功能
     * 但是 网络和数据库只有一个 单独的创建
     * 想要使用时 就来调用这个单独的对象 而不是每次都new新的
     * 节省内存 避免多线程访问对象冲突
     *
     * 哪些部分需要单例: 线程池 网络 数据库 SP 下载 (耗时操作)等等
     *
     * 单例常用的命名: XXInstance XXSingleton
     */

    /**
     * 单例的写法:(双重校验锁)
     * 1.私有化构造方法:外部不能调用构造方法随意的创建对象
     * 2.对外提供获取对象的方法
     * (1) 定义静态当前类对象
     * (2) 对外提供获取方法: 进行单例判断
     */
    //定义请求队列
    private RequestQueue requestQueue;
    //2.(1) 定义静态当前类对象
    private  static  VolleyInstance instance;
    // 1. 私有构造方法
    private  VolleyInstance(){
        requestQueue = Volley.newRequestQueue(CarApp.getContext());

    }
    //2.(2)提供public公开方法: 进行单例判断
    public  static  VolleyInstance getInstance(){
        //如果该对象是null
        if (instance ==null){
            //全部线程同步扫描
            synchronized (VolleyInstance.class){
                //如果该对象还是null
                if (instance ==null){
                    instance = new VolleyInstance();
                }
            }
        }
        return instance;
    }
    /*****************************/
    //以下是封装网络请求
    //对外提供的请求方法
    public void startRequest(String url, final VolleyResult result){
        StringRequest sr = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //如果请求成功,将返回数据存储到接口里
                result.success(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //如果请求失败,通过接口通知调用者请求失败
                result.failure();
            }
        });
        requestQueue.add(sr);
    }
}

