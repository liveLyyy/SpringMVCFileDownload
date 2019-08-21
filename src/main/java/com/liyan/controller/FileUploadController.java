package com.liyan.controller;

import com.liyan.domain.User;
import org.apache.commons.io.FileUtils;
import org.aspectj.util.FileUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Controller
public class FileUploadController {

    @RequestMapping(value = "/{formName}")
    public String loginFrom(@PathVariable String formName) {
        return formName;
    }

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public String Upload(MultipartFile multipartFile, @RequestParam("description") String name, @RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
        System.out.println(name);
        //如果文件不为空，写入上传路径
        if (!file.isEmpty()) {
            //上传文件路径
            String path = request.getServletContext().getRealPath("static/files/");
            //上传文件名
            String filename = file.getOriginalFilename();
            File filepath = new File(path, filename);
            //判断路径是否存在，如果不存在就创建一个
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            //将上传文件保存到一个目标文件当中
            file.transferTo(new File(path + File.separator + filename));
            return "success";
        }
        return "error";
    }

    @RequestMapping("register")
    public String registar(HttpServletRequest request, @ModelAttribute User user, Model model) throws Exception {
        System.out.println(user.getUsername());
        //如果文件不为空，写入上传路径
        if (!user.getImage().isEmpty()) {
            //上传文件路径
            String path = request.getServletContext().getRealPath("static/files/");
            //上传文件名
            String filename = user.getImage().getOriginalFilename();
            File filepath = new File(path, filename);
            //判断路径是否存在，如果不存在就创建一个
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            //将上传文件保存到一个目标文件当中
            user.getImage().transferTo(new File(path + File.separator + filename));
            //将用户添加到model
            model.addAttribute("user", user);
            return "userInfo";
        } else {
            return "error";
        }
    }

    @RequestMapping("download")
    public ResponseEntity<byte[]> Download(@RequestParam("filename") String filename, HttpServletRequest request, Model model) throws Exception {
        //下载文件路径
        String path = request.getServletContext().getRealPath("static/files/");
        File file = new File(path + File.separator + filename);
        HttpHeaders headers = new HttpHeaders();
        //下载显示的文件名，解决中文名称乱码问题
        String downloadFielName = new String(filename.getBytes("UTF-8"), "iso-8859-1");
        //通知浏览器以attachment（下载方式）打开图片
        headers.setContentDispositionFormData("attachment", downloadFielName);
        //application/octet-stream ： 二进制流数据（最常见的文件下载）
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }


}
