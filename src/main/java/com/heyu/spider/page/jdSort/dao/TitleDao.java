package com.heyu.spider.page.jdSort.dao;

import com.heyu.spider.page.jdSort.entity.Title;
import org.apache.ibatis.annotations.Mapper;
import java.util.ArrayList;
import java.util.List;

@Mapper
public interface TitleDao{

    /**
     *  ����id����
     */
    public Title selectById(Integer id);

    /**
     *  �����¼
     */
    public Boolean insert(Title title);

    /**
     *  ����idɾ��
     */
    public Boolean deleteById(Integer id);
    /**
     *  ��������
     */
    public List<Title> findAllList();


    /**
    * ����
    *
    */
    public Boolean update(Title title);

    void insertBatch(ArrayList<Title> titles);
}
