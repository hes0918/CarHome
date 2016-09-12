package com.lanou3g.dllo.athm.controler.fragment.recomdfrgmet;

import android.os.Bundle;
import android.widget.ListView;

import com.lanou3g.dllo.athm.R;
import com.lanou3g.dllo.athm.controler.adapter.listview_adapter.RecmdNewesAdapter;
import com.lanou3g.dllo.athm.controler.fragment.AbsBaseFragment;
import com.lanou3g.dllo.athm.model.bean.RecmdNewesBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/9.
 */
public class NewestFrgment extends AbsBaseFragment {

    private ListView listView;
    private List<RecmdNewesBean> datas;


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

        //构造列表假数据
        bulidDatas();
        //创建适配器
        RecmdNewesAdapter adapter = new RecmdNewesAdapter(context);
        adapter.setDatas(datas);
        listView.setAdapter(adapter);

    }

    private void bulidDatas() {
        datas = new ArrayList<>();
        for (int i = 0; i <30 ; i++) {
            datas.add(new RecmdNewesBean("标题","时间","计数"+i,R.mipmap.ic_launcher));

        }
    }
}
