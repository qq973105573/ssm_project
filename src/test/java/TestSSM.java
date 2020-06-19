
import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hwp.config.SpringMybatis;
import com.hwp.entity.AppVersion;
import com.hwp.mapper.AppVersionMapper;
import com.hwp.service.AppVersionService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringMybatis.class)
public class TestSSM {

    @Autowired
    DruidDataSource dataSource;

    @Autowired
    SqlSessionFactory sessionFactory;
//
    @Autowired
    AppVersionMapper appVersionMapper;

    @Autowired
    DataSourceTransactionManager tx;
//
    @Autowired
    AppVersionService appVersionService;

    @Test
    public void TestConn() throws SQLException {
//        System.out.println(dataSource.getConnection());
        System.out.println(sessionFactory.openSession().getConnection());
    }

    @Test
    public void testMapper(){
        AppVersion appVersion = appVersionMapper.selectByPrimaryKey(1L);
        System.out.println(appVersion);
    }

    @Test
    public void testTx() throws SQLException {
        System.out.println(tx.getDataSource().getConnection());
    }
//
    @Test
    public void testServiceFindAll(){
        AppVersion appVersion = appVersionService.selectByPrimaryKey(1L);
        System.out.println(appVersion);
    }
    @Test
    public void testDelete(){
        int i = appVersionService.deleteByPrimaryKey(1);
        System.out.println(i);
    }

//
//
//    @Test
//    public void testServiceInsertBatch(){
//        List<User> users = userService.findAll();
//        int i = userService.insertBatch(users);
//    }
}
