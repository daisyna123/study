package com.report;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import org.apache.commons.lang.StringUtils;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

/**
 * @DESCRIPTION 打印工具类
 * @AUTHER administrator zhangna
 * @create 2018-05-16
 */
public class PrintUtil {
    /**加载数据源*/
    private List<Map<String,?>> datasource;

    /**列对象*/
    private List<PrintColumn> columns;

    /**打印方式*/
    private PrintWay printWay;

    /**是否加序号字段*/
    private boolean hasSerialNumber = true;

    private final String DEFAULT_SERIAL_NUMBER_STRING = "序号";

    private String selfSerialNumber;

    /**报表名称*/
    private String title;

    /**报表打印布局 纵向 横向*/
    private String printLayout;

    private HashMap<String,String> headContent;//暂时未用到，设置标题下面需要加的内容

    /***
     * 打印主方法
     */
    public void print(FileOutputStream  outputStream) throws DRException {
        JasperReportBuilder builder = report();
        //设置格式
        /**
         * 标题居中、加粗
         * 增加打印时间
         * 隔行变色
         *字段行：背景颜色、居中、加粗、加边框
         * 可选是否加序号列hasSerialNumber为true时，加序号列
         */
        StyleBuilder bold = DynamicReports.stl.style().bold();//加粗
        StyleBuilder styleBuilder = DynamicReports.stl.style(bold).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER).setFontSize(16);//加粗居中
        StyleBuilder headStyle = DynamicReports.stl.style(styleBuilder).setBorder(stl.pen1Point()).setBackgroundColor(Color.LIGHT_GRAY)
                .setForegroundColor(Color.white).setTopPadding(5);//字段行

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(hasSerialNumber){//是否加序号列
            if(StringUtils.isBlank(selfSerialNumber)){
                selfSerialNumber = DEFAULT_SERIAL_NUMBER_STRING;
            }
            TextColumnBuilder<Integer> rowNumberColumn = DynamicReports.col.columnRowNumberColumn(selfSerialNumber)
                    .setFixedColumns(6).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER);
            builder.columns(rowNumberColumn);
        }
        builder.setColumnTitleStyle(headStyle);
        //添加标题
        if(StringUtils.isBlank(this.title)){
            this.title = "";
        }
        builder.addTitle(cmp.text(this.title).setStyle(styleBuilder));
        //添加打印时间
        builder.addPageHeader(cmp.text("打印时间："+format.format(new Date())).setStyle(
                DynamicReports.stl.style().setHorizontalTextAlignment(HorizontalTextAlignment.LEFT)
        ));
//        builder.setColumnStyle(stl.style().setBorder(stl.penThin()));//单元格边框

        JasperDesign jd = new JasperDesign();
        jd.setName("页码");
        //纵向
