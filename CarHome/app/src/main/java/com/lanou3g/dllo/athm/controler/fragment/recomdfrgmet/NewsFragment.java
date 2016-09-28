package com.lanou3g.dllo.athm.controler.fragment.recomdfrgmet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.google.gson.Gson;
import com.lanou3g.dllo.athm.R;
import com.lanou3g.dllo.athm.controler.activity.RecmNewsAty;
import com.lanou3g.dllo.athm.controler.adapter.listview_adapter.RecmdNewsAdapter;
import com.lanou3g.dllo.athm.controler.fragment.AbsBaseFragment;
import com.lanou3g.dllo.athm.model.bean.RecmdNewsBean;
import com.lanou3g.dllo.athm.model.net.VolleyInstance;
import com.lanou3g.dllo.athm.model.net.VolleyResult;
import java.util.List;

/**
 * Created by dllo on 16/9/10.
 * 推荐-新闻
 */
public class NewsFragment extends AbsBaseFragment {
    //定义ListView 实体类
    private ListView listView;
    private RecmdNewsAdapter adapter;
    private List<RecmdNewsBean.ResultBean.NewslistBean> data;

    public static NewsFragment newInstance(String str) {

        Bundle args = new Bundle();
        //将String存储到Bundle中
        //Bundle是一个轻量级的存储类
        args.putString("text",str);
        NewsFragment fragment = new NewsFragment();
        //把这个数据创给fragment
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.news_fragment;
    }

    @Override
    protected void initView() {
     listView = byview(R.id.news_list_view);
    }

    @Override
    protected void initDatas() {
        //在onActivityCreated中接收上面的传值   必须在这里接收
        //因为给Fragment传值的是Activity
        //只有这个方法才能接到
        //取值
        Bundle bunble = getArguments();
        String string = bunble.getString("text");

        //创建绑定适配器
        adapter = new RecmdNewsAdapter(context);
        listView.setAdapter(adapter);
        //封装网络获取的数据
        VolleyInstance.getInstance().startRequest(string, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                //解析
                Gson gson = new Gson();
                RecmdNewsBean bean = gson.fromJson(resultStr,RecmdNewsBean.class);
                data = bean.getResult().getNewslist();
                adapter.setDatas(data);
            }

            @Override
            public void failure() {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RecmdNewsBean.ResultBean.NewslistBean
                        bean = (RecmdNewsBean.ResultBean.NewslistBean) parent.getItemAtPosition(position);
                String a = bean.getId() +"";
                Intent intent = new Intent(context, RecmNewsAty.class);
                intent.putExtra("id",a);
                startActivity(intent);
            }
        });

    }
}
