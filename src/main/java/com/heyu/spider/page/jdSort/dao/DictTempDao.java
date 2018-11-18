package com.heyu.spider.page.jdSort.dao;

import com.heyu.spider.page.jdSort.entity.Dict;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DictTempDao {

    /**
     *  ����id����
     */
    public Dict findById(Integer id);

    /**
     *  �����¼
     */
    public Boolean insert(Dict dict);

    /**
     *  ����idɾ��
     */
    public Boolean deleteById(Integer id);
    /**
     *  ��������
     */
    public List<Dict> findAllList();

    /**
    * ��ҳ��ѯ�б�
    */
    public List<Dict> findByCondition(Integer pageNumber, Integer pageSize, Dict dict);

    /**
    * ��ȡ��ҳ������
    */
    public Long getTotalCount(Dict dict);

    /**
    * ����
    *
    */
    public Boolean update(Dict dict);

    List<String> findSecond(@Param("firstDict") String firstDict);

    List<String> findFirst();

    List<Dict> findThird(@Param("firstDict") String firstDict, @Param("secondDict") String secondDict);

    void insertBatch(List<Dict> dicts);
}
