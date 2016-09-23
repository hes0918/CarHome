package com.lanou3g.dllo.athm.controler.adapter.listview_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lanou3g.dllo.athm.R;
import com.lanou3g.dllo.athm.model.bean.ForumHotBean;

import java.util.List;

/**
 * Created by dllo on 16/9/22.
 * 论坛热帖的适配器
 */
public class ForumHotAdapter extends BaseAdapter {
    private List<ForumHotBean.ResultBean.ListBean> datas;
    private Context context;

    public ForumHotAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<ForumHotBean.ResultBean.ListBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        //如果数据不为null并且个数大于0
        //ListView的行数就是数据集合的大小,否则为0行
        return datas != null && datas.size()>0? datas.size():0;
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
                    inflate(R.layout.item_forum_hot, parent, false);
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
        ForumHotBean.ResultBean.ListBean bean = datas.get(position);
        if (bean != null){
            viewHolder.titleTv.setText(bean.getTitle());
            viewHolder.idTv.setText(bean.getBbsname());
            viewHolder.timeTv.setText(bean.getLastreplydate());
            viewHolder.countTv.setText(bean.getReplycounts() + ""+ "回帖");
        }

        return convertView;
    }
    class ViewHolder {
        TextView titleTv,idTv,timeTv,countTv;
        public ViewHolder(View v) {
            titleTv = (TextView) v.findViewById(R.id.item_hot_title_tv);
            timeTv = (TextView) v.findViewById(R.id.item_hot_time_tv);
            idTv = (TextView) v.findViewById(R.id.item_hot_id_tv);
            countTv = (TextView) v.findViewById(R.id.item_hot_count_tv);
        }
    }
}
