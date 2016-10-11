package com.lanou3g.dllo.athm.utils;

import android.view.View;

import com.lanou3g.dllo.athm.model.bean.FindCarBrandRcBean;

/**
 * Created by dllo on 16/8/23.
 * RecyclerView 行布局点击接口
 *
 */
public interface OnFindRvItemClick {
    /**
     *
     * @param position  点击的行布局位置
     *             行布局上的数据实体类
     *             如果适配器实体类是XXBean
     *             这里的String就换为XXBean
     */
    void onRvItemClickListener(int position,  FindCarBrandRcBean.ResultBean.ListBean bean);
}
