package com.lanou3g.dllo.athm.controler.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lanou3g.dllo.athm.R;
import com.lanou3g.dllo.athm.controler.adapter.listview_adapter.RecmdBullAtyAdapter;
import com.lanou3g.dllo.athm.model.bean.BullAtyBean;
import com.lanou3g.dllo.athm.model.net.VolleyInstance;
import com.lanou3g.dllo.athm.model.net.VolleyResult;

import java.util.List;

/**
 * Created by dllo on 16/9/26.
 */
public class RecmBullAty extends AbsBaseActivity implements View.OnClickListener {
    private ListView listView;
    private RecmdBullAtyAdapter atyAdapter;
    //web的标题栏
    private ImageView xzImageView,scImageView;
    private TextView titleTv;
    @Override
    protected int setLayout() {
        return R.layout.activity_recm_bull;
    }

    @Override
    protected void initView() {
        listView = byView(R.id.bull_aty_list_view);
        xzImageView = byView(R.id.bull_aty_xz_iv);
        scImageView = byView(R.id.bull_aty_sc_iv);
        titleTv = byView(R.id.bull_aty_title_tv);
    }

    @Override
    protected void initDatas() {
        titleTv.setOnClickListener(this);
        xzImageView.setOnClickListener(this);
        scImageView.setOnClickListener(this);
        //创建适配器
        atyAdapter = new RecmdBullAtyAdapter(this);
        //绑定适配器
        listView.setAdapter(atyAdapter);

        Intent intent =getIntent();
        intent.getStringExtra("id");
        String url = "http://cont.app.autohome.com.cn/autov5.0.0/content/News/fastnewscontent-n"
                + intent.getStringExtra("id") + "-lastid0-o1.json";
        //解析
        VolleyInstance.getInstance().startRequest(url, new VolleyResult() {
            @Override
            public void success(String resultStr) {

                Gson gson = new Gson();
                BullAtyBean bean = gson.fromJson(resultStr,BullAtyBean.class);
                List<BullAtyBean.ResultBean.MessagelistBean> datas = bean.getResult().getMessagelist();
                atyAdapter.setDatas(datas);
            }

            @Override
            public void failure() {

            }
        });


    }
    //标题的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bull_aty_title_tv://返回键
                finish();
                break;
            case R.id.bull_aty_sc_iv:
                scImageView.setSelected(true);
                Toast.makeText(this, "已收藏", Toast.LENGTH_SHORT).show();

                break;
            case R.id.bull_aty_xz_iv:
                Toast.makeText(this, "正在下载", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
