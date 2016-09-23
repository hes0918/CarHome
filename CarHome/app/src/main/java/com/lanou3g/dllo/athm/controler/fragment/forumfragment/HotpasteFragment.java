package com.lanou3g.dllo.athm.controler.fragment.forumfragment;

import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;
import com.lanou3g.dllo.athm.R;
import com.lanou3g.dllo.athm.controler.adapter.listview_adapter.ForumHotAdapter;
import com.lanou3g.dllo.athm.controler.fragment.AbsBaseFragment;
import com.lanou3g.dllo.athm.model.bean.ForumHotBean;
import com.lanou3g.dllo.athm.model.net.VolleyInstance;
import com.lanou3g.dllo.athm.model.net.VolleyResult;
import com.lanou3g.dllo.athm.utils.UrlQuantity;

import java.util.List;

/**
 * Created by dllo on 16/9/10.
 * 论坛-热帖
 */
public class HotpasteFragment extends AbsBaseFragment{
    private ListView listView;
    private ForumHotAdapter adapter;
    private List<ForumHotBean.ResultBean.ListBean> data;

    public static HotpasteFragment newInstance() {
        Bundle args = new Bundle();
        HotpasteFragment fragment = new HotpasteFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int setLayout() {
        return R.layout.forum_hotpaste_fragment;
    }

    @Override
    protected void initView() {
     listView = byview(R.id.hot_list_view);
    }

    @Override
    protected void initDatas() {

        //在onActivityCreated中接收上面的传值   必须在这里接收
        //因为给Fragment传值的是Activity
        //只有这个方法才能接到
        //取值
        Bundle bunble = getArguments();

        //创建listView适配器
        adapter = new ForumHotAdapter(context);
        //绑定
        listView.setAdapter(adapter);
        VolleyInstance.getInstance().startRequest(UrlQuantity.HOTPASTE, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                ForumHotBean bean = gson.fromJson(resultStr, ForumHotBean.class);
                data = bean.getResult().getList();
                adapter.setDatas(data);
            }

            @Override
            public void failure() {

            }
        });
    }
}