//		if(this.printLayout!=null&&!this.printLayout.equals("")){
//			jd.setPageWidth(595);
//			jd.setPageHeight(842);
//			jd.setColumnWidth(555);
//		}else{//横向
        jd.setPageWidth(1000);
        jd.setPageHeight(555);
        jd.setColumnWidth(1000);
        jd.setTopMargin(0);

        builder.setTemplateDesign(jd);
        //添加列
        addColumns(builder);
        //添加数据源
        addDataSource(builder);
        //隔行变色，页脚
        builder.highlightDetailEvenRows()//隔行变色
                .pageFooter(cmp.pageXofY().setStyle(styleBuilder));//设置页脚
        //打印
        toPrint(builder,outputStream);

    }

    /**
     * 添加列
     * @param builder
     * @return
     */
    public JasperReportBuilder addColumns(JasperReportBuilder builder){
        List<PrintColumn> list = null;
        if(datasource != null && datasource.size() > 0){
            list = new ArrayList<PrintColumn>();
            Map<String, ?> firstRawLine = datasource.get(0);
            if(getColumns() != null){//columns为列对象
                for (int i=0;i<getColumns().size();i++){
                    PrintColumn column = getColumns().get(i);
                    Object value = firstRawLine.get(column.getColumnHeader());//拿到数据源列名

                    //修正数据类型
                    if (value instanceof Date){
                        column.setClazz(Date.class);
                    }else if (value instanceof BigDecimal){
                        column.setClazz(BigDecimal.class);
                    }else if (value instanceof Integer){
                        column.setClazz(Integer.class);
                    }else if (value instanceof String){
                        column.setClazz(String.class);
                    }
                    list.add(column);
                }
            }
        }

        if(list != null){
            for (PrintColumn colm:list
                 ) {
                TextColumnBuilder<?> columnBuilder = DynamicReports.col.column(colm.getHeader(), colm.getColumnHeader(), colm.getClazz());
                columnBuilder.setTitleStretchWithOverflow(Boolean.TRUE);
                columnBuilder.setStretchWithOverflow(Boolean.TRUE);
                columnBuilder.setHorizontalTextAlignment(HorizontalTextAlignment.RIGHT);
                builder.columns(columnBuilder);
            }
        }
        return builder;
    }

    /**
     * 以什么方式输出，printWay.DOC导出为world
     *              PrintWay.HTML导出为HTML
     *              PrintWay.XLSX导出为excel
     *              PrintWay.PRINTER 打印
     *              PrintWay.PDF导出为pdf
     * @param builder
     * @param out
     * @return
     */
    public JasperReportBuilder toPrint(JasperReportBuilder builder,OutputStream out){
        try {
            if(printWay == PrintWay.DOC){
                    builder.toDocx(out);
            }else if(printWay == PrintWay.HTML){
                builder.toHtml(out);
            }else if(printWay == PrintWay.XLSX){
                builder.toXlsx(out);
            }else if(printWay == PrintWay.PRINTER){
                builder.print();
            }else if (printWay == PrintWay.PDF){
                builder.toPdf(out);
            }else{
                builder.show();
            }

        } catch (DRException e) {
            e.printStackTrace();
        }
        return builder;
    }

    /**
     * 添加需要打印的数据源
     * @param builder
     * @return
     */
    protected JasperReportBuilder addDataSource(JasperReportBuilder builder){
        JRDataSource ds = null;
        if (this.datasource != null && this.datasource.size() == 0){
            ds = new JREmptyDataSource();
        }else{
            ds = new JRMapCollectionDataSource(this.datasource);
        }
        builder.setDataSource(ds);
        return builder;
    }
    /***
     * 设置索引名和表头名称
     * @param index
     * @param head
     */
    public void setIndex(String [] index,String head[] ){
        List<PrintColumn> list  = null;
        if(index != null && head !=null){
            if(index.length == head.length){
                list = new ArrayList<PrintColumn>();
                for(int i=0 ;i<index.length ;i++){
                    PrintColumn column = new PrintColumn();
                    column.setHeader(head[i]);//表格列名
                    column.setColumnHeader(index[i]);//数据源列名
                    column.setClazz(String.class);//打印类型
                    list.add(column);
                }
                columns = list;//columns列对象
            }
        }
    }

    public List<Map<String, ?>> getDatasource() {
        return datasource;
    }

    public void setDatasource(List<Map<String, ?>> datasource) {
        this.datasource = datasource;
    }

    public List<PrintColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<PrintColumn> columns) {
        this.columns = columns;
    }

    public PrintWay getPrintWay() {
        return printWay;
    }

    public void setPrintWay(PrintWay printWay) {
        this.printWay = printWay;
    }

    public boolean isHasSerialNumber() {
        return hasSerialNumber;
    }

    public void setHasSerialNumber(boolean hasSerialNumber) {
        this.hasSerialNumber = hasSerialNumber;
    }

    public String getSelfSerialNumber() {
        return selfSerialNumber;
    }

    public void setSelfSerialNumber(String selfSerialNumber) {
        this.selfSerialNumber = selfSerialNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrintLayout() {
        return printLayout;
    }

    public void setPrintLayout(String printLayout) {
        this.printLayout = printLayout;
    }

    public HashMap<String, String> getHeadContent() {
        return headContent;
    }

    public void setHeadContent(HashMap<String, String> headContent) {
        this.headContent = headContent;
    }
}
