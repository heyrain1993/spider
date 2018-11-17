package com.heyu.spider.page.jdSort.entity;
import java.util.Date;

import java.io.Serializable;


public class Title implements Serializable{

    /**
        ����ID
     */
    private Integer id;

    private Integer dictId;

    /**
        һ��Ŀ¼
     */
    private String firstTitle;

    /**
        ����Ŀ¼
     */
    private String secondTitle;

    /**
        ����Ŀ¼
     */
    private String thridTitle;

    /**
        ����ʱ��
     */
    private Date createTime;

    /**
        ����ʱ��
     */
    private Date updateTime;

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDictId() {
        return dictId;
    }

    public void setDictId(Integer dictId) {
        this.dictId = dictId;
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
