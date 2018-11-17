package com.heyu.spider.page.jdSort.api;


import com.heyu.spider.page.jdSort.entity.Dict;

import java.util.List;

public interface IDictService {

    /**
     *  ����id����
     */
    Dict getById(Integer id);

    /**
     *  �����¼
     */
    Boolean insert(Dict dict);

    /**
     *  ����idɾ��
     */
    Boolean deleteById(Integer id);

    /**
     *  ��������
     */
    List<Dict> findAllList();

    Boolean update(Dict dict);
}
