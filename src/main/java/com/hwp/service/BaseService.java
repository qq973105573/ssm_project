package com.hwp.service;


import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * 服务层通用接口，提供服务层接口的基础CRUD方法
 */
public interface BaseService<T> {

    int deleteByPrimaryKey(Object o);

    int delete(T t);

    int insert(T t);

    int insertSelective(T t);

    boolean existsWithPrimaryKey(Object o);

    List<T> selectAll();

    T selectByPrimaryKey(Object o);

    int selectCount(T t);

    List<T> select(T t);

    T selectOne(T t);

    int updateByPrimaryKey(T t);

    int updateByPrimaryKeySelective(T t);

    int deleteByExample(Object o);

    List<T> selectByExample(Object o);

    int selectCountByExample(Object o);

    T selectOneByExample(Object o);

    int updateByExample(T t, Object o);

    int updateByExampleSelective(T t, Object o);

    List<T> selectByExampleAndRowBounds(Object o, RowBounds rowBounds);

    List<T> selectByRowBounds(T t, RowBounds rowBounds);
}
