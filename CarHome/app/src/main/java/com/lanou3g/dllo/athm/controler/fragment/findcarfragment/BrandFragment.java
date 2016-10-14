package com.lanou3g.dllo.athm.controler.fragment.findcarfragment;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lanou3g.dllo.athm.R;
import com.lanou3g.dllo.athm.controler.adapter.listview_adapter.FindCarBrandAdapter;
import com.lanou3g.dllo.athm.controler.adapter.listview_adapter.FindCarDrawerAdapter;
import com.lanou3g.dllo.athm.controler.adapter.rc_adapter.FindcarBrandRcAdapter;
import com.lanou3g.dllo.athm.controler.fragment.AbsBaseFragment;
import com.lanou3g.dllo.athm.model.bean.FindCarBrandBean;
import com.lanou3g.dllo.athm.model.bean.FindCarBrandDrawerBean;
import com.lanou3g.dllo.athm.model.bean.FindCarBrandRcBean;
import com.lanou3g.dllo.athm.model.bean.ForumSiftAllBean;
import com.lanou3g.dllo.athm.model.net.VolleyInstance;
import com.lanou3g.dllo.athm.model.net.VolleyResult;
import com.lanou3g.dllo.athm.utils.OnFindRvItemClick;
import com.lanou3g.dllo.athm.utils.UrlQuantity;
import com.lanou3g.dllo.athm.views.FindCarBrandListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/10.
 * 找车 品牌页面
 */
public class BrandFragment extends AbsBaseFragment {
    private ListView listView;
    private FindCarBrandAdapter adapter;
    private List<FindCarBrandBean.ResultBean.BrandlistBean> datas;
    //RC实体类
    private List<FindCarBrandRcBean.ResultBean.ListBean> rcData;
    //RC适配器
    private FindcarBrandRcAdapter rcAdapter;

    //头布局的rcView
    private RecyclerView recyclerView;
    private View headView;
    private View oneHeadView;
    //点击头布局rcView的抽屉
    private DrawerLayout rootView;
    private LinearLayout drawerView;
    //抽屉里面的listView
    private ListView drawerListView;
    private FindCarDrawerAdapter drawerAdapter;
    private List<FindCarBrandDrawerBean.ResultBean.FctlistBean.SerieslistBean> drawerData = new ArrayList<>();


    public static BrandFragment newInstance(String str) {

        Bundle args = new Bundle();
        //将String存储到Bundle中
        //Bundle是一个轻量级的存储类
        args.putString("text", str);
        BrandFragment fragment = new BrandFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.findcar_brand_fragment;
    }

    @Override
    protected void initView() {
        listView = byview(R.id.brand_list_view);
        rootView = byview(R.id.find_root_view);
        drawerView = byview(R.id.find_drawer_view);
        drawerListView = byview(R.id.find_drawer_list);
    }

    @Override
    protected void initDatas() {
        //在onActivityCreated中接收上面的传值   必须在这里接收
        //因为给Fragment传值的是Activity
        //只有这个方法才能接到
        //取值
        Bundle bunble = getArguments();

        /**
         * 1.ListView处理
         *  初始化,绑定适配器
         * 2.网络请求
         *  设置ListView的适配器数据
         *  头布局设置数据,加到ListView
         */

        //绑定listview适配器
        adapter = new FindCarBrandAdapter(context);
        listView.setAdapter(adapter);

        //利用封装的网络工具类请求Listview最新的数据
        listviewNewestVolley();
         //为listview添加菜单头布局
        oneHeadView = LayoutInflater.from(context).inflate(R.layout.brand_head_rc1,null);
        /************************/
        //这里记得初始化组件和写菜单的点击事件
        /************************/
        listView.addHeaderView(oneHeadView);


        //为listview添加RC品牌的头布局
        headView = LayoutInflater.from(context).inflate(R.layout.findcar_brand_head, null);
        recyclerView = (RecyclerView) headView.findViewById(R.id.brand_head_rc);
        //初始化RC适配器 绑定适配器
        rcAdapter = new FindcarBrandRcAdapter(context);
        recyclerView.setAdapter(rcAdapter);
        GridLayoutManager manager = new GridLayoutManager(context, 5);
        //绑定布局管理器
        recyclerView.setLayoutManager(manager);
        listView.addHeaderView(headView);

        //利用封装的网络工具类请求Rc品牌最新的数据
        rcOtherViewVolley();

        //为RecyclerView设置行布局点击事件
        rcAdapter.setOnRvItemClick(new OnFindRvItemClick() {
            @Override
            public void onRvItemClickListener(final int position, FindCarBrandRcBean.ResultBean.ListBean bean) {
                Log.d("BrandFragment", "bean:" +123);
                rootView.openDrawer(drawerView);
                String a = bean.getId()+"";
                String url = "http://app.api.autohome.com.cn/autov5.0.0/cars/seriesprice-pm1-b"
                        + a + "-t1.json";
                //抽屉的适配器
                drawerAdapter = new FindCarDrawerAdapter(context);
                drawerListView.setAdapter(drawerAdapter);

                //网络工具获取抽屉数据
                VolleyInstance.getInstance().startRequest(url, new VolleyResult() {
                    @Override
                    public void success(String resultStr) {
                        Gson gson = new Gson();
                        //解析
                        FindCarBrandDrawerBean drawerBean = gson.fromJson(resultStr,FindCarBrandDrawerBean.class);
                        List<FindCarBrandDrawerBean.ResultBean.FctlistBean> beans = drawerBean.getResult().getFctlist();
                        for (int i = 0; i < beans.size(); i++) {
                            List<FindCarBrandDrawerBean.ResultBean.FctlistBean.SerieslistBean> d =
                                    beans.get(i).getSerieslist();
                            drawerData.addAll(d);
                        }
                        Log.d("BrandFragment", "drawerData:" + drawerData.get(2).getImgurl());
                        drawerAdapter.setDatas(drawerData);
                    }

                    @Override
                    public void failure() {

                    }
                });

            }
        });

    }

    //利用封装的网络工具类请求Rc品牌最新的数据
    private void rcOtherViewVolley() {
        VolleyInstance.getInstance().startRequest(UrlQuantity.BRANDURLPINPAI, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                //解析
                Gson gson = new Gson();
                FindCarBrandRcBean rcbean = gson.fromJson(resultStr, FindCarBrandRcBean.class);
                rcData = rcbean.getResult().getList();
                rcAdapter.setDatas(rcData);
            }
            @Override
            public void failure() {

            }
        });
    }

    //利用封装的网络工具类请求Listview最新的数据
    private void listviewNewestVolley() {
        VolleyInstance.getInstance().startRequest(UrlQuantity.BRANDURL, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                //解析
                Gson gson = new Gson();
                FindCarBrandBean bean = gson.fromJson(resultStr, FindCarBrandBean.class);
                datas = bean.getResult().getBrandlist();
                List<FindCarBrandBean.ResultBean.BrandlistBean.ListBean> itemDatas = new ArrayList<FindCarBrandBean.ResultBean.BrandlistBean.ListBean>();

                for (int i = 0; i < datas.size(); i++) {
                    for (int j = 0; j < datas.get(i).getList().size(); j++) {
                        itemDatas.add(datas.get(i).getList().get(j));
                    }
                }
                adapter.setDatas(itemDatas);

            }

            @Override
            public void failure() {

            }
        });
    }
}
