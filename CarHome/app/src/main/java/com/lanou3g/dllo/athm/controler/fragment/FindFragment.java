package com.lanou3g.dllo.athm.controler.fragment;

import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.lanou3g.dllo.athm.R;
import com.lanou3g.dllo.athm.controler.adapter.listview_adapter.FindAdapter;
import com.lanou3g.dllo.athm.controler.adapter.rc_adapter.FindHeadGuideAdapter;
import com.lanou3g.dllo.athm.controler.adapter.rc_adapter.FindHeadLkAdapter;
import com.lanou3g.dllo.athm.controler.adapter.rc_adapter.FindHeadWwAdapter;
import com.lanou3g.dllo.athm.controler.adapter.rotate_vp_adapter.FindRotateVpAdapter;
import com.lanou3g.dllo.athm.model.bean.FindBean;
import com.lanou3g.dllo.athm.model.bean.FindHeadGuideBean;
import com.lanou3g.dllo.athm.model.bean.rotatebean.FindRotateBean;
import com.lanou3g.dllo.athm.model.net.VolleyInstance;
import com.lanou3g.dllo.athm.model.net.VolleyResult;
import com.lanou3g.dllo.athm.utils.UrlQuantity;
import com.lanou3g.dllo.athm.views.FindListView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/9.
 * 发现
 */
public class FindFragment extends AbsBaseFragment {
    //轮播图部分
    //停留的时间
    private  static  final  int TIME = 3000;
    //滑动的vp
    private ViewPager viewPager;
    //随着轮播改变的小点点
    private LinearLayout pointLl;
    //轮播图的滑动适配器
    private FindRotateVpAdapter vpAdapter;
    //轮播图的实体类
    private List<FindRotateBean> rotateDatas;

    //自定义的listView
    private FindListView listView;
    private FindAdapter adapter;
    private List<FindBean.ResultBean.GoodslistBean.ListBean> datas;
    //头布局的RcGDView
    private RecyclerView recyclerView;
    private List<FindHeadGuideBean.ResultBean.FunctionlistBean> gdData;
    private FindHeadGuideAdapter gdAdapter;
    private View headGdView;

    //头布局的猜你喜欢(LK)
    private RecyclerView lkRecyclerView;
    private List<FindBean.ResultBean.ModulelistBean.ListBean> lkData;
    private FindHeadLkAdapter lkAdapter;
    private View lkHeadView;
    //为我推荐
    private RecyclerView wwRecyclerView;
    private FindHeadWwAdapter wwAdapter;
    private View wwHeadView;

    @Override
    protected int setLayout() {
        return R.layout.find_fragment;
    }

    @Override
    protected void initView() {
        viewPager = byview(R.id.find_rotate_vp);
        pointLl = byview(R.id.find_rotate_point_container);
        listView = byview(R.id.find_lv);
    }

    @Override
    protected void initDatas() {
        //绑定listView适配器
        adapter = new FindAdapter(context);
        listView.setAdapter(adapter);
        //利用封装的网络工具类请求Listview最新的数据
        listviewFindVolley();

        //为listview添加导航头布局
        headGdView = LayoutInflater.from(context).inflate(R.layout.find_head_gd_rc,null);
        recyclerView = (RecyclerView) headGdView.findViewById(R.id.find_head_gd_rc);
        //利用网络请求rcGd数据
        rcGdHeadViewVolley();


        //添加猜你喜欢(lk)头布局
        lkHeadView = LayoutInflater.from(context).inflate(R.layout.find_head_lk_rc,null);
        lkRecyclerView = (RecyclerView) lkHeadView.findViewById(R.id.find_head_lk_rc);

        wwHeadView = LayoutInflater.from(context).inflate(R.layout.find_head_ww_rc,null);
        wwRecyclerView = (RecyclerView) wwHeadView.findViewById(R.id.find_head_ww_rc);
        //利用网络请求rcLk数据
        rcLkHeadViewVolley();

        //请求轮播图的数据
        buildDatas();
        //绑定vp适配器
        vpAdapter = new FindRotateVpAdapter(context);
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
    }

