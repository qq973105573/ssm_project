
import com.alibaba.druid.pool.DruidDataSource;
import com.hwp.config.SpringMybatis;
import com.hwp.entity.AppVersion;
import com.hwp.entity.Qualification;
import com.hwp.entity.QualificationCondition;
import com.hwp.mapper.AppVersionMapper;
import com.hwp.mapper.QualificationMapper;
import com.hwp.service.AppVersionService;
import com.hwp.service.QualificationService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringMybatis.class)
public class TestQualification {

    @Autowired
    QualificationMapper qualificationMapper;

    @Autowired
    QualificationService qualificationService;


    @Test
    public void testMapper(){
        Qualification qualification = qualificationMapper.selectByPrimaryKey(1L);
        System.out.println(qualification);
    }


    @Test
    public void testServiceFindAll(){
        Qualification qualification = qualificationService.selectByPrimaryKey(1L);
        System.out.println(qualification);
    }

    @Test
    public void testSelectByApp(){
        //查询所有del_flag为0的数据
        Qualification qualification = new Qualification();
        qualification.setDelFlag("0");
        List<Qualification> qualifications = qualificationMapper.select(qualification);
        for (Qualification version : qualifications) {
            System.out.println(version);
        }
    }
    @Test
    public void testSelectPage(){
        QualificationCondition condition = new QualificationCondition();
        condition.setStartDate("2019-03-01");
        condition.setEndDate("2019-09-30");
        condition.setType(2);
        condition.setCheck(0);
        List<Qualification> qualifications = qualificationMapper.selectPage(condition);
        for (Qualification qualification : qualifications) {
            System.out.println(qualification);
        }
    }

//
//
//    @Test
//    public void testServiceInsertBatch(){
//        List<User> users = userService.findAll();
//        int i = userService.insertBatch(users);
//    }
}
