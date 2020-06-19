package com.hwp.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import tk.mybatis.spring.annotation.MapperScan;

import java.io.IOException;
import java.util.Properties;

@Configuration
/**
 * 1.spring管理数据源
 * 2.spring管理SqlSessionFactoryBean
 * 3.扫描Mapper接口
 */
@MapperScan(basePackages = "com.hwp.mapper")//扫描数据访问接口，创建代理子类 //替换为tkmapper的注解
@Import(SpringService.class)
public class SpringMybatis {
    @Bean
    public DruidDataSource getDruidDataSource(){
        DruidDataSource dataSource = null;
        Properties properties = new Properties();
        try {
            properties.load(SpringMybatis.class.getClassLoader().getResourceAsStream("db.properties"));
            dataSource = new DruidDataSource();
            dataSource.configFromPropety(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    /*创建sqlSessionFactoryBean  替代  SQL Session Factory 用于设置mybatis的全局配置 和创建sqlSession*/
    @Bean
    public SqlSessionFactoryBean getSqlSessionFactoryBean(DruidDataSource dataSource){
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);//设置数据源
        //设置mybatis原主配置文件信息
        //org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        //替换为tkmapper提供的配置类
        tk.mybatis.mapper.session.Configuration configuration = new tk.mybatis.mapper.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);//开启转驼峰
        factoryBean.setConfiguration(configuration);
        //引入分页插件
        PageInterceptor interceptor = new PageInterceptor();//分页拦截对象
        //设置方言属性
        interceptor.setProperties(new Properties());
        factoryBean.setPlugins(interceptor);
        return factoryBean;
    }

}
