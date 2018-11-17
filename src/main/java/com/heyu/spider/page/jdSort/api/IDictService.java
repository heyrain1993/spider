package com.heyu.spider.page.api.dict;

import com.heyu.spider.page.entity.dict.Dict;

import java.util.List;
import com.yunji.oms.stock.entity.common.PageData;
import com.yunji.oms.stock.entity.common.PageUtils;


public interface IDictService {

    /**
     *  根据id查找
     */
    Dict getById(Integer id);

    /**
     *  插入记录
     */
    Boolean insert(Dict dict);

    /**
     *  根据id删除
     */
    Boolean deleteById(Integer id);

    /**
     *  查找所有
     */
    List<Dict> findAllList();

    /**
    *   分页查找
    */
    PageData<Dict> findByPage(Dict dict,PageUtils pageUtils);

    Boolean update(Dict dict);
}
