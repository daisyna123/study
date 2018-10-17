package com.report;

/**
 * 打印方式枚举类
 * Created by administrator on 2018-05-17.
 */
public enum PrintWay {
    HTML(1),PRINTER(2),PDF(3),XLSX(4),DOC(5);
    int value;
    PrintWay(int i){
        this.value = i;
    }
    public int getValue(){
        return this.value;
    }
}
