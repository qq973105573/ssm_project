import com.alibaba.excel.EasyExcel;
import com.hwp.config.SpringMybatis;
import com.hwp.entity.SysArea;
import com.hwp.listener.SysAreaListener;
import com.hwp.mapper.SysAreaMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringMybatis.class)
public class TestExcel {
    @Autowired
    SysAreaMapper sysAreaMapper;

    private List<SysArea> createdModelList(){
        ArrayList<SysArea> sysAreas = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            SysArea sysArea = new SysArea();
            sysArea.setId(1L+i);
            sysArea.setUpdateDate(new Date());
            sysAreas.add(sysArea);
        }
        return sysAreas;
    }
    @Test
    public void write(){
        EasyExcel.write("D:\\sysArea.xlsx",SysArea.class).sheet().doWrite(createdModelList());
    }
    @Test
    public void read(){
        EasyExcel.read("D:\\sysArea.xlsx",SysArea.class,new SysAreaListener(sysAreaMapper)).sheet().doRead();
        System.out.println("读数据");
    }
}
