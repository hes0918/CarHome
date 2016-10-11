package com.lanou3g.dllo.athm.controler.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.lanou3g.dllo.athm.R;
import com.lanou3g.dllo.athm.controler.adapter.listview_adapter.MeAtyAdapter;
import com.lanou3g.dllo.athm.model.db.CarHomeBean;
import com.lanou3g.dllo.athm.model.db.LiteOrmInstance;

import java.util.List;

/**
 * 收藏详情页
 */

public class MeCollectAty extends AppCompatActivity {
    private ListView listView;
    private MeAtyAdapter adapter;
    private TextView returnTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me_collect_aty);
        returnTv = (TextView) findViewById(R.id.me_aty_return);
        returnTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        listView = (ListView) findViewById(R.id.me_aty_list);
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
