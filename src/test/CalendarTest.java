import org.jfree.data.time.Day;
import sun.util.calendar.LocalGregorianCalendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @DESCRIPTION 日历类例子
 * @AUTHER administrator zhangna
 * @create 2018-06-01
 */
public class CalendarTest {
    public static void main(String [] arg){
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar);
        //取出年
        System.out.println(calendar.get(Calendar.YEAR));
        //取出月
        System.out.println(calendar.get(Calendar.MONTH));
        //取出日
        System.out.println(calendar.get(Calendar.DATE));

        //设置年月日，时分秒 2018-6-1 5:20:20
        calendar.set(2018,5,1,5,20,20);

       //将calendar推迟1年
        calendar.add(Calendar.YEAR,-1);//2017-6-1
        System.out.println(calendar.get(Calendar.YEAR));

        //将calendar提前8个月
        calendar.roll(Calendar.MONTH,-8);//2016-11-1
        System.out.println(calendar.get(Calendar.MONTH));

        System.out.println(calendar.getTime());
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format1 = sd.format(calendar.getTime());
        System.out.println(format1);
        Date dt = new Date();
        String format = sd.format(dt);
        System.out.println(format);

    }
}
