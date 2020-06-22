import com.hwp.config.SpringMybatis;
import com.hwp.entity.Statute;
import com.hwp.service.StatuteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringMybatis.class)
public class TestStatute {
    @Autowired
    StatuteService statuteService;
    @Test
    public void TestStatute(){
        Statute statute = new Statute();
        List<Statute> list = statuteService.select(statute);
        System.out.println(list);
    }
}