    /**
     * 解析设置头布局猜你喜欢
     */
    private void rcLkHeadViewVolley() {
        VolleyInstance.getInstance().startRequest(UrlQuantity.FIND, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                FindBean lkBean = gson.fromJson(resultStr,FindBean.class);
                lkData= lkBean.getResult().getModulelist().get(0).getList();
                //初始化适配器 绑定
                lkAdapter  = new FindHeadLkAdapter(context);
                lkAdapter.setDatas(lkData);
                GridLayoutManager lkManager = new GridLayoutManager(context,2);
                lkRecyclerView.setLayoutManager(lkManager);
                lkRecyclerView.setAdapter(lkAdapter);
                listView.addHeaderView(lkHeadView);

                FindBean wwBean = gson.fromJson(resultStr,FindBean.class);
                lkData= wwBean.getResult().getModulelist().get(1).getList();
                wwAdapter = new FindHeadWwAdapter(context);
                wwAdapter.setDatas(lkData);
                GridLayoutManager wwManager = new GridLayoutManager(context,2);
                wwRecyclerView.setLayoutManager(wwManager);
                wwRecyclerView.setAdapter(wwAdapter);
                listView.addHeaderView(wwHeadView);

            }

            @Override
            public void failure() {

            }
        });

    }

    /**
     * 解析设置头布局导航
     */
    private void rcGdHeadViewVolley() {
        VolleyInstance.getInstance().startRequest(UrlQuantity.FINDGD, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                FindHeadGuideBean gdBean = gson.fromJson(resultStr,FindHeadGuideBean.class);
                gdData = gdBean.getResult().getFunctionlist();
                //初始化Rc适配器 绑定
                gdAdapter = new FindHeadGuideAdapter(context);
                recyclerView.setAdapter(gdAdapter);
                gdAdapter.setDatas(gdData);
                GridLayoutManager manager = new GridLayoutManager(context,4);
                recyclerView.setLayoutManager(manager);
                listView.addHeaderView(headGdView);
            }

            @Override
            public void failure() {

            }
        });
    }

    //利用封装的网络工具类请求Listview最新的数据
    private void listviewFindVolley() {
        VolleyInstance.getInstance().startRequest(UrlQuantity.FIND, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                FindBean bean = gson.fromJson(resultStr,FindBean.class);
                datas = bean.getResult().getGoodslist().getList();
                adapter.setDatas(datas);
            }

            @Override
            public void failure() {

            }
        });
    }

    /**
     * 设置轮播的数据
     */
    private void buildDatas() {
        rotateDatas = new ArrayList<>();
        rotateDatas.add(new FindRotateBean("http://app2.autoimg.cn/appdfs/g18/M0A/4F/80/autohomecar__wKgH2VfjgrGAf45ZAAG4O89fQA0913.jpg"));
        rotateDatas.add(new FindRotateBean("http://app2.autoimg.cn/appdfs/g21/M0F/31/F4/autohomecar__wKjBwlfjgyWAb-8QAAIwQFHA0CQ346.jpg"));
        rotateDatas.add(new FindRotateBean("http://app2.autoimg.cn/appdfs/g22/M0C/32/2B/autohomecar__wKgFVlfjq4GAentxAAFe31duapA704.jpg"));
        rotateDatas.add(new FindRotateBean("http://app2.autoimg.cn/appdfs/g13/M00/50/63/autohomecar__wKgH1Ffjky2AZ11LAAJTMu2PiLQ967.jpg"));
        rotateDatas.add(new FindRotateBean("http://app2.autoimg.cn/appdfs/g11/M09/51/F6/autohomecar__wKjBzFfjbNqACW_YAAKKXo-uUyc629.jpg"));
    }

    /**
     * 设置轮播图选择器的小点
     */
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

}
