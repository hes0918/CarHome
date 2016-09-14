package com.lanou3g.dllo.athm.model.net;

/**
 * Created by dllo on 16/9/14.
 * 网络请求结果接口
 */
public interface VolleyResult {
    //请求成功接口
    void  success(String resultStr);
    //请求失败接口
    void  failure();
}
