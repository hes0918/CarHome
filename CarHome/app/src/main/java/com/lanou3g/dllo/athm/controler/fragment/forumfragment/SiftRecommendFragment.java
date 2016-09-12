package com.lanou3g.dllo.athm.controler.fragment.forumfragment;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.lanou3g.dllo.athm.R;
import com.lanou3g.dllo.athm.controler.adapter.listview_adapter.ForumDialogAdapter;
import com.lanou3g.dllo.athm.controler.adapter.pager_adapter.SiftRecyclerAdapter;
import com.lanou3g.dllo.athm.controler.fragment.AbsBaseFragment;
import com.lanou3g.dllo.athm.utils.OnRvItemClick;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/10.
 */
public class SiftRecommendFragment extends AbsBaseFragment {
    //定义标题栏的图片 点击弹出dialog
    private ImageView imageView;
    private AlertDialog alertDialog;

    private ListView listView;
    private List<String> datas;
    //Activity 不居中最外层的布局
    private LinearLayout rootView;

    //定义RecyclerView
    private RecyclerView recyclerView;
    //定义占位布局的Fragment
    private SiftAllFragment siftAllFragment;
    //定义适配器
    private SiftRecyclerAdapter adapter;

    @Override
    protected int setLayout() {
        return R.layout.forum_sift_fragment;
    }

    @Override
    protected void initView() {
        recyclerView = byview(R.id.sift_title_recycler);
        siftAllFragment = new SiftAllFragment();
        //初始化图片
        imageView = byview(R.id.forum_sift_image);
        //依附的父容器
        rootView = byview(R.id.root_view);


    }

    @Override
    protected void initDatas() {


        adapter = new SiftRecyclerAdapter(context);
        recyclerView.setAdapter(adapter);
        //RecyclerView 对比ListView多的是
        //1.需要设置布局管理器
        //  布局管理器一共有三种
        //  现在先来使用第一种 线性布局管理器 垂直和水平方向
        LinearLayoutManager llManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        //将布局管理者设置给RecyclerView
        recyclerView.setLayoutManager(llManager);
        //为RecyclerView设置数据
        List<String> data = new ArrayList<>();
        data.add("全部");
        data.add("媳妇当车模");
        data.add("美人\"记\"");
        data.add("论坛红人馆");
        data.add("论坛讲师");
        data.add("精挑细选");
        data.add("现身说法");
        data.add("高端阵地");
        data.add("电动车");
        data.add("汇买车");
        data.add("行车点评");
        data.add("超级试驾员");
        data.add("海外购车");
        data.add("经典老车");
        data.add("妹子选车");
        data.add("优惠购车");
        data.add("原创大片");
        data.add("顶配风采");
        data.add("改装有理");
        data.add("养车有道");
        data.add("首发阵营");
        data.add("新车直播");
        data.add("历史选题");
        data.add("摩友天地");
        data.add("蜜月之旅");
        data.add("甜蜜有礼");
        data.add("摄影课堂");
        data.add("车友聚会");
        data.add("单车部落");
        data.add("杂谈俱乐部");
        data.add("华北游记");
        data.add("西南游记");
        data.add("东北游记");
        data.add("西北游记");
        data.add("华中游记");
        data.add("华南游记");
        data.add("华东游记");
        data.add("港澳台游记");
        data.add("海外游记");
        data.add("沧海遗珠");
        adapter.setDatas(data);


        //为RecyclerView设置行布局点击事件
        adapter.setOnRvItemClick(new OnRvItemClick() {

            @Override
            public void onRvItemClickListener(int position, String str) {
                Toast.makeText(context, "position:" + position, Toast.LENGTH_SHORT).show();
                //碎片管理者
                FragmentManager manager = getChildFragmentManager();
                final FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.forum_sift_frame, siftAllFragment);
                transaction.commit();

            }

        });

        //点击图片弹出dialog
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //popup  弹出 弹出窗口
                final PopupWindow pw = new PopupWindow(context);
                //设置固定值的宽
                pw.setWidth(600);
                //使用Match或者Wrap来限制
                // pw.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                //高设置的方式和宽一样 也是这两种
                pw.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
                //加载出对话框显示的View
                final View view = LayoutInflater.from(context).inflate(R.layout.forum_dialog_login, null);
                listView = (ListView) view.findViewById(R.id.dialog_list_view);
                //构造listview假数据
                //创建适配器
                bulidDatas();
                ForumDialogAdapter adapter = new ForumDialogAdapter(context);
                adapter.setDatas(datas);
                listView.setAdapter(adapter);
                //点击关闭退出窗口
                TextView dialogTv = (TextView) view.findViewById(R.id.dialog_close_tv);
                dialogTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //关闭PopWindow
                        pw.dismiss();
                    }
                });
                //设置弹出窗口的内容
                pw.setContentView(view);
                //设置弹出窗口焦点
                pw.setFocusable(true);
                //外界点击能力
                pw.setOutsideTouchable(true);
                //显示在固定的X,Y位置
                //参数1:父容器 弹出窗口 显示依附的父容器
                //参数2:Gravity 重心
                //参数3 4:X,Y的位置
                pw.showAtLocation(rootView, Gravity.RIGHT,0,0);
            }

        });


    }


    private void bulidDatas() {
        datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            datas.add("全部" + i);
        }
    }
}
