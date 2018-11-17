package com.heyu.spider.page.jdSort.api;


import com.heyu.spider.page.jdSort.entity.DictFiled;

import java.util.List;

public interface IDictFiledService {

    /**
     *  ����id����
     */
    DictFiled getById(Integer id);

    /**
     *  �����¼
     */
    Boolean insert(DictFiled dictFiled);

    /**
     *  ����idɾ��
     */
    Boolean deleteById(Integer id);

    /**
     *  ��������
     */
    List<DictFiled> findAllList();

    Boolean update(DictFiled dictFiled);
}
