package com.heyu.spider.page.service.dict;

import com.heyu.spider.page.entity.dict.Dict;
import com.heyu.spider.page.api.dict.IDictService;
import com.heyu.spider.page.dao.dict.DictDao;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yunji.oms.stock.entity.common.PageData;
import com.yunji.oms.stock.entity.common.PageUtils;


@Service("dictService")
public class DictServiceImpl implements IDictService {

    @Autowired
    private DictDao dictDao;

    /**
     *  根据id查找
     */
    public Dict getById(Integer id) {
        return dictDao.findById(id);
    }

    /**
     *  插入记录
     */
    public Boolean insert(Dict dict) {
        return dictDao.insert(dict);
    }

    /**
     *  根据id删除
     */
    public Boolean deleteById(Integer id) {
        return dictDao.deleteById(id);
    }
    /**
     *  查找所有
     */
    public List<Dict> findAllList() {
        return dictDao.findAllList();
    }

    /**
    *   分页查找
    */
    public PageData<Dict> findByPage(Dict dict,PageUtils pageUtils){
        //获取数据库中总数
        Long count=dictDao.getTotalCount(dict);
        if(count == null || count==0){
            return PageData.EMPTY_PAGE;
        }
        List<Dict> resultList=dictDao.findByCondition(pageUtils.getPage().intValue(),pageUtils.getRows(),dict);
        return new PageData<Dict>(resultList,count);
    }

    public Boolean update(Dict dict){
        return dictDao.update(dict);
    }


    public void setDictDao(DictDao dictDao) {
        this.dictDao = dictDao;
    }
}