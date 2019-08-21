<%--
  Created by IntelliJ IDEA.
  User: ly
  Date: 2019/8/16
  Time: 10:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件下载</title>
</head>
<body>
<%--<a href="download?fileName=a.rar">下载</a>--%>
<form action="upload" enctype="multipart/form-data" method="post">
    姓名:<input type="text" name="name"/><br/>
    文件:<input type="file" name="file"/><br/>
    <input type="submit" value="提交"/>
</form>
</body>
</html>
