package com.lanou3g.dllo.athm.controler.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lanou3g.dllo.athm.R;


public class FroumSiftAllAty extends AbsBaseActivity implements View.OnClickListener {
    private WebView webView;
    //web的标题栏
    private ImageView xzImageView,scImageView;
    private TextView titleTv;

    @Override
    protected int setLayout() {
        return R.layout.activity_froum_sift_all_aty;
    }

    @Override
    protected void initView() {
        webView = byView(R.id.siftall_web);
        xzImageView = byView(R.id.siftall_aty_xz_iv);
        scImageView = byView(R.id.siftall_aty_sc_iv);
        titleTv = byView(R.id.siftall_aty_title_tv);
    }

    @Override
    protected void initDatas() {
        titleTv.setOnClickListener(this);
        xzImageView.setOnClickListener(this);
        scImageView.setOnClickListener(this);

        Intent intent =getIntent();
        intent.getStringExtra("id");
        String url = "http://forum.app.autohome.com.cn/autov5.0.0/forum/club/topiccontent-a2-pm2-v5.0.0-t"
                + intent.getStringExtra("id") + "-o0-p1-s20-c1-nt0-fs0-sp0-al0-cw320.json";
        webView.loadUrl(url);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.siftall_aty_title_tv://返回键
                finish();
                break;
            case R.id.siftall_aty_sc_iv:
                scImageView.setSelected(true);
                Toast.makeText(this, "已收藏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.siftall_aty_xz_iv:
                Toast.makeText(this, "正在下载", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
