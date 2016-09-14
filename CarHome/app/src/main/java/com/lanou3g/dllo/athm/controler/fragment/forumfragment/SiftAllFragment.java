package com.lanou3g.dllo.athm.controler.fragment.forumfragment;

import android.os.Bundle;
import android.widget.ListView;

import com.lanou3g.dllo.athm.R;
import com.lanou3g.dllo.athm.controler.adapter.listview_adapter.ForumSiftAllAdapter;
import com.lanou3g.dllo.athm.controler.fragment.AbsBaseFragment;
import com.lanou3g.dllo.athm.model.bean.ForumSiftAllBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/10.
 * 论坛-精贴-全部的页面
 */
public class SiftAllFragment extends AbsBaseFragment {
    //定义Listview 实体类
    private ListView listView;
    private List<ForumSiftAllBean> datas;
    //单例
    public static SiftAllFragment newInstance(String str) {

        Bundle args = new Bundle();
        //将String存储到Bundle中
        //Bundle是一个轻量级的存储类
        args.putString("text",str);

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


        //构造列表假数据测试
        bulidDatas();
        //创建适配器
        ForumSiftAllAdapter adapter = new ForumSiftAllAdapter(context);
        //设置数据
        adapter.setDatas(datas);
        //绑定适配器
        listView.setAdapter(adapter);
    }

    private void bulidDatas() {
        datas = new ArrayList<>();
        for (int i = 0; i <40 ; i++) {
            datas.add(new ForumSiftAllBean("标题","内容","计数",R.mipmap.ic_launcher));

        }
    }
}
