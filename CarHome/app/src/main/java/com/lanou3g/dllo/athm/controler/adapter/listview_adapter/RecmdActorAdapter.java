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
import com.lanou3g.dllo.athm.model.bean.RecmdActorBean;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dllo on 16/9/18.
 */
public class RecmdActorAdapter extends BaseAdapter{
    private List<RecmdActorBean.ResultBean.NewslistBean> datas;
    private Context context;

    public RecmdActorAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<RecmdActorBean.ResultBean.NewslistBean> datas) {
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
                    .inflate(R.layout.item_recmd_actor, parent, false);
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
        RecmdActorBean.ResultBean.NewslistBean bean = datas.get(position);
        if (bean != null){
            viewHolder.titleTv.setText(bean.getUsername());
            viewHolder.timeTv.setText(bean.getPublishtime());
            viewHolder.contentTv.setText(bean.getTitle());
            //Glide 加载图片
            Glide.with(context).load(bean.getUserpic()).into(viewHolder.headIv);
            Glide.with(context).load(bean.getIndexdetail().get(0)).into(viewHolder.contentIv);
        }
        return convertView;
    }
    class ViewHolder{
        ImageView contentIv;
        CircleImageView headIv;
        TextView titleTv,contentTv,timeTv,goodTv;
        public  ViewHolder(View v){
            headIv = (CircleImageView) v.findViewById(R.id.item_ac_head_iv);
            contentIv= (ImageView) v.findViewById(R.id.item_ac_content_iv);
            titleTv= (TextView) v.findViewById(R.id.item_ac_title_tv);
            contentTv= (TextView) v.findViewById(R.id.item_ac_content_tv);
            timeTv= (TextView) v.findViewById(R.id.item_ac_time_tv);
            goodTv= (TextView) v.findViewById(R.id.item_ac_good_tv);
        }
    }
}
