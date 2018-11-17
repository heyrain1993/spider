package com.heyu.spider.page.dao.title;

import com.heyu.spider.page.entity.title.Title;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TitleDao{

    @Autowired
    private SqlSession session;

    /**
     *  根据id查找
     */
    public Title findById(Integer id){
        return session.selectOne("TitleMapper.selectById",id);
    }

    /**
     *  插入记录
     */
    public Boolean insert(Title title){
        return session.insert("TitleMapper.insert",title) > 0;
    }

    /**
     *  根据id删除
     */
    public Boolean deleteById(Integer id){
        return session.delete("TitleMapper.deleteById",id) > 0;
    }
    /**
     *  查找所有
     */
    public List<Title> findAllList(){
        return session.selectList("TitleMapper.findAllList");
    }

    /**
    * 分页查询列表
    */
    public List<Title> findByCondition(Integer pageNumber,Integer pageSize,Title title){
        Map<String ,Object> paramsMap = new HashMap<String,Object>(3);
        paramsMap.put("offset",(pageNumber-1)*pageSize);
        paramsMap.put("pageSize",pageSize);
        paramsMap.put("title",title);
        return session.selectList("TitleMapper.findByCondition",paramsMap);
    }

    /**
    * 获取分页的总数
    */
    public Long getTotalCount(Title title){
        Map<String,Object> paramsMap = new HashMap<String,Object>(1);
        paramsMap.put("title",title);
        return session.selectOne("TitleMapper.getTotalCount",paramsMap);
    }

    /**
    * 更新
    *
    */
    public Boolean update(Title title){
        return session.update("TitleMapper.update",title) >0;
    }

    public void setSession(SqlSession session) {
        this.session = session;
    }
}
