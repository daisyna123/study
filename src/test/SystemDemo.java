import com.report.PrintColumn;

import java.io.FileOutputStream;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

/**
 * @DESCRIPTION System来获取平台的系统属性和环境变量
 * @AUTHER administrator zhangna
 * @create 2018-05-29
 */
public class SystemDemo {
    static PrintColumn obj ;
    public static void main(String []arg) throws  Exception{
        //获取系统所有变量
 /*       Map<String, String> getenv = System.getenv();
        for(String name:getenv.keySet()){
            System.out.println(name+"------>"+getenv.get(name));
        }
        //获取指定环境变量的值
        System.out.println("环境变量"+System.getenv("JAVA_HOME"));

        //获取所有系统属性
        Properties pr = System.getProperties();
//        pr.store(new FileOutputStream("e:/props.txt"),"System Properties");
        System.out.println(System.getProperty("os.name"));
*/




 /*
        Integer a= new Integer(127) ;
        Integer b= new Integer(127) ;
        System.out.println(a==b);
        System.out.println(System.identityHashCode(a));
        System.out.println(System.identityHashCode(b));//可以比较是否是同一个对象
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
        Integer c = 127;
        Integer d = 127;
        System.out.println(c==d);
        System.out.println(System.identityHashCode(c));
        System.out.println(System.identityHashCode(d));
        System.out.println(c.hashCode()+"     "+d.hashCode());*/

        //String方法

       /* System.out.println("toStirng: "+Objects.toString(obj));
        System.out.println("hashCode: "+Objects.hashCode(obj));
        System.out.println(Objects.requireNonNull(obj));*/


    }
}
