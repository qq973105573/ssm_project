package com.hwp.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.activation.DataSource;

/**
 *
 整合事务控制
 a.开启扫描服务层
 b.声明事务管理器
 c.开启事务注解支持
 d.在需要事务控制的服务层的类上添加事务注解
 f.引入配置文件
 */
@Configuration
@ComponentScan(basePackages = "com.hwp.service")
@PropertySource(value = "classpath:sys.properties",encoding = "utf-8")
@EnableTransactionManagement
public class SpringService {

    @Bean
    public DataSourceTransactionManager getDataSourceTransactionManager(DruidDataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
}
