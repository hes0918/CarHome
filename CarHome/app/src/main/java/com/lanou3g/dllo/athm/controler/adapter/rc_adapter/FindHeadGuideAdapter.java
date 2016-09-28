package com.lanou3g.dllo.athm.controler.adapter.rc_adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lanou3g.dllo.athm.R;
import com.lanou3g.dllo.athm.model.bean.FindHeadGuideBean;

import java.util.List;

/**
 * Created by dllo on 16/9/27.
 */
public class FindHeadGuideAdapter extends RecyclerView.Adapter<FindHeadGuideAdapter.MyViewHolder>{
    private Context context;
    private List<FindHeadGuideBean.ResultBean.FunctionlistBean> datas;

    public FindHeadGuideAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<FindHeadGuideBean.ResultBean.FunctionlistBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //布局初始化  从context获得一个布局加载器 加载布局 因为加载布局的方法是Activity才可以 所以利用context
        final View view = LayoutInflater.from(context).inflate(R.layout.item_find_head_gd_rc, parent, false);
        //把布局里面的数据赋值缓存类里面
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        FindHeadGuideBean.ResultBean.FunctionlistBean bean = datas.get(position);
        holder.gdname.setText(bean.getTitle());
        Glide.with(context).load(bean.getIconurl()).into(holder.gdLogo);
    }

    @Override
    public int getItemCount() {
        return datas != null && datas.size() > 0 ? datas.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        //定义
        TextView gdname;
        ImageView gdLogo;

        public MyViewHolder(View itemView) {
            super(itemView);
            gdLogo = (ImageView) itemView.findViewById(R.id.find_gd_rc_head_iv);
            gdname = (TextView) itemView.findViewById(R.id.find_gd_rc_head_tv);
        }
    }
}
