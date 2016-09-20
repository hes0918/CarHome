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
import com.lanou3g.dllo.athm.model.bean.RecmdBulletinBean;

import java.util.List;

/**
 * Created by dllo on 16/9/20.
 * 推荐=快报-listview适配器
 */
public class RecmdBulletinAdapter extends BaseAdapter {
    private List<RecmdBulletinBean.ResultBean.ListBean> datas;
    private Context context;

    public RecmdBulletinAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<RecmdBulletinBean.ResultBean.ListBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    /**
     * 列表有多少行 根据ListSize来决定的
     * 获取ListView的条目
     */
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
                    inflate(R.layout.item_recmd_bultin, parent, false);
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
        RecmdBulletinBean.ResultBean.ListBean bean = datas.get(position);
        if (bean != null){
            viewHolder.titleTv.setText(bean.getTitle());
            //因为数字是int需要转型
            viewHolder.watchTv.setText(String.valueOf(bean.getReviewcount())+"人浏览");
            viewHolder.timeTv.setText(bean.getCreatetime());
            Glide.with(context).load(bean.getImg()).into(viewHolder.countIv);
        }
        return convertView;
    }
    class ViewHolder{
        TextView titleTv,watchTv,timeTv;
        ImageView countIv;

        public ViewHolder(View v){
            titleTv = (TextView) v.findViewById(R.id.item_bulletin_title_tv);
            watchTv = (TextView) v.findViewById(R.id.item_bulletin_watch_tv);
            timeTv = (TextView) v.findViewById(R.id.item_bulletin_time_tv);
            countIv = (ImageView) v.findViewById(R.id.item_bulletin_content_iv);

        }
    }
}
