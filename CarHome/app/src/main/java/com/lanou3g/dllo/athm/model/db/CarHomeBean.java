package com.lanou3g.dllo.athm.model.db;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by dllo on 16/10/10.
 * 数据库收藏的实体类
 */
@Table("carhome")
public class CarHomeBean {
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;
    private int webUrlid;
    private String title;
    private String url;

    public CarHomeBean() {
    }

    public CarHomeBean(int webUrlid, String title, String url) {
        this.webUrlid = webUrlid;
        this.title = title;
        this.url = url;
    }

    public int getWebUrlid() {
        return webUrlid;
    }

    public void setWebUrlid(int webUrlid) {
        this.webUrlid = webUrlid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
