package com.lanou3g.dllo.athm.controler.activity;

import android.content.Intent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.gson.Gson;
import com.lanou3g.dllo.athm.R;
import com.lanou3g.dllo.athm.model.bean.BullDetailbean;
import com.lanou3g.dllo.athm.model.net.VolleyInstance;
import com.lanou3g.dllo.athm.model.net.VolleyResult;

/**
 * Created by dllo on 16/9/26.
 */
public class RecmBullAty extends AbsBaseActivity {
    private WebView webView;
    @Override
    protected int setLayout() {
        return R.layout.activity_recm_bull;
    }

    @Override
    protected void initView() {
        webView = byView(R.id.bulletin_web);

    }

    @Override
    protected void initDatas() {

        Intent intent =getIntent();
        intent.getStringExtra("id");
        String url = "http://cont.app.autohome.com.cn/autov5.0.0/content/News/fastnewscontent-n"
                + intent.getStringExtra("id") + "-lastid0-o1.json";
        //解析
        VolleyInstance.getInstance().startRequest(url, new VolleyResult() {
            @Override
            public void success(String resultStr) {

                Gson gson = new Gson();
                BullDetailbean bean = gson.fromJson(resultStr,BullDetailbean.class);
                BullDetailbean.ResultBean data = bean.getResult();

            }

            @Override
            public void failure() {

            }
        });


        webView.loadUrl(url);
        //设置不跳转浏览器,在当前Aty上显示
        webView.setWebViewClient(new WebViewClient(){
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
}
