package com.downloadfFIle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * @DESCRIPTION 下载模板
 * @AUTHER administrator zhangna
 * @create 2018-06-11
 */
@Controller
@RequestMapping("/test")
public class DownFile {
    @RequestMapping("/downloadExcel")
    @ResponseBody
    public void downloadExcel(HttpServletRequest request, HttpServletResponse response) throws IOException{
        InputStream resourceAsStream = this.getClass().getResourceAsStream("/品项档案导入模板.xls");

        //input转byte[]
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
        while ((rc = resourceAsStream.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        byte[] in2b = swapStream.toByteArray();

        //下载
        downloadFile(response,request,"品项档案导入模板.xls",in2b);
    }
    public void downloadFile(HttpServletResponse response,HttpServletRequest request,
                             String fileName,byte[] filedata) throws IOException {
        OutputStream myout = null;
        //检查获取的数据
        if(filedata == null){
            response.sendError(1);
        }
        if(request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
            fileName = new String(fileName.getBytes("GBK"),"iso-8859-1");
        }else{
            fileName = URLEncoder.encode(fileName, "utf-8");
        }
        response.setContentType("multipart/form-data");
        response.setHeader("content-disposition","attachment;filename="+fileName);
        // 写明要下载的文件的大小
        response.setContentLength(filedata.length);
        // 从response对象中得到输出流,准备下载
        myout = response.getOutputStream();
        myout.write(filedata);
        myout.flush();
        if(myout != null){
            myout.close();
        }

    }

}
