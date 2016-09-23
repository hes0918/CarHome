package com.lanou3g.dllo.athm.controler.adapter.listview_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou3g.dllo.athm.R;
import com.lanou3g.dllo.athm.model.bean.FindCarBrandBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/9/21.
 * 品牌页适配器
 */
public class FindCarBrandAdapter extends BaseAdapter {
    private List<FindCarBrandBean.ResultBean.BrandlistBean.ListBean> datas;
    private Context context;

    public FindCarBrandAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<FindCarBrandBean.ResultBean.BrandlistBean.ListBean> datas) {
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
//        //定义组件缓存类
//        ViewHolder viewHolder = null;
//        //第一次生成行布局;判断如果是null就加载
//        if (convertView == null) {
        convertView = LayoutInflater.from(context).
                inflate(R.layout.item_findcar_brand, parent, false);
//            //把加载出来的View传入缓存类去findViewById
//            viewHolder = new ViewHolder(convertView);
//            //Object的方法, setTag 保存某个属性
//            //将viewHolder临时挂载在convertView上保存
//            convertView.setTag(viewHolder);
//        } else {
//            //不是第一次生成行布局,开始复用
//            viewHolder = (ViewHolder) convertView.getTag();
//        }
//        //设置组件的内容
//        //获取当前行的实体类 对应的位置去集合获取
        FindCarBrandBean.ResultBean.BrandlistBean.ListBean bean = datas.get(position);
//        if (bean != null) {
        ((TextView) convertView.findViewById(R.id.item_brand_tv)).setText(bean.getName());
//        Glide.with(context).load(bean.getImgurl()).into((ImageView) convertView.findViewById(R.id.item_brand_iv));
        Picasso.with(context).load(bean.getImgurl()).resize(50,50).into((ImageView) convertView.findViewById(R.id.item_brand_iv));
//        }
        return convertView;
    }

    class ViewHolder {
        TextView brandTv;
        ImageView brandIv;

        public ViewHolder(View v) {
            brandTv = (TextView) v.findViewById(R.id.item_brand_tv);
            brandIv = (ImageView) v.findViewById(R.id.item_brand_iv);
        }
    }
}
