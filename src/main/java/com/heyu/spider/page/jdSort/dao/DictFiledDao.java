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
     *  根据id查找
     */
    public DictFiled findById(Integer id){
        return session.selectOne("DictFiledMapper.selectById",id);
    }

    /**
     *  插入记录
     */
    public Boolean insert(DictFiled dictFiled){
        return session.insert("DictFiledMapper.insert",dictFiled) > 0;
    }

    /**
     *  根据id删除
     */
    public Boolean deleteById(Integer id){
        return session.delete("DictFiledMapper.deleteById",id) > 0;
    }
    /**
     *  查找所有
     */
    public List<DictFiled> findAllList(){
        return session.selectList("DictFiledMapper.findAllList");
    }

    /**
    * 分页查询列表
    */
    public List<DictFiled> findByCondition(Integer pageNumber,Integer pageSize,DictFiled dictFiled){
        Map<String ,Object> paramsMap = new HashMap<String,Object>(3);
        paramsMap.put("offset",(pageNumber-1)*pageSize);
        paramsMap.put("pageSize",pageSize);
        paramsMap.put("dictFiled",dictFiled);
        return session.selectList("DictFiledMapper.findByCondition",paramsMap);
    }

    /**
    * 获取分页的总数
    */
    public Long getTotalCount(DictFiled dictFiled){
        Map<String,Object> paramsMap = new HashMap<String,Object>(1);
        paramsMap.put("dictFiled",dictFiled);
        return session.selectOne("DictFiledMapper.getTotalCount",paramsMap);
    }

    /**
    * 更新
    *
    */
    public Boolean update(DictFiled dictFiled){
        return session.update("DictFiledMapper.update",dictFiled) >0;
    }

    public void setSession(SqlSession session) {
        this.session = session;
    }
}
