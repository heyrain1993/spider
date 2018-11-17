package com.heyu.spider.page.jdSort.dao;

import com.heyu.spider.page.jdSort.entity.DictFiled;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DictFiledDao{

    /**
     *  ����id����
     */
    public DictFiled findById(Integer id);

    /**
     *  �����¼
     */
    public Boolean insert(DictFiled dictFiled);

    /**
     *  ����idɾ��
     */
    public Boolean deleteById(Integer id);
    /**
     *  ��������
     */
    public List<DictFiled> findAllList();
    /**
    * ��ҳ��ѯ�б�
    */
    public List<DictFiled> findByCondition(Integer pageNumber,Integer pageSize,DictFiled dictFiled);

    /**
    * ��ȡ��ҳ������
    */
    public Long getTotalCount(DictFiled dictFiled);

    /**
    * ����
    *
    */
    public Boolean update(DictFiled dictFiled);

    void insertBatch(List<DictFiled> dictFileds);

    List<DictFiled> findByDictId(@Param("dictId") Integer id);

    List<String> findFiled(@Param("dictId")Integer dictId);

    List<String> findFiledValue(@Param("dictId")Integer dictId, @Param("filed")String filed);
}
