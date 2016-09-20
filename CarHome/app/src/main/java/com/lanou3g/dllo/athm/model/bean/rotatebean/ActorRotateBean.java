package com.lanou3g.dllo.athm.model.bean.rotatebean;

import java.io.Serializable;

/**
 * Created by dllo on 16/9/18.
 */
public class ActorRotateBean implements Serializable {
    private int imgId;
    private String imgUrl;

    public ActorRotateBean() {
    }

    public ActorRotateBean(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public ActorRotateBean(int imgId) {
        this.imgId = imgId;
    }

    public ActorRotateBean(int imgId, String imgUrl) {
        this.imgId = imgId;
        this.imgUrl = imgUrl;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
