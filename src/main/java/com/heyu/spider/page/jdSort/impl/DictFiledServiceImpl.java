package com.heyu.spider.page.service.dict;

import com.heyu.spider.page.entity.dict.DictFiled;
import com.heyu.spider.page.api.dict.IDictFiledService;
import com.heyu.spider.page.dao.dict.DictFiledDao;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yunji.oms.stock.entity.common.PageData;
import com.yunji.oms.stock.entity.common.PageUtils;


@Service("dictFiledService")
public class DictFiledServiceImpl implements IDictFiledService {

    @Autowired
    private DictFiledDao dictFiledDao;

    /**
     *  根据id查找
     */
    public DictFiled getById(Integer id) {
        return dictFiledDao.findById(id);
    }

    /**
     *  插入记录
     */
    public Boolean insert(DictFiled dictFiled) {
        return dictFiledDao.insert(dictFiled);
    }

    /**
     *  根据id删除
     */
    public Boolean deleteById(Integer id) {
        return dictFiledDao.deleteById(id);
    }
    /**
     *  查找所有
     */
    public List<DictFiled> findAllList() {
        return dictFiledDao.findAllList();
    }

    /**
    *   分页查找
    */
    public PageData<DictFiled> findByPage(DictFiled dictFiled,PageUtils pageUtils){
        //获取数据库中总数
        Long count=dictFiledDao.getTotalCount(dictFiled);
        if(count == null || count==0){
            return PageData.EMPTY_PAGE;
        }
        List<DictFiled> resultList=dictFiledDao.findByCondition(pageUtils.getPage().intValue(),pageUtils.getRows(),dictFiled);
        return new PageData<DictFiled>(resultList,count);
    }

    public Boolean update(DictFiled dictFiled){
        return dictFiledDao.update(dictFiled);
    }


    public void setDictFiledDao(DictFiledDao dictFiledDao) {
        this.dictFiledDao = dictFiledDao;
    }
}