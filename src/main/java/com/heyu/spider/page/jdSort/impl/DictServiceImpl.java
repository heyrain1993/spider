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
    public PageData<Dict> findByPage(Dict dict,PageUtils pageUtils){
        //��ȡ���ݿ�������
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