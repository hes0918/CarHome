package com.lanou3g.dllo.athm.controler.fragment.recomdfrgmet;

import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.lanou3g.dllo.athm.R;
import com.lanou3g.dllo.athm.controler.adapter.listview_adapter.RecmdNewesAdapter;
import com.lanou3g.dllo.athm.controler.fragment.AbsBaseFragment;
import com.lanou3g.dllo.athm.model.bean.RecmdNewesBean;

import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/9.
 */
public class NewestFrgment extends AbsBaseFragment {
    //网络接口
    private String url ="http://app.api.autohome.com.cn/autov4.8.8/news/newslist-pm1-c0-nt0-p1-s30-l0.json";
    //定义请求列队
    private RequestQueue queue;
    private RecmdNewesAdapter adapter;

    private ListView listView;



    //new一个单例
    public static NewestFrgment newInstance(String str) {

        Bundle args = new Bundle();
        //将String存储到Bundle中
        //Bundle是一个轻量级的存储类
        args.putString("text",str);

        NewestFrgment fragment = new NewestFrgment();
        //把这个数据创给fragment
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int setLayout() {
        return R.layout.newest_fragment;
    }

    @Override
    protected void initView() {
        //初始化
        listView = byview(R.id.newest_list_view);


    }

    @Override
    protected void initDatas() {
        //在onActivityCreated中接收上面的传值   必须在这里接收
        //因为给Fragment传值的是Activity
        //只有这个方法才能接到
        //取值
        Bundle bunble = getArguments();
        String string = bunble.getString("text");
        //绑定适配器
        adapter = new RecmdNewesAdapter(context);
        listView.setAdapter(adapter);
        //初始化请求队列
        queue = Volley.newRequestQueue(context);
        //请求数据
        StringRequest sr = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               //解析
                Gson gson = new Gson();
                RecmdNewesBean bean = gson.fromJson(response,RecmdNewesBean.class);
                List<RecmdNewesBean.ResultBean.NewslistBean> datas = bean.getResult().getNewslist();
                adapter.setDatas(datas);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
       queue.add(sr);


    }


}
