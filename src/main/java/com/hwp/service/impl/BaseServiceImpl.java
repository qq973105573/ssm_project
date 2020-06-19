package com.hwp.service.impl;

import com.hwp.service.BaseService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 通用服务层实现类  提供通用的实现方法
 */
public class BaseServiceImpl<T> implements BaseService<T> {

    /*
    * 泛型注入  spring容器会根据当前的泛型来查找对应的实例对象，进行注入
    * 以AppVersionServiceImpl 继承BaserviceImpl<AppVersion>为例:
    * spring容器会查找注入Mapper<Appversion>  的类型
    * 根据容器中只有一个AppVersionMapper继承了Mapper<Appversion>，可以找到该接口的代理子类
    *
    * */
    @Autowired
    Mapper<T> mapper;

    public int deleteByPrimaryKey(Object o) {
        int result = 0;
        result = mapper.deleteByPrimaryKey(o);
        if(result>0){
            return result;
        }else {
            throw new RuntimeException("删除失败！请重试");
        }
    }

    public int delete(T t) {
        return mapper.delete(t);
    }

    public int insert(T t) {
        return mapper.insert(t);
    }

    public int insertSelective(T t) {
        int result = 0;
        result=mapper.insertSelective(t);
        if(result>0){
            return result;
        }else {
            throw new RuntimeException("添加失败!请重试");
        }
    }

    public boolean existsWithPrimaryKey(Object o) {
        return mapper.existsWithPrimaryKey(o);
    }

    public List<T> selectAll() {
        return mapper.selectAll();
    }

    public T selectByPrimaryKey(Object o) {
        return mapper.selectByPrimaryKey(o);
    }

    public int selectCount(T t) {
        return mapper.selectCount(t);
    }

    public List<T> select(T t) {
        return mapper.select(t);
    }

    public T selectOne(T t) {
        return mapper.selectOne(t);
    }

    public int updateByPrimaryKey(T t) {
        return mapper.updateByPrimaryKey(t);
    }

    public int updateByPrimaryKeySelective(T t) {
        int result = 0;
        result=mapper.updateByPrimaryKeySelective(t);
        if(result>0){
            return result;
        }else {
            throw new RuntimeException("更新失败!请重试");
        }
    }

    public int deleteByExample(Object o) {
        return mapper.deleteByExample(o);
    }

    public List<T> selectByExample(Object o) {
        return mapper.selectByExample(o);
    }

    public int selectCountByExample(Object o) {
        return mapper.selectCountByExample(o);
    }

    public T selectOneByExample(Object o) {
        return mapper.selectOneByExample(o);
    }

    public int updateByExample(T t, Object o) {
        return mapper.updateByExample(t,o);
    }

    public int updateByExampleSelective(T t, Object o) {
        return mapper.updateByExampleSelective(t,o);
    }

    public List<T> selectByExampleAndRowBounds(Object o, RowBounds rowBounds) {
        return mapper.selectByExampleAndRowBounds(o,rowBounds);
    }

    public List<T> selectByRowBounds(T t, RowBounds rowBounds) {
        return mapper.selectByRowBounds(t,rowBounds);
    }
}
