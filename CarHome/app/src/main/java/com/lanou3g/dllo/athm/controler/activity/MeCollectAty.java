package com.lanou3g.dllo.athm.controler.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lanou3g.dllo.athm.R;
import com.lanou3g.dllo.athm.controler.adapter.listview_adapter.MeAtyAdapter;
import com.lanou3g.dllo.athm.model.db.CarHomeBean;
import com.lanou3g.dllo.athm.model.db.LiteOrmInstance;

import java.util.List;

/**
 * 收藏详情页
 */

public class MeCollectAty extends AbsBaseActivity {
    private ListView listView;
    private MeAtyAdapter adapter;
    private TextView returnTv;


    @Override
    protected int setLayout() {
        return R.layout.activity_me_collect_aty;
    }

    @Override
    protected void initView() {
        returnTv = byView(R.id.me_aty_return);
        listView = byView(R.id.me_aty_list);
    }

    @Override
    protected void initDatas() {


        returnTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //创建适配器
        adapter = new MeAtyAdapter(this);
        //构造数据
        //查询数据库收藏内容
        List<CarHomeBean> bean= LiteOrmInstance.getLiteOrmInstance().queryAll();
        Log.d("MeCollectAty", "bean:" + bean.size());
        //设置数据
        adapter.setDatas(bean);

        //绑定适配器
        listView.setAdapter(adapter);


    }
}
