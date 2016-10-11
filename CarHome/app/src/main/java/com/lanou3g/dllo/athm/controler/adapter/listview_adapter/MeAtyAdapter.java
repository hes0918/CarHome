package com.lanou3g.dllo.athm.controler.adapter.listview_adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lanou3g.dllo.athm.R;
import com.lanou3g.dllo.athm.model.db.CarHomeBean;

import java.util.List;

/**
 * Created by dllo on 16/9/23.
 * 个人的收藏详情页面适配器
 */
public class MeAtyAdapter extends BaseAdapter {
    private List<CarHomeBean> datas;
    private Context context;

    public MeAtyAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<CarHomeBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return datas != null && datas.size() > 0 ? datas.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return datas != null && datas.size() > 0 ? datas.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //定义组件缓存类
        ViewHolder viewHolder = null;
        //第一次生成行布局;判断如果是null就加载
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.item_me_aty, parent, false);
            //把加载出来的View传入缓存类去findViewById
            viewHolder = new ViewHolder(convertView);
            //Object的方法, setTag 保存某个属性
            //将viewHolder临时挂载在convertView上保存
            convertView.setTag(viewHolder);
        } else {
            //不是第一次生成行布局,开始复用
            viewHolder = (ViewHolder) convertView.getTag();
        }
        CarHomeBean bean = datas.get(position);
        if (bean!=null){
            viewHolder.titleTv.setText(bean.getTitle());
            Log.d("MeAtyAdapter", bean.getTitle());
        }
        return convertView;
    }

    class ViewHolder {
        TextView titleTv;


        public ViewHolder(View v) {
            titleTv = (TextView) v.findViewById(R.id.item_me_aty_title_tv);
        }
    }

}