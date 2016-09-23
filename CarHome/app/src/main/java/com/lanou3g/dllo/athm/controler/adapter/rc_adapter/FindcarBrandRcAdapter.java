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
import com.lanou3g.dllo.athm.model.bean.FindCarBrandRcBean;

import java.util.List;


/**
 * Created by dllo on 16/9/22.
 * 头布局的RC适配器
 */
public class FindcarBrandRcAdapter extends RecyclerView.Adapter<FindcarBrandRcAdapter.MyViewHolder>  {

    private Context context;
    private List<FindCarBrandRcBean.ResultBean.ListBean> datas;

    public FindcarBrandRcAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<FindCarBrandRcBean.ResultBean.ListBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //布局初始化  从context获得一个布局加载器 加载布局 因为加载布局的方法是Activity才可以 所以利用context
        final View view = LayoutInflater.from(context).inflate(R.layout.brand_head_rc2, parent, false);
        //把布局里面的数据赋值缓存类里面
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        FindCarBrandRcBean.ResultBean.ListBean bean = datas.get(position);
           holder.name.setText(bean.getName());
        Glide.with(context).load(bean.getImg()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return datas != null && datas.size() > 0 ? datas.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        //定义
        TextView name;
        ImageView img;

        public MyViewHolder(View itemView) {
            super(itemView);
            //初始化
            name = (TextView) itemView.findViewById(R.id.brand_rc_name_tv);
            img = (ImageView) itemView.findViewById(R.id.brand_rc_head_iv);
        }
    }
}
