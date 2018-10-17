import java.math.BigDecimal;

/**
 * @DESCRIPTION bigDouble类的练习
 * @AUTHER administrator zhangna
 * @create 2018-05-30
 */
public class DoubleTest {
    public static void main(String [] arg){
        System.out.println("0.01+0.05  ="+(0.05+0.01));
        System.out.println("1 - 0.42 = "+(1 - 0.42));
        BigDecimal f1 = new BigDecimal("0.01");
        BigDecimal f2 = new BigDecimal("0.05");
        System.out.println("0.01 + 0.05 ="+ f1.add(f2));
    }
}
