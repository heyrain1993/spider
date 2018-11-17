package com.heyu.spider.page.api.dict;

import com.heyu.spider.page.entity.dict.DictFiled;

import java.util.List;
import com.yunji.oms.stock.entity.common.PageData;
import com.yunji.oms.stock.entity.common.PageUtils;


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

    /**
    *   ��ҳ����
    */
    PageData<DictFiled> findByPage(DictFiled dictFiled,PageUtils pageUtils);

    Boolean update(DictFiled dictFiled);
}
