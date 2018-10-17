import org.apache.commons.lang.StringUtils;

import java.util.Arrays;

/**
 * @DESCRIPTION 基础demo
 * @AUTHER administrator zhangna
 * @create 2018-05-23
 */
public class Deom {
    static public void main(String arg[]){
      /*  byte b = 127;
        System.out.println(b);
        Long ong = 9999999L;//Long类型
        System.out.println(ong);
        //以0开头的是八进制数
        int ocatlValue = 013;
        System.out.println("八进制"+ocatlValue);
//        以0x或0X开头的是16进制数
        int slValue = 0x1a;
        int slValue2= 0X1b;
        System.out.println("十六进制"+slValue);
        System.out.println("十六进制"+slValue2);
//        以0b开头的是二进制数
        int eValue = 0b11;
        System.out.println("二进制"+eValue);*/

/*        char aChar ='a';
        System.out.println(aChar);
        char enterChar='\u9999';
        System.out.println(enterChar);
        char cChar='哈';
        System.out.println(cChar);
        int value = cChar;
        System.out.println(value);*/
     /*   System.out.println(12/0.0);
        System.out.println(3+4+"hello");
        System.out.println("hello"+3+4);
        System.out.println(5.2 % 3.1);*/

    /* int []ttype = new int[8];
     for(int i=0 ,len = ttype.length;i<len;i++) {
         ttype[i] = 10-i;
     }

    int [] type1 = new int[]{12,4,5,6};
        Arrays.sort(type1);
        int i = Arrays.binarySearch(type1, 12);
        System.out.println(i);
        for (int t:type1
                ) {
            System.out.println(t);
        }
        Arrays.parallelSort(ttype);
        System.out.println(Arrays.toString(ttype));*/

//        System.out.println(fn(10));
      /*  Integer a= new Integer(127) ;
        Integer b= new Integer(127) ;*/
       /* Integer a= 127;
        Integer b= 127;
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
        System.out.println( a == b ? a:0);
        System.out.println(a+" "+b);*/

      /* for(int i = 1; i< 10;i++){
           for(int j = 1 ; j< 35 ;j++){
               for(int k= 1 ;k < 36 ;k++){
                   if(i*j*k==36 ){
                       if(i<=j && j<=k) {
                           System.out.println("i = " + i + "  j=" + j + "  k=" + k);
                       }
                   }
               }
           }
       }

        for(int i = 1; i< 10;i++){
            for(int j = 1 ; j< 35 ;j++){
                for(int k= 1 ;k < 36 ;k++){
                    if(i*j*k==36 ){
                        if(i<=j && j<=k) {
                            System.out.println(i+"+"+j+"+"+k+"="+(i+j+k));
                        }
                    }
                }
            }
        }
*/

      StringBuilder str = new StringBuilder("ABCDEFG");
      String str1 ="CD";
      String str2 = "A";
      String str3 = "B";
      int pos = str.indexOf(str1);
      int length = str1.length();
      str.delete(pos,pos+length);
        System.out.println(str);
        int i = str.indexOf(str2);
        str.deleteCharAt(i);
        i = str.indexOf(str3);
        str.deleteCharAt(i);
        System.out.println(str);

        
    }

    /**
     * 递归
     * f(0) = 1
     * f(1) = 4    f(n+2) = 2*f(n+1)+f(n)
     * 所以
     * f(n) = 2*f(n-1)+f(n-2)
     */
    private static int fn(int param){
        int result = 0;
        if(param == 0){
            result = 1;
        }else if(param == 1){
            result = 4;
        }else{
            result = 2*fn(param-1)+fn(param-2);
        }

        return result;
    }
}
