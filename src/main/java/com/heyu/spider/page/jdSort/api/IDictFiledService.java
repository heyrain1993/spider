package com.heyu.spider.page.api.dict;

import com.heyu.spider.page.entity.dict.DictFiled;

import java.util.List;
import com.yunji.oms.stock.entity.common.PageData;
import com.yunji.oms.stock.entity.common.PageUtils;


public interface IDictFiledService {

    /**
     *  根据id查找
     */
    DictFiled getById(Integer id);

    /**
     *  插入记录
     */
    Boolean insert(DictFiled dictFiled);

    /**
     *  根据id删除
     */
    Boolean deleteById(Integer id);

    /**
     *  查找所有
     */
    List<DictFiled> findAllList();

    /**
    *   分页查找
    */
    PageData<DictFiled> findByPage(DictFiled dictFiled,PageUtils pageUtils);

    Boolean update(DictFiled dictFiled);
}
