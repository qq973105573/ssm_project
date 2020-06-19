import com.hwp.config.SpringMybatis;
import com.hwp.entity.Examine;
import com.hwp.mapper.ExamineMapper;
import com.hwp.service.ExamineService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringMybatis.class)
public class TestExamine {

    @Autowired
    ExamineMapper examineMapper;
    @Autowired
    ExamineService examineService;

    @Test
    public void testMapper(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("type",1);
        map.put("name","人员");
        map.put("officeId",56);
        List<Examine> examines = examineMapper.selectPage(map);
        for (Examine examine : examines) {
            System.out.println(examine);
        }
    }
}
