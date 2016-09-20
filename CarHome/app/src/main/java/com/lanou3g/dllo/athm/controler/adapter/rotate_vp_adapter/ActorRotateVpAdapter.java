package com.lanou3g.dllo.athm.controler.adapter.rotate_vp_adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lanou3g.dllo.athm.R;
import com.lanou3g.dllo.athm.model.bean.rotatebean.ActorRotateBean;

import java.util.List;

/**
 * Created by dllo on 16/9/19.
 * 优创界面的轮播图vp
 */
public class ActorRotateVpAdapter extends PagerAdapter{
    private List<ActorRotateBean> rotateDatas;
    private Context context;
    private LayoutInflater inflater;



    public ActorRotateVpAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setRotateDatas(List<ActorRotateBean> rotateDatas) {
        this.rotateDatas = rotateDatas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        // 为了让ViewPager到最后一页不会像翻书一样回到第一页
        // 设置页数为int最大值,这样向下滑动永远都是下一页
        return rotateDatas == null ? 0 : Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // position是int最大值所以这里可能是几百甚至上千,因此取余避免数组越界
        int newPosition = position % rotateDatas.size();
        ActorRotateBean bean = rotateDatas.get(newPosition);
        View converView = inflater.inflate(R.layout.actor_coming,container,false);
        ImageView imageView = (ImageView) converView.findViewById(R.id.item_actor_iv);
        Glide.with(context).load(bean.getImgUrl()).into(imageView);
        container.addView(converView);
        return converView;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }
}
