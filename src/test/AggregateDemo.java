import com.report.PrintWay;

import java.util.*;

/**
 * @DESCRIPTION 集合练习
 * @AUTHER administrator zhangna
 * @create 2018-06-05
 */
public class AggregateDemo {
    /**
     * collection练习
     */
    public static void collectionTest(){
        Collection c = new ArrayList();
        c.add("孙悟空");
        c.add(6);
        System.out.println("元素个数为："+c.size());
        c.remove(6);
        System.out.println("删除之后元素个数"+c.size());
        System.out.println("元素中是否包含孙悟空："+c.contains("孙悟空"));
        c.add("轻量级javaEE");
        Collection books = new HashSet();
        books.add("轻量级javaEE");
        books.add("疯狂java讲义");
        c.retainAll(books);
        System.out.println("c和book的交集"+c);
        //Lambda表达式遍历集合
        books.forEach(obj -> System.out.println("迭代集合"+obj));

        //iterator遍历
        System.out.println("----------------------------");
        Iterator iterator = books.iterator();
        while(iterator.hasNext()){
            String str =(String) iterator.next();
            System.out.println(str);
        }

    }

    public static void treeSetDemo(){
        TreeSet set  = new TreeSet();
        set.add(2);
        set.add(-8);
        set.add(1);
        set.add(3);
        System.out.println(set);
        //集合里的第一个元素
        System.out.println(set.first());
        //集合里的最后一个元素
        System.out.println(set.last());
        //集合里小于2的子集，不包含2
        System.out.println(set.headSet(2));
        //集合里大于2的子集，包含2
        System.out.println(set.tailSet(2));
        //集合里大于等于1小于3的子集
        System.out.println(set.subSet(1,3));
    }
    public static void treeSetErr(){
        TreeSet set = new TreeSet();
        set.add(new String("疯狂java讲义"));
        set.add(new Date());
    }
    public static void treeSetTest3(){
        TreeSet set = new TreeSet();
        set.add(new R(5));
        set.add(new R(-3));
        set.add(new R(9));
        set.add(new R(-2));
        //打印集合
        System.out.println(set);
        //取出第一个元素
        R first = (R) set.first();
        first.count = 20;
        //对最后一个元素赋值，值与第二个元素相同-2
        R last = (R) set.last();
//        last.count = -2 ;
        //再次查看有重复数据
        System.out.println(set);
        //删除改变了的元素
        System.out.println(set.remove(-2));
        System.out.println(set);
        //删除未改变的元素
        System.out.println(set.remove(5));
        System.out.println(set);
    }

    public static void enumSetdemo(){
        EnumSet enumSet = EnumSet.allOf(PrintWay.class);//将PrintWay枚举的数据转为集合
        System.out.println(enumSet);
        EnumSet enumSet1 = EnumSet.noneOf(PrintWay.class);//创建类型为PrintWay一个空的enumSet
        System.out.println(enumSet1);
        enumSet1.add(PrintWay.DOC);//手动添加DOC的值
        enumSet1.add(PrintWay.HTML);
        System.out.println(enumSet1);
        EnumSet enumSet2 = EnumSet.of(PrintWay.HTML,PrintWay.PDF);//指定枚举类中的哪些数据
        System.out.println(enumSet2);
        EnumSet enumSet3 = EnumSet.range(PrintWay.HTML,PrintWay.PDF);//创建了一个从PrintWay.HTML到PrintWay.PDF之间的数据为EnumSe集合
        System.out.println(enumSet3);
        EnumSet enumSet4 = EnumSet.complementOf(enumSet3);//创建一个enumSet3类型相同，值为该枚举的剩下的值
        System.out.println(enumSet4);

    }

    public static void listDemo(){
        String [] strs = {"轻量级编程","疯狂java","jquery"};
        List list = new ArrayList();
        for (int i=0,len = strs.length;i<len;i++){
            list.add(strs[i]);
        }
        ListIterator listIterator = list.listIterator();
        while(listIterator.hasNext()){
            System.out.println(listIterator.next());
        }
        System.out.println("======反向输出======");
        while (listIterator.hasPrevious()){
            System.out.println(listIterator.previous());
        }
        String [] strs1 = {"轻量级编程","疯狂java","jquery"};
        List<String> strings = Arrays.asList(strs1);
        List co =  new ArrayList();
        co.addAll(strings);
        System.out.println(strings);
        System.out.println(co);
        co.add("hello");
        System.out.println(co);
    }
    public static void main(String []arg0){
//        collectionTest();
//        treeSetDemo();
//        treeSetErr();
//        treeSetTest3();
//        enumSetdemo();
        listDemo();
    }
}
