package com.lanou3g.dllo.athm.controler.fragment;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lanou3g.dllo.athm.R;
import com.lanou3g.dllo.athm.controler.activity.MeCollectAty;
import com.lanou3g.dllo.athm.controler.adapter.listview_adapter.MeAdapter;
import com.lanou3g.dllo.athm.model.bean.RecmdNewesBean;
import com.lanou3g.dllo.athm.model.db.CarHomeBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;


/**
 * Created by dllo on 16/9/9.
 * 我
 */
public class MeFragment extends AbsBaseFragment {
    private ListView listView;
    private MeAdapter adapter;
    private List<String> datas;
    private LinearLayout qqLl;
    private TextView qqtv;
    private ImageView qqimg;

    @Override
    protected int setLayout() {
        return R.layout.me_fragment;
    }

    @Override
    protected void initView() {
      listView = byview(R.id.me_list_view);
        qqLl = byview(R.id.me_qq_root);
        qqtv = byview(R.id.me_qq_name_tv);
        qqimg = byview(R.id.me_qq_tx_img);
    }

    @Override
    protected void initDatas() {
        qqLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取第三方平台
                Platform platform = ShareSDK.getPlatform(context, QQ.NAME);
                //授权
                platform.authorize();
                //获取用户信息
                platform.setPlatformActionListener(new PlatformActionListener() {
                    //完成的监听
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        Toast.makeText(context, "完成", Toast.LENGTH_SHORT).show();
                        //获取qq头像和名字
                        PlatformDb db = platform.getDb();
                        String name = db.getUserName();
                        String icon = db.getUserIcon();
                        qqtv.setText(name);
                        Picasso.with(context).load(icon).into(qqimg);
                    }
                    //登录错误的监听
                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {
                        Toast.makeText(context, "错误", Toast.LENGTH_SHORT).show();
                    }
                    //登录取消的监听
                    @Override
                    public void onCancel(Platform platform, int i) {
                        Toast.makeText(context, "取消", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        adapter = new MeAdapter(context);
        datas = new ArrayList<>();
        datas.add("兑换商城");
        datas.add("我的优惠券");
        datas.add("我的收藏");
        datas.add("浏览历史");
        datas.add("草稿箱");
        datas.add("设置");
        adapter.setDatas(datas);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:


                        Intent intent = new Intent(context, MeCollectAty.class);
                        startActivity(intent);

                        break;
                }
            }
        });
    }


}
