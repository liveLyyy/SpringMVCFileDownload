<%--
  Created by IntelliJ IDEA.
  User: livel
  Date: 2019/8/21
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>文件下载</title>
</head>
<body>
<h3>文件下载</h3>
<a href="download?filename=${requestScope.user.image.originalFilename}">
    ${requestScope.user.image.originalFilename }
</a>
</body>
</html>
