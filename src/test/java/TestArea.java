import com.hwp.config.SpringMybatis;
import com.hwp.entity.SysArea;
import com.hwp.mapper.SysAreaMapper;
import com.hwp.service.AreaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringMybatis.class)
public class TestArea {

    @Autowired
    SysAreaMapper sysAreaMapper;
    @Autowired
    AreaService areaService;

    @Autowired
    @Test
    public void TestArea() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("areaName","广州");
        List<SysArea> SysAreas = sysAreaMapper.selectPage(map);
        for (SysArea SysArea : SysAreas) {
            System.out.println(SysArea);
        }
    }
}


