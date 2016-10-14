package com.lanou3g.dllo.athm.controler.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
/**
 * 最新的详情页
 */

import com.lanou3g.dllo.athm.R;
import com.lanou3g.dllo.athm.model.db.CarHomeBean;
import com.lanou3g.dllo.athm.model.db.LiteOrmInstance;
import com.lanou3g.dllo.athm.utils.CarToast;

import java.util.List;

public class RecmNewesAty extends AbsBaseActivity implements View.OnClickListener {

    private WebView webView;
    //web的标题栏
    private ImageView xzImageView,scImageView;
    private TextView titleTv;
    private int id;
    private String title;
    private String url;
    @Override
    protected int setLayout() {
        return R.layout.activity_recm_newes_aty;
    }

    @Override
    protected void initView() {
        webView =byView(R.id.recm_web);
        xzImageView = byView(R.id.newes_aty_xz_iv);
        scImageView = byView(R.id.newes_aty_sc_iv);
        titleTv = byView(R.id.newes_aty_title_tv);
    }

    @Override
    protected void initDatas() {
        titleTv.setOnClickListener(this);
        xzImageView.setOnClickListener(this);
        scImageView.setOnClickListener(this);

        Intent intent =getIntent();
        id = intent.getIntExtra("id",0);
        title = intent.getStringExtra("title");
        url = "http://cont.app.autohome.com.cn/autov4.2.5/content/News/newscontent-a2-pm1-v4.2.5-n"
                + id + "-lz0-sp0-nt0-sa1-p0-c1-fs0-cw320.html";
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
        //查询是否已被收藏
        List<CarHomeBean> bean=LiteOrmInstance.getLiteOrmInstance().queryById(id);
        if (bean.size()>0){
            scImageView.setSelected(true);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.newes_aty_title_tv://返回键
                finish();
                break;
            case R.id.newes_aty_sc_iv:
                if (scImageView.isSelected()==true){
                    scImageView.setSelected(false);
                    //根据ID 查询数据库里面收藏的网页
                    List<CarHomeBean> bean=LiteOrmInstance.getLiteOrmInstance().queryById(id);
                    if (bean.size()>0){
                        LiteOrmInstance.getLiteOrmInstance().deleteById(id);
                        Toast.makeText(this, "取消收藏", Toast.LENGTH_SHORT).show();
                    }

                }else if (scImageView.isSelected()==false){
                    scImageView.setSelected(true);

                    //根据ID 查询数据库里面收藏的网页
                    List<CarHomeBean> bean=LiteOrmInstance.getLiteOrmInstance().queryById(id);
                    Log.d("RecmNewesAty", "bean:" + bean);
                    if (bean.size()>0){
                    }else {
                        LiteOrmInstance.getLiteOrmInstance().insert(new CarHomeBean(id,title,url));
                        CarToast.shortMsg("收藏成功");
                    }
                }


                break;
            case R.id.newes_aty_xz_iv:
                CarToast.shortMsg("不能下载");
                break;
        }
    }
}
