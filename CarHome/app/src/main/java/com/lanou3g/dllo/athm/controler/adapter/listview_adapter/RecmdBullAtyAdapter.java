package com.lanou3g.dllo.athm.controler.adapter.listview_adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lanou3g.dllo.athm.R;
import com.lanou3g.dllo.athm.model.bean.BullAtyBean;

import java.util.List;

/**
 * Created by dllo on 16/10/9.
 * 快报详情页的listView适配器
 */
public class RecmdBullAtyAdapter extends BaseAdapter{
    private List<BullAtyBean.ResultBean.MessagelistBean> datas;
    private Context context;

    public void setDatas(List<BullAtyBean.ResultBean.MessagelistBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public RecmdBullAtyAdapter(Context context) {
        this.context = context;
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
                    inflate(R.layout.item_bull_aty_list, parent, false);
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
        BullAtyBean.ResultBean.MessagelistBean bean = datas.get(position);
        if (bean != null){
            viewHolder.nameTv.setText(bean.getAuthorname());
            viewHolder.contentTv.setText(bean.getContent());
            viewHolder.timeTv.setText(bean.getPublishtime());
            viewHolder.pinglunTv.setText(bean.getReplycount() + "评论");
            viewHolder.zanTv.setText(bean.getUpcount() + "赞");
            //Glide
            //能加载uri path file等格式的图片
            //谷歌推荐的图片加载
            //更流畅的加载gif
            Glide.with(context).load(bean.getShareimg()).into(viewHolder.headImg);
            Glide.with(context).load(bean.getAttachments().get(0).getPicurl()).into(viewHolder.contentImg);

        }

        return convertView;
    }

    class ViewHolder{
        TextView nameTv,contentTv,zanTv,timeTv,pinglunTv;
        ImageView headImg,contentImg;
        ListView itemListView;

        public ViewHolder(View v){
            nameTv = (TextView) v.findViewById(R.id.item_bull_aty_name_tv);
            contentTv = (TextView) v.findViewById(R.id.item_bull_aty_content_tv);
            contentImg = (ImageView) v.findViewById(R.id.item_bull_aty_content_iv);
            headImg = (ImageView) v.findViewById(R.id.item_bull_aty_head_iv);
            zanTv = (TextView) v.findViewById(R.id.item_bull_aty_zan_tv);
            timeTv = (TextView) v.findViewById(R.id.item_bull_aty_time_tv);
            pinglunTv = (TextView) v.findViewById(R.id.item_bull_aty_pinglun_tv);
            itemListView = (ListView) v.findViewById(R.id.item_bull_aty_list_view);
        }
    }
}
