
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.jasper.constant.ImageType;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.chart.Bar3DChartBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.style.ReportStyleBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.definition.style.DRIReportStyle;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;

import java.awt.*;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

/**
 * @DESCRIPTION DynamicReports报表、打印、导出练习
 * @AUTHER administrator zhangna
 * @create 2018-05-16
 */
public class DynamicReportsDemo {
    public DynamicReportsDemo(){
        bulid();
    }
    public void bulid(){
        String titleName = "测试导出报表";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            StyleBuilder boldstyle = DynamicReports.stl.style().bold();//加粗
            StyleBuilder boldCenertStyle = DynamicReports.stl.style(boldstyle).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER);//加粗并居中
            StyleBuilder styleBuilder = DynamicReports.stl.style(boldCenertStyle).setBorder(stl.pen1Point()).setBackgroundColor(Color.pink);//设置背景颜色
            TextColumnBuilder<Integer> rowNumberColumn = DynamicReports.col.columnRowNumberColumn("NO")//新增列NO
                    .setFixedColumns(4)//设置行宽
                    .setHorizontalTextAlignment(HorizontalTextAlignment.CENTER);//设置对齐方式


            //设置斜体
            StyleBuilder italicstyle = DynamicReports.stl.style().italic();//斜体
            TextColumnBuilder<String> itemcolumn = DynamicReports.col.column("Item", "item", type.stringType());//为了分组将列单独写出来
            TextColumnBuilder<BigDecimal> unitPricepriceColumn = col.column("unit price", "unitPrice", type.bigDecimalType());
            TextColumnBuilder<Integer> quantityColumn = col.column("Quantity", "quantity", type.integerType());
            //price = quantity*unitprice
            TextColumnBuilder<BigDecimal> priceColumn = unitPricepriceColumn.multiply(quantityColumn).setTitle("price");



            Bar3DChartBuilder bar3DChartBuilder = cht.bar3DChart();
            bar3DChartBuilder.setTitle("sales by item")
                    .setCategory(itemcolumn)
                    .setShowLabels(true)
                    .setShowValues(true)//显示值
                    .setValuePattern("￥")//值前缀
                    .setBookmarkLevel(230)
                    .addSerie(cht.serie(unitPricepriceColumn), cht.serie(priceColumn));//图表

            Bar3DChartBuilder bar3DChartBuilder1 = cht.bar3DChart();
            bar3DChartBuilder1.setTitle("哈哈").setCategory(itemcolumn).addSerie(
                    cht.serie(unitPricepriceColumn), cht.serie(priceColumn)
            ).setUseSeriesAsCategory(true);


            JasperReportBuilder builder = report();
            builder.columns(rowNumberColumn, itemcolumn, priceColumn);
            builder.columns(// add columns
//                    col.column("Item","item", type.stringType()),
//                    col.column("Quantity","quantity",type.integerType())
//                    col.column("unit price","unitPrice",type.bigDecimalType())
                    quantityColumn
            ).addTitle(cmp.text("标题").setStyle(boldCenertStyle))//设置标题
                    .setColumnTitleStyle(styleBuilder)//设置字段首行样式
                    .highlightDetailEvenRows()//隔行变色
                    .pageFooter(cmp.pageXofY().setStyle(boldCenertStyle))//设置页脚
                    .summary(bar3DChartBuilder, bar3DChartBuilder1)//图表
                    .setDataSource(createDataSource())//设置数据源
                    .groupBy(itemcolumn);//分组   目前根据名称
            //增加打印时间
            builder.addPageHeader(DynamicReports.cmp.text("打印时间:"+format.format(new Date())).setStyle(DynamicReports.stl.style().setHorizontalTextAlignment(HorizontalTextAlignment.LEFT)));

//            builder.toXls(new FileOutputStream("F:/test.xls"));//测试没有导出数据但生成xls文件
//            builder.toXlsx(new FileOutputStream("F:/text.xlsx"));//成功导出
//            builder.toPdf(new FileOutputStream("F:/test.pdf"));
            builder.show();
//            builder.print();
//            builder.toDocx(new FileOutputStream("F:/"+titleName+".docx"));
//            builder.toImage(new FileOutputStream("F:/test.jpg"), ImageType.JPG);//图片
//            builder.toHtml(new FileOutputStream("F:/text.html"));//导出为html
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JRDataSource createDataSource(){
        DRDataSource dataSource =  new DRDataSource("item","quantity","unitPrice");//假数据添加字段
        dataSource.add("DvD",1,new BigDecimal(50));
        dataSource.add("DvD",3,new BigDecimal(300));
        dataSource.add("book",5,new BigDecimal(200));
        dataSource.add("Life",2,new BigDecimal(100));
        dataSource.add("NotBook",2,new BigDecimal(300));
       /* dataSource.add("10.138.69.123",2,new BigDecimal(100));
        dataSource.add("192.168.9.126",2,new BigDecimal(300));
        dataSource.add("192.168.9.125",2,new BigDecimal(300));
        dataSource.add("192.168.9.124",2,new BigDecimal(300));
        dataSource.add("192.168.9.123",2,new BigDecimal(300));
        dataSource.add("192.168.9.122",2,new BigDecimal(300));*/
        return dataSource;
    }

    public  static void main(String []arg0 ){
       new DynamicReportsDemo();
    }
}
