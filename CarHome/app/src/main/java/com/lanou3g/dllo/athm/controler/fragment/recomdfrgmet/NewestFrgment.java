package com.lanou3g.dllo.athm.controler.fragment.recomdfrgmet;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

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
import com.lanou3g.dllo.athm.model.net.VolleyInstance;
import com.lanou3g.dllo.athm.model.net.VolleyResult;
import com.lanou3g.dllo.athm.utils.UrlQuantity;

import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/9.
 * 推荐-最新页面
 */
public class NewestFrgment extends AbsBaseFragment {
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


        //利用封装的网络工具类请求
        VolleyInstance.getInstance().startRequest(UrlQuantity.newestUrl, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                //解析
                Gson gson = new Gson();
                RecmdNewesBean bean = gson.fromJson(resultStr,RecmdNewesBean.class);
                List<RecmdNewesBean.ResultBean.NewslistBean> datas = bean.getResult().getNewslist();
                adapter.setDatas(datas);
            }

            @Override
            public void failure() {

            }
        });


    }


}
