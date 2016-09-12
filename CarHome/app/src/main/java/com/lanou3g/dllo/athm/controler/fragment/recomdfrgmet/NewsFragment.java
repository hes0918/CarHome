package com.lanou3g.dllo.athm.controler.fragment.recomdfrgmet;

import android.os.Bundle;
import android.widget.TextView;

import com.lanou3g.dllo.athm.R;
import com.lanou3g.dllo.athm.controler.fragment.AbsBaseFragment;

/**
 * Created by dllo on 16/9/10.
 */
public class NewsFragment extends AbsBaseFragment {

    private TextView textView;

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
        textView=byview(R.id.news_fragment_tv);

    }

    @Override
    protected void initDatas() {
        //在onActivityCreated中接收上面的传值   必须在这里接收
        //因为给Fragment传值的是Activity
        //只有这个方法才能接到
        //取值
        Bundle bunble = getArguments();
        String string = bunble.getString("text");
        //使用
        textView.setText(string);
    }
}
