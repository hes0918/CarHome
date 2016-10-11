package com.lanou3g.dllo.athm.controler.fragment.recomdfrgmet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.lanou3g.dllo.athm.R;
import com.lanou3g.dllo.athm.controler.activity.RecmNewesAty;
import com.lanou3g.dllo.athm.controler.activity.RecmVideoAty;
import com.lanou3g.dllo.athm.controler.adapter.listview_adapter.RecmdVideoAdapter;
import com.lanou3g.dllo.athm.controler.fragment.AbsBaseFragment;
import com.lanou3g.dllo.athm.model.bean.RecmdVideoBean;
import com.lanou3g.dllo.athm.model.net.VolleyInstance;
import com.lanou3g.dllo.athm.model.net.VolleyResult;
import com.lanou3g.dllo.athm.utils.UrlQuantity;

import java.util.List;

/**
 * Created by dllo on 16/9/9.
 * 推荐-视频
 */
public class VideoFragment extends AbsBaseFragment {
    private ListView listView;
    private RecmdVideoAdapter adapter;

    public static VideoFragment newInstance(String str) {

        Bundle args = new Bundle();
        //将String存储到Bundle中
        //Bundle是一个轻量级的存储类
        args.putString("text",str);
        VideoFragment fragment = new VideoFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int setLayout()
    {
        return R.layout.video_fragment;
    }

    @Override
    protected void initView() {
      listView = byview(R.id.video_list_view);
    }

    @Override
    protected void initDatas() {
        //在onActivityCreated中接收上面的传值   必须在这里接收
        //因为给Fragment传值的是Activity
        //只有这个方法才能接到
        //取值
        Bundle bunble = getArguments();
        String string = bunble.getString("text");
        adapter = new RecmdVideoAdapter(context);
        listView.setAdapter(adapter);
        VolleyInstance.getInstance().startRequest(UrlQuantity.VIDEO, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                RecmdVideoBean bean = gson.fromJson(resultStr, RecmdVideoBean.class);
                List<RecmdVideoBean.ResultBean.ListBean> datas = bean.getResult().getList();
                adapter.setDatas(datas);
            }

            @Override
            public void failure() {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RecmdVideoBean.ResultBean.ListBean bean = (RecmdVideoBean.ResultBean.ListBean) parent.getItemAtPosition(position);
                String a = bean.getId() +"";
                Intent intent = new Intent(context, RecmVideoAty.class);
                intent.putExtra("id",a);
                startActivity(intent);
            }
        });

    }
}
