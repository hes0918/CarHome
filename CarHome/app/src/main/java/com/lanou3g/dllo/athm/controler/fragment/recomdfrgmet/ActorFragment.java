package com.lanou3g.dllo.athm.controler.fragment.recomdfrgmet;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lanou3g.dllo.athm.R;
import com.lanou3g.dllo.athm.controler.adapter.listview_adapter.RecmdActorAdapter;
import com.lanou3g.dllo.athm.controler.adapter.rotate_vp_adapter.ActorRotateVpAdapter;
import com.lanou3g.dllo.athm.model.bean.RecmdActorBean;
import com.lanou3g.dllo.athm.model.bean.rotatebean.ActorRotateBean;
import com.lanou3g.dllo.athm.model.bean.rotatebean.RotateBean;
import com.lanou3g.dllo.athm.model.net.VolleyInstance;
import com.lanou3g.dllo.athm.model.net.VolleyResult;
import com.lanou3g.dllo.athm.utils.UrlQuantity;
import com.lanou3g.dllo.athm.views.ActorListView;
import com.lanou3g.dllo.athm.controler.fragment.AbsBaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/9.
 * 论坛-优创页
 */
public class ActorFragment extends AbsBaseFragment implements View.OnClickListener {
    //轮播图部分
    //停留的时间
    private  static  final  int TIME = 3000;
    //滑动的vp
    private ViewPager viewPager;
    //随着轮播改变的小点点
    private LinearLayout pointLl;
    //轮播图的滑动适配器
    private ActorRotateVpAdapter vpAdapter;
    //轮播图的实体类
    private List<ActorRotateBean> rotateDatas;
    //定义标题栏的图片 点击弹出dialog
    private ImageView imageView;
    private TextView pwtitle;
    //定义PopupWindow窗口
    private PopupWindow pw;
    //Activity 不居中最外层的布局
    private ScrollView rootView;
    //加载出对话框显示的View
    private View view;
    private TextView yxTv;
    private TextView qbTv;
    private TextView gzTv;
    //定义自定义的listView
    private ActorListView listView;
    //定义listView适配器
    private RecmdActorAdapter adapter;
    //定义实体类的组合
    private List<RecmdActorBean.ResultBean.NewslistBean> datas;

    //new一个fragment的单例
    public static ActorFragment newInstance(String str) {
        Bundle args = new Bundle();
        //将String存储到Bundle中
        //Bundle是一个轻量级的存储类
        args.putString("text", str);
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
        viewPager=byview(R.id.actor_rotate_vp);
        pointLl = byview(R.id.actor_rotate_point_container);
        rootView = byview(R.id.actor_root_view);
        imageView = byview(R.id.actor_pw_iv);
        listView = byview(R.id.actor_lv);
        pwtitle = byview(R.id.action_pw_title_tv);
    }

