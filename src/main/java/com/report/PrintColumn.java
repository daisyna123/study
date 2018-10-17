package com.report;

/**
 * @DESCRIPTION 打印对象
 * @AUTHER administrator zhangna
 * @create 2018-05-16
 */
public class PrintColumn {
    /**表格列名*/
    private String header;

    /**数据源列名**/
    private String columnHeader;

    /**打印类型*/
    private Class<?> clazz;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getColumnHeader() {
        return columnHeader;
    }

    public void setColumnHeader(String columnHeader) {
        this.columnHeader = columnHeader;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }
}
