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
import com.lanou3g.dllo.athm.model.bean.ForumSiftAllBean;

import java.util.List;

/**
 * Created by dllo on 16/9/12.Ò
 * 论坛-精贴-listview适配器
 */
public class ForumSiftAllAdapter extends BaseAdapter {
    private List<ForumSiftAllBean.ResultBean.ListBean> datas;
    private Context context;

    public ForumSiftAllAdapter(Context context) {
        this.context = context;
    }

    //set方法,将行布局数据传入
    public void setDatas(List<ForumSiftAllBean.ResultBean.ListBean> datas) {
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

    /**
     * 列表内容长什么样
     * 获得每一行内容的实体类
     * 获取行布局的数据
     * 参数position:当前位置(从0开始 是第几行)
     */
    @Override
    public Object getItem(int position) {
        return datas != null && datas.size() > 0 ? datas.get(position) : null;
    }

    /**
     * 获取当前是第几行
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 将实体类的内容设置到每一行的组件(TextView,ImageView等)中
     * getView方法:每一次都会执行
     * 为了避免多次的执行findviewById
     * 采取列表缓存优化方式:将组件缓存(只执行一次findView操作)
     * 之后的数据采取复用形式
     * <p/>
     * 参数1:当前是哪一行
     * 参数2:当前行布局视图
     * 参数3:ListView(容器 装着这些行布局的容器)
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //定义组件缓存类
        ViewHolder viewHolder = null;
        //第一次生成行布局;判断如果是null就加载
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.item_forum_sift, parent, false);
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
        ForumSiftAllBean.ResultBean.ListBean bean = datas.get(position);
        if (bean != null) {
            viewHolder.coutentTv.setText(bean.getBbsname());
            viewHolder.titleTv.setText(bean.getTitle());
//        viewHolder.countTv.setText(bean.getReplycounts());
            //Glide
            //能加载uri path file等格式的图片
            //谷歌推荐的图片加载
            //更流畅的加载gif
            Glide.with(context).load(bean.getSmallpic()).into(viewHolder.iv);
        }
        //将设置好的行布局返回给系统
        return convertView;
    }

    class ViewHolder {
        TextView titleTv;
        TextView coutentTv;
        TextView countTv;
        ImageView iv;

        public ViewHolder(View v) {
            titleTv = (TextView) v.findViewById(R.id.item_sift_title_tv);
            coutentTv = (TextView) v.findViewById(R.id.item_sift_content_tv);
            countTv = (TextView) v.findViewById(R.id.item_sift_count_tv);
            iv = (ImageView) v.findViewById(R.id.item_sift_list_iv);
        }
    }

}
