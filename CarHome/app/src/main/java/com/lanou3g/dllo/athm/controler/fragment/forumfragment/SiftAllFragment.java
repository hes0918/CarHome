package com.lanou3g.dllo.athm.controler.fragment.forumfragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.lanou3g.dllo.athm.R;
import com.lanou3g.dllo.athm.controler.activity.FroumSiftAllAty;
import com.lanou3g.dllo.athm.controler.activity.RecmNewesAty;
import com.lanou3g.dllo.athm.controler.adapter.listview_adapter.ForumSiftAllAdapter;
import com.lanou3g.dllo.athm.controler.fragment.AbsBaseFragment;
import com.lanou3g.dllo.athm.model.bean.ForumSiftAllBean;
import com.lanou3g.dllo.athm.model.net.VolleyInstance;
import com.lanou3g.dllo.athm.model.net.VolleyResult;
import com.lanou3g.dllo.athm.utils.UrlQuantity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/10.
 * 论坛-精贴-全部的页面
 */
public class SiftAllFragment extends AbsBaseFragment {
    //定义Listview 实体类
    private ListView listView;
    private ForumSiftAllAdapter adapter;

    //单例
    public static SiftAllFragment newInstance(String str) {

        Bundle args = new Bundle();
        //将String存储到Bundle中
        //Bundle是一个轻量级的存储类
        args.putString("text", str);
        SiftAllFragment fragment = new SiftAllFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.forum_sift_all_frgt;
    }

    @Override
    protected void initView() {
        //初始化Listview
        listView = byview(R.id.sift_all_list_view);
    }

    @Override
    protected void initDatas() {
        //在onActivityCreated中接收上面的传值   必须在这里接收
        //因为给Fragment传值的是Activity
        //只有这个方法才能接到
        //取值
        Bundle bunble = getArguments();
        String string = bunble.getString("text");

        //创建适配器
        adapter = new ForumSiftAllAdapter(context);
        //绑定适配器
        listView.setAdapter(adapter);
        //利用封装的网络工具类请求
        VolleyInstance.getInstance().startRequest(string, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                //解析
                Gson gson = new Gson();
                ForumSiftAllBean bean = gson.fromJson(resultStr, ForumSiftAllBean.class);
                List<ForumSiftAllBean.ResultBean.ListBean> datas = bean.getResult().getList();
                adapter.setDatas(datas);
            }

            @Override
            public void failure() {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ForumSiftAllBean.ResultBean.ListBean bean = (ForumSiftAllBean.ResultBean.ListBean) parent.getItemAtPosition(position);
                String a = bean.getBbsid() +"";
                Intent intent = new Intent(context, FroumSiftAllAty.class);
                intent.putExtra("id",a);
                startActivity(intent);
            }
        });
    }


}
