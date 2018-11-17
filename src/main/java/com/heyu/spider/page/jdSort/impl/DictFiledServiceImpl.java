package com.heyu.spider.page.jdSort.impl;

import com.heyu.spider.page.jdSort.api.IDictFiledService;
import com.heyu.spider.page.jdSort.dao.DictFiledDao;
import com.heyu.spider.page.jdSort.entity.DictFiled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("dictFiledService")
public class DictFiledServiceImpl implements IDictFiledService {

    @Autowired
    private DictFiledDao dictFiledDao;

    /**
     *  ����id����
     */
    public DictFiled getById(Integer id) {
        return dictFiledDao.findById(id);
    }

    /**
     *  �����¼
     */
    public Boolean insert(DictFiled dictFiled) {
        return dictFiledDao.insert(dictFiled);
    }

    /**
     *  ����idɾ��
     */
    public Boolean deleteById(Integer id) {
        return dictFiledDao.deleteById(id);
    }
    /**
     *  ��������
     */
    public List<DictFiled> findAllList() {
        return dictFiledDao.findAllList();
    }

    /**
    *   ��ҳ����
    */
    /*public PageData<DictFiled> findByPage(DictFiled dictFiled,PageUtils pageUtils){
        //��ȡ���ݿ�������
        Long count=dictFiledDao.getTotalCount(dictFiled);
        if(count == null || count==0){
            return PageData.EMPTY_PAGE;
        }
        List<DictFiled> resultList=dictFiledDao.findByCondition(pageUtils.getPage().intValue(),pageUtils.getRows(),dictFiled);
        return new PageData<DictFiled>(resultList,count);
    }*/

    public Boolean update(DictFiled dictFiled){
        return dictFiledDao.update(dictFiled);
    }


    public void setDictFiledDao(DictFiledDao dictFiledDao) {
        this.dictFiledDao = dictFiledDao;
    }
}