package com.heyu.spider.page.api.dict;

import com.heyu.spider.page.entity.dict.Dict;

import java.util.List;
import com.yunji.oms.stock.entity.common.PageData;
import com.yunji.oms.stock.entity.common.PageUtils;


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

    /**
    *   ��ҳ����
    */
    PageData<Dict> findByPage(Dict dict,PageUtils pageUtils);

    Boolean update(Dict dict);
}
