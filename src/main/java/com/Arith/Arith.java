package com.Arith;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @DESCRIPTION BigDouble工具类
 * 因为BigDouble构造构造器需要传入String，不然还是可能造成精度丢失
 * @AUTHER administrator zhangna
 * @create 2018-05-30
 */
public class Arith {
    //默认除法运算精度
    private static final int DEF_DIV_SCALE = 10;
    //构造器私有，这个类不能实例化
    private Arith(){}

    //提供精确的加法运算
    public static double add(double v1,double v2){
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.add(b2).doubleValue();
    }

    //提供精确减法运算
    public static double sub(double v1,double v2){
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.subtract(b2).doubleValue();
    }

    //提供精确乘法运算
    public static  double mul(double v1,double v2){
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.multiply(b2).doubleValue();
    }

    //提供精确到小数点后十位四舍五入，DEF_DIB_SCALE
    public static double div(double v1,double v2){
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.divide(b2,DEF_DIV_SCALE,BigDecimal.ROUND_UP).doubleValue();
    }

    //测试
    public static void main(String [] arg0){
        DecimalFormat df = new DecimalFormat("#0.00");
        String format = df.format(4.015*100);//保留两位小数
        System.out.println(format);
        System.out.printf("0.01+0.05=%.2f\n",0.01+0.05);//保留两位小数
        System.out.println("--------------华丽丽的分割线------------------");
        System.out.println(0.01+0.05);
        System.out.println(1.0-0.42);
        System.out.println(4.015*100);
        System.out.println(12.3/100);
        System.out.println("--------------华丽丽的分割线------------------");
        System.out.println(Arith.add(0.01,0.05));
        System.out.println(Arith.sub(1.0,0.42));
        System.out.println(Arith.mul(4.015,100));
        System.out.println(Arith.div(12.3,100));
    }
}
