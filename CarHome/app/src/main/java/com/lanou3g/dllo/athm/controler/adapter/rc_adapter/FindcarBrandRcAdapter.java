package com.lanou3g.dllo.athm.controler.adapter.rc_adapter;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.lanou3g.dllo.athm.R;
import com.lanou3g.dllo.athm.controler.fragment.findcarfragment.BrandFragment;
import com.lanou3g.dllo.athm.model.bean.FindCarBrandRcBean;
import com.lanou3g.dllo.athm.utils.OnFindRvItemClick;
import com.lanou3g.dllo.athm.utils.OnRvItemClick;

import java.util.List;


/**
 * Created by dllo on 16/9/22.
 * 头布局的RC适配器
 */
public class FindcarBrandRcAdapter extends RecyclerView.Adapter<FindcarBrandRcAdapter.MyViewHolder>  {

    private Context context;
    private List<FindCarBrandRcBean.ResultBean.ListBean> datas;
    private int p;

    //定义点击接口对象
    private OnFindRvItemClick onRvItemClick;
    //提供set方法


    public void setOnRvItemClick(OnFindRvItemClick onRvItemClick) {
        this.onRvItemClick = onRvItemClick;
    }

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
    public void onBindViewHolder(final MyViewHolder holder,final int position) {

        holder.name.setText(datas.get(position).getName());
        Glide.with(context).load(datas.get(position).getImg()).into(holder.img);
        //设置itemView(convertView)的点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断自定义点击接口不为null执行
                if (onRvItemClick != null) {
                    //获取当前行布局的position
                    p = holder.getLayoutPosition();
                    notifyDataSetChanged();
                    FindCarBrandRcBean.ResultBean.ListBean bean = datas.get(position);
                    //将数据存储到接口对象中
                    //回调接口方法-发出命令-执行setOnRvItemClick方法
                    onRvItemClick.onRvItemClickListener(p,bean);
                }
            }
        });
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
