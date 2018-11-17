package com.heyu.spider.page.jdSort.entity;
import java.util.Date;

import java.io.Serializable;


public class Dict implements Serializable{

    /**
        ����ID
     */
    private Integer id;

    /**
        һ��Ŀ¼
     */
    private String firstDict;

    /**
        ����Ŀ¼
     */
    private String secondDict;

    /**
        ����Ŀ¼
     */
    private String thridDict;

    private String url;

    /**
        ����ʱ��
     */
    private Date createTime;

    /**
        ����ʱ��
     */
    private Date updateTime;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getFirstDict() {
        return firstDict;
    }

    public void setFirstDict(String firstDict) {
        this.firstDict = firstDict;
    }


    public String getSecondDict() {
        return secondDict;
    }

    public void setSecondDict(String secondDict) {
        this.secondDict = secondDict;
    }


    public String getThridDict() {
        return thridDict;
    }

    public void setThridDict(String thridDict) {
        this.thridDict = thridDict;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
