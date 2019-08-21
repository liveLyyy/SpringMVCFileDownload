package com.liyan.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.UUID;

@Controller
public class UploadController {


    @RequestMapping("download")
    public void Download(String fileName, HttpServletResponse response, HttpServletRequest request) throws Exception{

        //设置响应流中文件进行下载
        response.setHeader("Content-Disposition","attachment;filename="+fileName);
        //把二进制流放入到响应体中.
        ServletOutputStream os = response.getOutputStream();
        String path =request.getServletContext().getRealPath("static/files");
        System.out.println(path);
        File file = new File(path, fileName);
        byte[] bytes = FileUtils.readFileToByteArray(file);
        os.write(bytes);
        os.flush();
        os.close();
    }
    @RequestMapping("")
    public String Upload(MultipartFile multipartFile,String name)throws Exception{
        String fileName=multipartFile.getOriginalFilename();
        String suffix=fileName.substring(fileName.lastIndexOf("."));
            String uuid= UUID.randomUUID().toString();
            FileUtils.copyInputStreamToFile(multipartFile.getInputStream(),new File("D:/"+uuid+suffix));
            return "index";
    }
}