    @Override
    protected void initDatas() {
        //fragment取值
        fragmentValue();
        //绑定listView
        adapter = new RecmdActorAdapter(context);
        listView.setAdapter(adapter);
        //利用封装的网络工具类请求Listview最新的数据
        listviewNewestVolley();
        //请求轮播图的数据
        buildDatas();
        //绑定vp适配器
        vpAdapter = new ActorRotateVpAdapter(context);
        viewPager.setAdapter(vpAdapter);
        //设置数据
        vpAdapter.setRotateDatas(rotateDatas);
        // ViewPager的页数为int最大值,设置当前页多一些,可以上来就向前滑动
        // 为了保证第一页始终为数据的第0条 取余要为0,因此设置数据集合大小的倍数
        viewPager.setCurrentItem(rotateDatas.size() * 100);
        // 开始轮播
        handler = new Handler();
        startRotate();
        // 添加轮播小点
        addPoints();
        // 随着轮播改变小点
        changePoints();
        //为listview添加头布局
        View headView = LayoutInflater.from(context).inflate(R.layout.actor_coming,null);
        listView.addHeaderView(headView);
        //点击图片弹出dialog
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupPW();
            }
        });
    }
    /**
     设置轮播图数据
     */
    private void buildDatas() {
        rotateDatas = new ArrayList<>();
        rotateDatas.add(new ActorRotateBean("http://qn.www2.autoimg.cn/youchuang/g19/M0C/2C/00/autohomecar__wKgFU1ffW7iAGPZ0AANHo8Rljj0535.jpg?imageView2/0/w/640/h/320"));
        rotateDatas.add(new ActorRotateBean("http://qn.www2.autoimg.cn/youchuang/g15/M03/4A/78/autohomecar__wKgH5VfeodGAVf7nAAMzrE1pgT0908.jpg?imageView2/0/w/640/h/320"));
        rotateDatas.add(new ActorRotateBean("http://qn.www2.autoimg.cn/youchuang/g4/M0C/4D/A7/autohomecar__wKgH2lffWv2Adm9TAAPUMXaqCOU308.jpg?imageView2/0/w/640/h/320"));
        rotateDatas.add(new ActorRotateBean("http://qn.www2.autoimg.cn/youchuang/g14/M04/49/FE/autohomecar__wKgH1VffTVSAUHRSAANsmbFvlP8648.jpg?imageView2/0/w/640/h/320"));
        rotateDatas.add(new ActorRotateBean("http://qn.www2.autoimg.cn/youchuang/g12/M01/4D/7E/autohomecar__wKjBy1fepBCALBHAAARPbzoaOKs603.jpg?imageView2/0/w/640/h/320"));
    }
    private void changePoints() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (isRotate) {
                    // 把所有小点设置为白色
                    for (int i = 0; i < rotateDatas.size(); i++) {
                        ImageView pointIv = (ImageView) pointLl.getChildAt(i);
                        pointIv.setImageResource(R.mipmap.lunpo_dangqian);
                    }
                    // 设置当前位置小点为灰色
                    ImageView iv = (ImageView) pointLl.getChildAt(position % rotateDatas.size());
                    iv.setImageResource(R.mipmap.lunpo);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 添加轮播切换小点
     */
    private void addPoints() {
        // 有多少张图加载多少个小点
        for (int i = 0; i < rotateDatas.size(); i++) {
            ImageView pointIv = new ImageView(context);
            pointIv.setPadding(5,5,5,5);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20,20);
            pointIv.setLayoutParams(params);

            // 设置第0页小点的为灰色
            if (i == 0) {
                pointIv.setImageResource(R.mipmap.lunpo);
            } else {
                pointIv.setImageResource(R.mipmap.lunpo_dangqian);
            }
            pointLl.addView(pointIv);
        }
    }


    private Handler handler;
    private boolean isRotate = false;
    private Runnable rotateRunnable;

    /**
     * 开始轮播
     */
    private void startRotate() {
        rotateRunnable = new Runnable() {
            @Override
            public void run() {
                int nowIndex = viewPager.getCurrentItem();
                viewPager.setCurrentItem(++nowIndex);
                if (isRotate) {
                    handler.postDelayed(rotateRunnable, TIME);
                }
            }
        };
        handler.postDelayed(rotateRunnable, TIME);
    }

    @Override
    public void onResume() {
        super.onResume();
        isRotate = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        isRotate = false;
    }

    //网络取值
    private void listviewNewestVolley() {
        VolleyInstance.getInstance().startRequest(UrlQuantity.ACTORURL, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                //解析新闻
                Gson gson = new Gson();
                RecmdActorBean bean = gson.fromJson(resultStr,RecmdActorBean.class);
                datas = bean.getResult().getNewslist();
                adapter.setDatas(datas);
            }

            @Override
            public void failure() {

            }
        });
    }

    //fragment取值
    private void fragmentValue() {
        //在onActivityCreated中接收上面的传值   必须在这里接收
        //因为给Fragment传值的是Activity
        //只有这个方法才能接到
        //取值
        Bundle bunble = getArguments();
        String string = bunble.getString("text");
    }

    //弹出窗口
    private void popupPW() {
        //popup 弹出窗口
        pw = new PopupWindow(context);
        //设置固定值的宽
        pw.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //高设置的方式和宽一样 也是这两种
        pw.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //加载出对话框显示的View
        view = LayoutInflater.from(context).inflate(R.layout.actor_dialog,null);
        //点击选择并退出窗口
         yxTv = (TextView) view.findViewById(R.id.actor_yx_tv);
         qbTv = (TextView) view.findViewById(R.id.actor_qb_tv);
         gzTv = (TextView) view.findViewById(R.id.actor_gz_tv);
        yxTv.setOnClickListener(this);
        qbTv.setOnClickListener(this);
        gzTv.setOnClickListener(this);
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
        pw.showAtLocation(rootView,Gravity.TOP,0,230);
      //  pw.showAsDropDown();
        //消失时的监听
        pw.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //图片设置换回来
                imageView.setImageResource(R.mipmap.ahlib_arrow_gray_down);
            }
        });
    }
    //pw的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.actor_yx_tv://优选
                pwtitle.setText("优选");
                yxTv.setTextColor(Color.BLUE);
                qbTv.setTextColor(Color.GRAY);
                gzTv.setTextColor(Color.GRAY);
                //关闭PopWindow
               // pw.dismiss();
                break;
            case R.id.actor_qb_tv://全部
                pwtitle.setText("全部");
                qbTv.setTextColor(Color.BLUE);
                yxTv.setTextColor(Color.GRAY);
                gzTv.setTextColor(Color.GRAY);
                //关闭PopWindow
               // pw.dismiss();
                break;
            case R.id.actor_gz_tv://我的关注
                pwtitle.setText("我的关注");
                gzTv.setTextColor(Color.BLUE);
                qbTv.setTextColor(Color.GRAY);
                yxTv.setTextColor(Color.GRAY);
                //关闭PopWindow
              //  pw.dismiss();
                break;
        }
    }
}
