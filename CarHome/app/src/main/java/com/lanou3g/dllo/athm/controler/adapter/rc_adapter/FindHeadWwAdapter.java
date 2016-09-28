package com.lanou3g.dllo.athm.controler.adapter.rc_adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lanou3g.dllo.athm.R;
import com.lanou3g.dllo.athm.model.bean.FindBean;

import java.util.List;

/**
 * Created by dllo on 16/9/27.
 */
public class FindHeadWwAdapter extends RecyclerView.Adapter<FindHeadWwAdapter.MyViewHolder>{
    private Context context;
    private List<FindBean.ResultBean.ModulelistBean.ListBean> datas;

    public FindHeadWwAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<FindBean.ResultBean.ModulelistBean.ListBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //布局初始化  从context获得一个布局加载器 加载布局 因为加载布局的方法是Activity才可以 所以利用context
         View view = LayoutInflater.from(context).inflate(R.layout.item_find_head_ww_rc, parent, false);
        //把布局里面的数据赋值缓存类里面
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        FindBean.ResultBean.ModulelistBean.ListBean bean = datas.get(position);
        holder.titleTv.setText(bean.getTitle());
        holder.contentTv.setText(bean.getShorttitle());
        holder.moneyTv.setText(bean.getPrice());
        holder.money2Tv.setText(bean.getFctprice());
        Glide.with(context).load(bean.getLogo()).into(holder.gdLogo);
    }

    @Override
    public int getItemCount() {
        return datas != null && datas.size() > 0 ? datas.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        //定义
        TextView titleTv,contentTv,moneyTv,money2Tv;
        ImageView gdLogo;

        public MyViewHolder(View itemView) {
            super(itemView);
            gdLogo = (ImageView) itemView.findViewById(R.id.item_find_head_ww_logo_iv);
            titleTv = (TextView) itemView.findViewById(R.id.item_find_head_ww_title_tv);
            contentTv = (TextView) itemView.findViewById(R.id.item_find_head_ww_content_tv);
            moneyTv = (TextView) itemView.findViewById(R.id.item_find_head_ww_money_tv);
            money2Tv = (TextView) itemView.findViewById(R.id.item_find_head_ww_money2_tv);
            money2Tv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }
}
