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
     *  根据id查找
     */
    public Dict findById(Integer id){
        return session.selectOne("DictMapper.selectById",id);
    }

    /**
     *  插入记录
     */
    public Boolean insert(Dict dict){
        return session.insert("DictMapper.insert",dict) > 0;
    }

    /**
     *  根据id删除
     */
    public Boolean deleteById(Integer id){
        return session.delete("DictMapper.deleteById",id) > 0;
    }
    /**
     *  查找所有
     */
    public List<Dict> findAllList(){
        return session.selectList("DictMapper.findAllList");
    }

    /**
    * 分页查询列表
    */
    public List<Dict> findByCondition(Integer pageNumber,Integer pageSize,Dict dict){
        Map<String ,Object> paramsMap = new HashMap<String,Object>(3);
        paramsMap.put("offset",(pageNumber-1)*pageSize);
        paramsMap.put("pageSize",pageSize);
        paramsMap.put("dict",dict);
        return session.selectList("DictMapper.findByCondition",paramsMap);
    }

    /**
    * 获取分页的总数
    */
    public Long getTotalCount(Dict dict){
        Map<String,Object> paramsMap = new HashMap<String,Object>(1);
        paramsMap.put("dict",dict);
        return session.selectOne("DictMapper.getTotalCount",paramsMap);
    }

    /**
    * 更新
    *
    */
    public Boolean update(Dict dict){
        return session.update("DictMapper.update",dict) >0;
    }

    public void setSession(SqlSession session) {
        this.session = session;
    }
}
