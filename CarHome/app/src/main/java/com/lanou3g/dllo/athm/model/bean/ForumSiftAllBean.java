package com.lanou3g.dllo.athm.model.bean;

/**
 * Created by dllo on 16/9/12.
 */
public class ForumSiftAllBean  {
    private  String title;//标题
    private  String coutent;//内容
    private  String count;//计数
    private  int  imgId; //图片 R.mipmap.地址

    public ForumSiftAllBean(String title, String coutent, String count, int imgId) {
        this.title = title;
        this.coutent = coutent;
        this.count = count;
        this.imgId = imgId;
    }

    public ForumSiftAllBean() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoutent() {
        return coutent;
    }

    public void setCoutent(String coutent) {
        this.coutent = coutent;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}
