package com.lanou3g.dllo.athm.controler.fragment.recomdfrgmet;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.google.gson.Gson;
import com.lanou3g.dllo.athm.R;
import com.lanou3g.dllo.athm.controler.activity.RecmNewesAty;
import com.lanou3g.dllo.athm.views.HomeListView;
import com.lanou3g.dllo.athm.controler.adapter.listview_adapter.RecmdNewesAdapter;
import com.lanou3g.dllo.athm.controler.adapter.rotate_vp_adapter.NewestRotateVpAdapter;
import com.lanou3g.dllo.athm.controler.fragment.AbsBaseFragment;
import com.lanou3g.dllo.athm.model.bean.RecmdNewesBean;
import com.lanou3g.dllo.athm.model.bean.rotatebean.RotateBean;
import com.lanou3g.dllo.athm.model.net.VolleyInstance;
import com.lanou3g.dllo.athm.model.net.VolleyResult;
import com.lanou3g.dllo.athm.utils.UrlQuantity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/9.
 * 推荐-最新页面
 */
public class NewestFragment extends AbsBaseFragment {
    private RecmdNewesAdapter adapter;//listview适配器
    private HomeListView listView;

    //轮播图部分
    private  static  final  int TIME = 3000;
    private ViewPager viewPager;
    private LinearLayout pointLl;//随着轮播图改变的小圆点
    //轮播图的实体类
    private List<RotateBean> rotateDatas;
    //轮播图的滑动适配器
    private NewestRotateVpAdapter vpAdapter;

    //new一个单例
    public static NewestFragment newInstance(String str) {
        Bundle args = new Bundle();
        //将String存储到Bundle中
        //Bundle是一个轻量级的存储类
        args.putString("text",str);

        NewestFragment fragment = new NewestFragment();
        //把这个数据创给fragment
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int setLayout() {
        return R.layout.newest_fragment;
    }

    @Override
    protected void initView() {
        //初始化
        listView = byview(R.id.newest_list_view);
        viewPager = byview(R.id.rotate_vp);
        pointLl = byview(R.id.rotate_point_container);
    }

    @Override
    protected void initDatas() {
        //在onActivityCreated中接收上面的传值   必须在这里接收
        //因为给Fragment传值的是Activity
        //只有这个方法才能接到
        //取值
        Bundle bunble = getArguments();
        String string = bunble.getString("text");
        //绑定listview适配器
        adapter = new RecmdNewesAdapter(context);
        listView.setAdapter(adapter);
        //利用封装的网络工具类请求Listview最新的数据
        listviewNewestVolley();
        //请求轮播图的数据
        buildDatas();
        //绑定vp适配器
        vpAdapter = new NewestRotateVpAdapter(context);
        viewPager.setAdapter(vpAdapter);
        //设置数据
        vpAdapter.setDatas(rotateDatas);
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
        View headView = LayoutInflater.from(context).inflate(R.layout.newest_coming,null);
        listView.addHeaderView(headView);
        //为ListView设置行布局点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                RecmdNewesBean.ResultBean.NewslistBean
                        bean  = (RecmdNewesBean.ResultBean.NewslistBean) parent.getItemAtPosition(position);
                int a = bean.getId();
                String t = bean.getTitle();
                Intent intent = new Intent(context, RecmNewesAty.class);
                intent.putExtra("id",a);
                intent.putExtra("title",t);
                startActivity(intent);
            }
        });
    }



    /**
    设置数据
     */
    private void buildDatas() {
        rotateDatas = new ArrayList<>();
        rotateDatas.add(new RotateBean("http://www2.autoimg.cn/newsdfs/g23/M09/28/5B/640x320_0_autohomecar__wKgFV1fcC3mAIoqiAAmugQfTvY4736.jpg"));
        rotateDatas.add(new RotateBean("http://www3.autoimg.cn/newsdfs/g13/M10/4B/6A/640x320_0_autohomecar__wKgH41fdOSiAAjDkAAhilw2PCM4796.jpg"));
        rotateDatas.add(new RotateBean("http://www3.autoimg.cn/newsdfs/g8/M15/4D/90/640x320_0_autohomecar__wKgH3lfdCnWANHYgAAiBTnbuRQ4923.jpg"));
        rotateDatas.add(new RotateBean("http://www3.autoimg.cn/newsdfs/g14/M15/37/2F/640x320_0_autohomecar__wKjByVfWG7qADbFnAAL6kfmt2pw883.jpg"));
        rotateDatas.add(new RotateBean("http://www2.autoimg.cn/newsdfs/g22/M0C/24/DD/640x320_0_autohomecar__wKgFVlfZeoSADAEWAAK5C6osmFI085.jpg"));
        rotateDatas.add(new RotateBean("http://www2.autoimg.cn/newsdfs/g12/M08/45/48/640x320_0_autohomecar__wKjBy1fZZ-aAS23SAAYJiOPlwTk412.jpg"));
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


    /**
     解析listview新闻
     */
    private void listviewNewestVolley() {
        VolleyInstance.getInstance().startRequest(UrlQuantity.NEWESTURL, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                //解析
                Gson gson = new Gson();
                RecmdNewesBean bean = gson.fromJson(resultStr,RecmdNewesBean.class);
                List<RecmdNewesBean.ResultBean.NewslistBean> datas = bean.getResult().getNewslist();
                adapter.setDatas(datas);
            }

            @Override
            public void failure() {

            }
        });
    }


}
