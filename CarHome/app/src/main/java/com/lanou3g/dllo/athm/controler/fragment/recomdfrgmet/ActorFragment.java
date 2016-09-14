package com.lanou3g.dllo.athm.controler.fragment.recomdfrgmet;

import android.os.Bundle;

import com.lanou3g.dllo.athm.R;
import com.lanou3g.dllo.athm.controler.fragment.AbsBaseFragment;

/**
 * Created by dllo on 16/9/9.
 * 论坛-优创页
 */
public class ActorFragment extends AbsBaseFragment {
    public static ActorFragment newInstance(String str) {

        Bundle args = new Bundle();
        //将String存储到Bundle中
        //Bundle是一个轻量级的存储类
        args.putString("text",str);

        ActorFragment fragment = new ActorFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.actor_fragment;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initDatas() {
        //在onActivityCreated中接收上面的传值   必须在这里接收
        //因为给Fragment传值的是Activity
        //只有这个方法才能接到
        //取值
        Bundle bunble = getArguments();
        String string = bunble.getString("text");
    }
}
