package com.lanou3g.dllo.athm.controler.adapter.listview_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lanou3g.dllo.athm.R;
import com.lanou3g.dllo.athm.model.bean.RecmdNewsBean;

import java.util.List;

/**
 * Created by dllo on 16/9/23.
 */
public class RecmdNewsAdapter extends BaseAdapter {
    private List<RecmdNewsBean.ResultBean.NewslistBean> datas;
    private Context context;

    public RecmdNewsAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<RecmdNewsBean.ResultBean.NewslistBean> datas) {
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
                    .inflate(R.layout.item_recmd_news, parent, false);
            //把加载出来的View传入缓存类去findViewById
            viewHolder = new ViewHolder(convertView);
            //Object的方法, setTag 保存某个属性
            //将viewHolder临时挂载在convertView上保存
            convertView.setTag(viewHolder);
        } else {
            //不是第一次生成行布局,开始复用
            viewHolder = (ViewHolder) convertView.getTag();

        }
        //设置组件的内容 (TextView设置文字符)
        //首先获取当前行的实体类,对应的位置去集合里获取
        RecmdNewsBean.ResultBean.NewslistBean bean = datas.get(position);
        if (bean != null){
            viewHolder.titleTv.setText(bean.getTitle());
            viewHolder.countTv.setText(bean.getReplycount() +""+"评论");
            viewHolder.timeTv.setText(bean.getTime());
            Glide.with(context).load(bean.getSmallpic()).into(viewHolder.iv);
        }
        return convertView;
    }

    class ViewHolder {
        TextView titleTv;
        TextView timeTv;
        TextView countTv;
        ImageView iv;

        public ViewHolder(View v) {
            titleTv = (TextView) v.findViewById(R.id.item_news_title_tv);
            timeTv = (TextView) v.findViewById(R.id.item_news_time_tv);
            countTv = (TextView) v.findViewById(R.id.item_news_count_tv);
            iv = (ImageView) v.findViewById(R.id.item_news_list_iv);
        }
    }
}
