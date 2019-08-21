SpringMVC文件下载
=================
1、在访问资源需要下载的时候，如果响应头中没有设置Content-Disposition，浏览器默认按照inline值进行处理，inline：能显示则显示，不能显示就下载<br>
2、只需要修改响应头中 Context-Diposition=“attachment;filename=文件名”attachment 下载，以附件形式下载，filename  就是下载时显示的下载文件名<br>
3、导入包：commons-fileupload、commons-io；在 jsp 中添加超链接,设置要下载文件，在 springmvc 中放行静态资源 files 文件夹<br>
```java
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
}
```
```html
<a href="download?fileName=a.rar">下载</a>
```

SpringMVC文件上传
=================
1、基于apache的commons-fileupload.jar<br>
2、Spring中MultipartResovler组件的作用<br>
>2.1、把客户端上传的文件流转换成MutipartFile封装类<br>
>2.2、通过MutipartFile封装类获取到文件流<br>

3、表单数据类型分类:在<form>的 enctype 属性控制表单类型<br>
>3.1、默认值 application/x-www-form-urlencoded，为普通表单数据（少量文字信息）<br>
>3.2、text/plain  大文字量时使用的类型，例如邮件或论文<br>
>3.3、multipart/form-data 表单中包含二进制文件内容时用此属性值<br>

4、导入包：commons-fileupload、commons-io；在 jsp 中添加超链接<br>