package com.heyu.spider.page.dao.dict;

import com.heyu.spider.page.entity.dict.DictFiled;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DictFiledDao{

    @Autowired
    private SqlSession session;

    /**
     *  ����id����
     */
    public DictFiled findById(Integer id){
        return session.selectOne("DictFiledMapper.selectById",id);
    }

    /**
     *  �����¼
     */
    public Boolean insert(DictFiled dictFiled){
        return session.insert("DictFiledMapper.insert",dictFiled) > 0;
    }

    /**
     *  ����idɾ��
     */
    public Boolean deleteById(Integer id){
        return session.delete("DictFiledMapper.deleteById",id) > 0;
    }
    /**
     *  ��������
     */
    public List<DictFiled> findAllList(){
        return session.selectList("DictFiledMapper.findAllList");
    }

    /**
    * ��ҳ��ѯ�б�
    */
    public List<DictFiled> findByCondition(Integer pageNumber,Integer pageSize,DictFiled dictFiled){
        Map<String ,Object> paramsMap = new HashMap<String,Object>(3);
        paramsMap.put("offset",(pageNumber-1)*pageSize);
        paramsMap.put("pageSize",pageSize);
        paramsMap.put("dictFiled",dictFiled);
        return session.selectList("DictFiledMapper.findByCondition",paramsMap);
    }

    /**
    * ��ȡ��ҳ������
    */
    public Long getTotalCount(DictFiled dictFiled){
        Map<String,Object> paramsMap = new HashMap<String,Object>(1);
        paramsMap.put("dictFiled",dictFiled);
        return session.selectOne("DictFiledMapper.getTotalCount",paramsMap);
    }

    /**
    * ����
    *
    */
    public Boolean update(DictFiled dictFiled){
        return session.update("DictFiledMapper.update",dictFiled) >0;
    }

    public void setSession(SqlSession session) {
        this.session = session;
    }
}
