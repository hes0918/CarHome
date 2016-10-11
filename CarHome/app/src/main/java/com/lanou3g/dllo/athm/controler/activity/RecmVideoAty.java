package com.lanou3g.dllo.athm.controler.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lanou3g.dllo.athm.R;

public class RecmVideoAty extends AbsBaseActivity implements View.OnClickListener {
    private WebView webView;
    //web的标题栏
    private ImageView xzImageView, scImageView;
    private TextView titleTv;

    @Override
    protected int setLayout() {
        return R.layout.activity_recm_video_aty;
    }

    @Override
    protected void initView() {
        webView = byView(R.id.video_web);
        xzImageView = byView(R.id.video_aty_xz_iv);
        scImageView = byView(R.id.video_aty_sc_iv);
        titleTv = byView(R.id.video_aty_title_tv);
    }

    @Override
    protected void initDatas() {
        titleTv.setOnClickListener(this);
        xzImageView.setOnClickListener(this);
        scImageView.setOnClickListener(this);

        Intent intent = getIntent();
        intent.getStringExtra("id");
        String url = "http://v.autohome.com.cn/v-" + intent.getStringExtra("id") + ".html";
        webView.loadUrl(url);
        //设置不跳转浏览器,在当前Aty上显示
        webView.setWebViewClient(new WebViewClient() {

        });
        //设置WebView加载网页的属性
        //WebSettings
        WebSettings webSettings = webView.getSettings();
        // 让WebView能够执行javaScript
        webSettings.setJavaScriptEnabled(true);
        // 让JavaScript可以自动打开windows
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        // 设置缓存
        webSettings.setAppCacheEnabled(true);
        // 设置缓存模式,一共有四种模式
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        // 设置缓存路径
//        webSettings.setAppCachePath("");
        // 支持缩放(适配到当前屏幕)
        webSettings.setSupportZoom(true);
        // 将图片调整到合适的大小
        webSettings.setUseWideViewPort(true);
        // 支持内容重新布局,一共有四种方式
        // 默认的是NARROW_COLUMNS
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        // 设置可以被显示的屏幕控制
        webSettings.setDisplayZoomControls(true);
        // 设置默认字体大小
        webSettings.setDefaultFontSize(12);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.video_aty_title_tv://返回键
                finish();
                break;
            case R.id.video_aty_sc_iv:
                scImageView.setSelected(true);
                Toast.makeText(this, "已收藏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.video_aty_xz_iv:
                Toast.makeText(this, "正在下载", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
