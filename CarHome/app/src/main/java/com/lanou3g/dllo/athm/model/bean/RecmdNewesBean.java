package com.lanou3g.dllo.athm.model.bean;

/**
 * Created by dllo on 16/9/12.
 */
public class RecmdNewesBean {
    private  String title;//标题
    private  String time;//时间
    private  String count;//计数
    private  int  img; //图片 R.mipmap.地址

    public RecmdNewesBean(String title, String time, String count, int img) {
        this.title = title;
        this.time = time;
        this.count = count;
        this.img = img;
    }

    public RecmdNewesBean() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
