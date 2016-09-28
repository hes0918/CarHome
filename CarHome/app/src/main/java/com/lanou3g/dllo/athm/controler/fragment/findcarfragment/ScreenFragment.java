package com.lanou3g.dllo.athm.controler.fragment.findcarfragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.ListView;

import com.google.gson.Gson;
import com.lanou3g.dllo.athm.R;
import com.lanou3g.dllo.athm.controler.adapter.listview_adapter.FindCarScreenAdapter;
import com.lanou3g.dllo.athm.controler.fragment.AbsBaseFragment;
import com.lanou3g.dllo.athm.model.bean.FindCarScreenBean;
import com.lanou3g.dllo.athm.model.net.VolleyInstance;
import com.lanou3g.dllo.athm.model.net.VolleyResult;
import com.lanou3g.dllo.athm.utils.UrlQuantity;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * 找车-筛选页面
 */
public class ScreenFragment extends AbsBaseFragment {
    //定义Listview 实体类
    private ListView listView;
    private FindCarScreenAdapter adapter;
    private List<FindCarScreenBean.ResultBean.SeriesBean> data;

    public static ScreenFragment newInstance(String str) {


        Bundle args = new Bundle();
        //将String存储到Bundle中
        //Bundle是一个轻量级的存储类
        args.putString("text",str);
        ScreenFragment fragment = new ScreenFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int setLayout() {
        return R.layout.findcar_screen_fragment;
    }

    @Override
    protected void initView() {
             listView = byview(R.id.screen_list_view);
    }

    @Override
    protected void initDatas() {
        //在onActivityCreated中接收上面的传值   必须在这里接收
        //因为给Fragment传值的是Activity
        //只有这个方法才能接到
        //取值
        Bundle bunble = getArguments();
        //创建绑定适配器
        adapter = new FindCarScreenAdapter(context);
        listView.setAdapter(adapter);
        //利用封装的网络工具获取ListView数据
        VolleyInstance.getInstance().startRequest(UrlQuantity.SCREEN, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                FindCarScreenBean bean = gson.fromJson(resultStr,FindCarScreenBean.class);
                data = bean.getResult().getSeries();
                adapter.setDatas(data);
            }

            @Override
            public void failure() {

            }
        });
    }

}
