package com.heyu.spider.page.jdSort.entity;
import java.util.Date;

import java.io.Serializable;


public class DictFiled implements Serializable{

    /**
        
     */
    private Integer id;

    /**
        Ŀ¼ID
     */
    private Integer dictId;

    private String dictName;

    /**
        ������
     */
    private String filed;

    /**
        ����ֵ
     */
    private String filedValue;

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


    public Integer getDictId() {
        return dictId;
    }

    public void setDictId(Integer dictId) {
        this.dictId = dictId;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getFiled() {
        return filed;
    }

    public void setFiled(String filed) {
        this.filed = filed;
    }


    public String getFiledValue() {
        return filedValue;
    }

    public void setFiledValue(String filedValue) {
        this.filedValue = filedValue;
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
