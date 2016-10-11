package com.lanou3g.dllo.athm.controler.adapter.listview_adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lanou3g.dllo.athm.R;
import com.lanou3g.dllo.athm.model.bean.FindCarBrandDrawerBean;

import java.util.List;

/**
 * Created by dllo on 16/9/30.
 */
public class FindCarDrawerAdapter extends BaseAdapter {
    private List<FindCarBrandDrawerBean.ResultBean.FctlistBean.SerieslistBean> datas;
    private Context context;

    public FindCarDrawerAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<FindCarBrandDrawerBean.ResultBean.FctlistBean.SerieslistBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        //如果数据不为null并且个数大于0
        //ListView的行数就是数据集合的大小,否则为0行
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
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.item_findcar_brand_drawer, parent, false);
            //把加载出来的View传入缓存类去findViewById
            viewHolder = new ViewHolder(convertView);
            //Object的方法, setTag 保存某个属性
            //将viewHolder临时挂载在convertView上保存
            convertView.setTag(viewHolder);
        } else {
            //不是第一次生成行布局,开始复用
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //设置组件的内容
        //获取当前行的实体类 对应的位置去集合获取
        FindCarBrandDrawerBean.ResultBean.FctlistBean.SerieslistBean bean = datas.get(position);
        Log.d("FindCarDrawerAdapter", bean.getName());

        if (bean != null){
            viewHolder.titleTv.setText(bean.getName());
            viewHolder.moneyTv.setText(bean.getPrice());
            Glide.with(context).load(bean.getImgurl()).into(viewHolder.iv);
        }
        return convertView;
    }
    class ViewHolder {
        TextView titleTv;
        TextView moneyTv;
        ImageView iv;
        public ViewHolder(View v) {
            titleTv = (TextView) v.findViewById(R.id.item_branddrawer_title);
            moneyTv = (TextView) v.findViewById(R.id.item_branddrawer_money);
            iv = (ImageView) v.findViewById(R.id.item_branddrawer_img);
        }
    }

}
