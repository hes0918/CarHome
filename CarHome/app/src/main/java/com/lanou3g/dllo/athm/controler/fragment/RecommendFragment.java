package com.lanou3g.dllo.athm.controler.fragment;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.lanou3g.dllo.athm.R;
import com.lanou3g.dllo.athm.controler.activity.RecmSerch;
import com.lanou3g.dllo.athm.controler.adapter.pager_adapter.RecmdPagerAdapter;
import com.lanou3g.dllo.athm.controler.fragment.recomdfrgmet.ActorFragment;
import com.lanou3g.dllo.athm.controler.fragment.recomdfrgmet.BulletinFragment;
import com.lanou3g.dllo.athm.controler.fragment.recomdfrgmet.NewestFragment;
import com.lanou3g.dllo.athm.controler.fragment.recomdfrgmet.NewsFragment;
import com.lanou3g.dllo.athm.controler.fragment.recomdfrgmet.VideoFragment;
import com.lanou3g.dllo.athm.utils.UrlQuantity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/9.
 * 推荐
 */
public class RecommendFragment extends AbsBaseFragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<AbsBaseFragment> fragments;
    private RecmdPagerAdapter adapter ;
    //搜索
    private ImageView imageView;




    @Override
    protected int setLayout() {
        return R.layout.recommend_frgment;
    }

    @Override
    protected void initView() {
        tabLayout = byview(R.id.recommend_tab);
        viewPager = byview(R.id.recommend_vp);
        imageView = byview(R.id.fragment_recommend_imageFind);

    }

   @Override
   protected void initDatas() {
       imageView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(context, RecmSerch.class);
               startActivity(intent);
           }
       });
       fragments = new ArrayList<>();
       fragments.add(NewestFragment.newInstance("最新"));
       fragments.add(ActorFragment.newInstance("优创"));
       fragments.add(BulletinFragment.newInstance("快报"));
       fragments.add(VideoFragment.newInstance("视频"));
       fragments.add(NewsFragment.newInstance(UrlQuantity.NEWS));
       fragments.add(NewsFragment.newInstance(UrlQuantity.PINGCE));
       fragments.add(NewsFragment.newInstance(UrlQuantity.DAOGOU));
       fragments.add(NewsFragment.newInstance(UrlQuantity.HANGQING));
       fragments.add(NewsFragment.newInstance(UrlQuantity.YONGCHE));
       fragments.add(NewsFragment.newInstance(UrlQuantity.JISHU));
       fragments.add(NewsFragment.newInstance(UrlQuantity.WENHUA));
       fragments.add(NewsFragment.newInstance(UrlQuantity.GAIZHUANG));
       fragments.add(NewsFragment.newInstance(UrlQuantity.YOUJI));
       fragments.add(NewsFragment.newInstance("原创视频"));
       fragments.add(NewsFragment.newInstance(UrlQuantity.SHUOKE));

       adapter = new RecmdPagerAdapter(getChildFragmentManager());
       adapter.setFragments(fragments);
//        绑定适配器
        viewPager.setAdapter(adapter);
//        viewPager和tablayout联动
        tabLayout.setupWithViewPager(viewPager);
        //设置标签长什么样
        tabLayout.getTabAt(0).setText("最新");
        tabLayout.getTabAt(1).setText("优创+");
        tabLayout.getTabAt(2).setText("快报");
        tabLayout.getTabAt(3).setText("视频");
        tabLayout.getTabAt(4).setText("新闻");
        tabLayout.getTabAt(5).setText("评测");
        tabLayout.getTabAt(6).setText("导购");
        tabLayout.getTabAt(7).setText("行情");
        tabLayout.getTabAt(8).setText("用车");
        tabLayout.getTabAt(9).setText("技术");
        tabLayout.getTabAt(10).setText("文化");
        tabLayout.getTabAt(11).setText("改装");
        tabLayout.getTabAt(12).setText("游记");
        tabLayout.getTabAt(13).setText("原创视频");
        tabLayout.getTabAt(14).setText("说客");

    }


}
