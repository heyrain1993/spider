package com.heyu.spider.page.jdSort.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.heyu.spider.page.jdSort.api.IDictService;
import com.heyu.spider.page.jdSort.dao.DictDao;
import com.heyu.spider.page.jdSort.entity.Dict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("dictService")
public class DictServiceImpl implements IDictService {

    @Autowired
    private DictDao dictDao;

    /**
     *  ����id����
     */
    public Dict getById(Integer id) {
        return dictDao.findById(id);
    }

    /**
     *  �����¼
     */
    public Boolean insert(Dict dict) {
        return dictDao.insert(dict);
    }

    /**
     *  ����idɾ��
     */
    public Boolean deleteById(Integer id) {
        return dictDao.deleteById(id);
    }
    /**
     *  ��������
     */
    public List<Dict> findAllList() {
        return dictDao.findAllList();
    }

    /**
    *   ��ҳ����
    */
    /*public PageData<Dict> findByPage(Dict dict,PageUtils pageUtils){
        //��ȡ���ݿ�������
        Long count=dictDao.getTotalCount(dict);
        if(count == null || count==0){
            return PageData.EMPTY_PAGE;
        }
        List<Dict> resultList=dictDao.findByCondition(pageUtils.getPage().intValue(),pageUtils.getRows(),dict);
        return new PageData<Dict>(resultList,count);
    }*/

    public Boolean update(Dict dict){
        return dictDao.update(dict);
    }


    public void setDictDao(DictDao dictDao) {
        this.dictDao = dictDao;
    }
}