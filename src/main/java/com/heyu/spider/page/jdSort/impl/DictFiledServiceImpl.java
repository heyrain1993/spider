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
    public PageData<DictFiled> findByPage(DictFiled dictFiled,PageUtils pageUtils){
        //��ȡ���ݿ�������
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