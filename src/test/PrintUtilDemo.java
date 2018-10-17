import com.report.PrintColumn;
import com.report.PrintUtil;
import com.report.PrintWay;
import net.sf.dynamicreports.report.exception.DRException;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @DESCRIPTION 测试PrintUtil工具类
 * @AUTHER administrator zhangna
 * @create 2018-05-17
 */
public class PrintUtilDemo {
    public static void  main(String arg[]) throws FileNotFoundException, DRException {
        //数据源list
        List<Map<String,?>> list = new ArrayList<>();
        for(int j=0;j<1000;j++) {
            Map<String, Object> map = new HashMap<String, Object>();
            for (int i = 0; i < 5; i++) {
                map.put("字段" + i, "值"+i+j+1);
            }
            list.add(map);
        }
        //显示字段，数据库对应字段
        String [] columnTitle ={"字段0","字段1","字段2","字段3","字段4"};
        PrintUtil pu = new PrintUtil();
        pu.setTitle("标题");
        pu.setDatasource(list);//数据源
        pu.setIndex(columnTitle,columnTitle);//显示字段，数据库对应字段
//        pu.setPrintWay(PrintWay.HTML);
        //不赋值给printWay时为显示报表
        pu.print(new FileOutputStream("f:/测试.html"));
        //测试中发现导出PDF是中午无法显示
    }
}
