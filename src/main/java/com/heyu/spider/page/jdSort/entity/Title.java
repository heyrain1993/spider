package com.heyu.spider.page.entity.title;
import java.util.Date;

import java.io.Serializable;


public class Title implements Serializable{

    /**
        自增ID
     */
    private Integer id;

    /**
        一级目录
     */
    private String firstTitle;

    /**
        二级目录
     */
    private String secondTitle;

    /**
        三级目录
     */
    private String thridTitle;

    /**
        创建时间
     */
    private Date createTime;

    /**
        更新时间
     */
    private Date updateTime;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getFirstTitle() {
        return firstTitle;
    }

    public void setFirstTitle(String firstTitle) {
        this.firstTitle = firstTitle;
    }


    public String getSecondTitle() {
        return secondTitle;
    }

    public void setSecondTitle(String secondTitle) {
        this.secondTitle = secondTitle;
    }


    public String getThridTitle() {
        return thridTitle;
    }

    public void setThridTitle(String thridTitle) {
        this.thridTitle = thridTitle;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


}
