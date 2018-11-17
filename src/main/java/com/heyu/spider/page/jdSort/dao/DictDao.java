package com.heyu.spider.page.dao.dict;

import com.heyu.spider.page.entity.dict.Dict;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DictDao{

    @Autowired
    private SqlSession session;

    /**
     *  ����id����
     */
    public Dict findById(Integer id){
        return session.selectOne("DictMapper.selectById",id);
    }

    /**
     *  �����¼
     */
    public Boolean insert(Dict dict){
        return session.insert("DictMapper.insert",dict) > 0;
    }

    /**
     *  ����idɾ��
     */
    public Boolean deleteById(Integer id){
        return session.delete("DictMapper.deleteById",id) > 0;
    }
    /**
     *  ��������
     */
    public List<Dict> findAllList(){
        return session.selectList("DictMapper.findAllList");
    }

    /**
    * ��ҳ��ѯ�б�
    */
    public List<Dict> findByCondition(Integer pageNumber,Integer pageSize,Dict dict){
        Map<String ,Object> paramsMap = new HashMap<String,Object>(3);
        paramsMap.put("offset",(pageNumber-1)*pageSize);
        paramsMap.put("pageSize",pageSize);
        paramsMap.put("dict",dict);
        return session.selectList("DictMapper.findByCondition",paramsMap);
    }

    /**
    * ��ȡ��ҳ������
    */
    public Long getTotalCount(Dict dict){
        Map<String,Object> paramsMap = new HashMap<String,Object>(1);
        paramsMap.put("dict",dict);
        return session.selectOne("DictMapper.getTotalCount",paramsMap);
    }

    /**
    * ����
    *
    */
    public Boolean update(Dict dict){
        return session.update("DictMapper.update",dict) >0;
    }

    public void setSession(SqlSession session) {
        this.session = session;
    }
}
