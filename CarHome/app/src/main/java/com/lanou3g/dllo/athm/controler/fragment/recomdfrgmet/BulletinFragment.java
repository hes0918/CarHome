package com.lanou3g.dllo.athm.controler.fragment.recomdfrgmet;

import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;
import com.lanou3g.dllo.athm.R;
import com.lanou3g.dllo.athm.controler.adapter.listview_adapter.RecmdBulletinAdapter;
import com.lanou3g.dllo.athm.controler.fragment.AbsBaseFragment;
import com.lanou3g.dllo.athm.model.bean.RecmdBulletinBean;
import com.lanou3g.dllo.athm.model.net.VolleyInstance;
import com.lanou3g.dllo.athm.model.net.VolleyResult;
import com.lanou3g.dllo.athm.utils.UrlQuantity;

import java.util.List;

/**
 * Created by dllo on 16/9/9.
 * 推荐-快报
 */
public class BulletinFragment extends AbsBaseFragment {
    //定义 list view
    private ListView listView;
    private RecmdBulletinAdapter adapter;
    private  RecmdBulletinBean bean;

    //单例
    public static BulletinFragment newInstance(String str) {

        Bundle args = new Bundle();
        //将String存储到Bundle中
        //Bundle是一个轻量级的存储类
        args.putString("text",str);
        BulletinFragment fragment = new BulletinFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int setLayout() {
        return R.layout.bulletin_fragment;
    }

    @Override
    protected void initView() {
        listView = byview(R.id.bulletin_list_view);

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
        adapter = new RecmdBulletinAdapter(context);
        //绑定适配器
        listView.setAdapter(adapter);
        //利用封装的网络工具类请求
        VolleyInstance.getInstance().startRequest(UrlQuantity.BULLETIN, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                //解析
                Gson gson = new Gson();
                bean = gson.fromJson(resultStr,RecmdBulletinBean.class);
                List<RecmdBulletinBean.ResultBean.ListBean> datas = bean.getResult().getList();
                adapter.setDatas(datas);
            }

            @Override
            public void failure() {

            }
        });

    }
}
