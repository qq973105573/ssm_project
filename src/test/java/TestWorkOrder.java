import com.github.pagehelper.PageInfo;
import com.hwp.config.SpringMybatis;
import com.hwp.entity.Statute;
import com.hwp.entity.WorkOrder;
import com.hwp.mapper.StatuteMapper;
import com.hwp.mapper.WorkOrderMapper;
import com.hwp.service.WorkOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringMybatis.class)
public class TestWorkOrder {

    @Autowired
    WorkOrderMapper workOrderMapper;

    @Autowired
    WorkOrderService workOrderService;

    @Test
    public void TestWorkOrder(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("status",1);
        map.put("startDate","2016-09-01");
        map.put("officeId",56);
        List<WorkOrder> workOrders = workOrderMapper.selectPage(map);
        for (WorkOrder workOrder : workOrders) {
            System.out.println(workOrder);
        }
    }
    @Test
    public void TestService(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("status",1);
        map.put("startDate","2016-09-01");
        map.put("officeId",56);
        PageInfo pageInfo = workOrderService.selectPage(1, 2, map);
        System.out.println(pageInfo);
    }

    @Test
    public void testSelectDetail(){
        Map<String, Object> map = workOrderService.selectDetail(4);
        Set<Map.Entry<String, Object>> entries = map.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("----------------------------");
        }
    }
}
