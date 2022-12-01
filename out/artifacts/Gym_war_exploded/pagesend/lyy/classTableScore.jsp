<%@ page import="com.xhwy.gym.entity.MemberAll" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生查看成绩</title>
</head>
<body>
<%
    MemberAll member = (MemberAll) session.getAttribute("members");
    String name = member.getStudentName();
    int score = member.getScore();
%>
<h1 style="font-size:55px;margin-top: 250px" align=center><%=name%>学员,你好</h1>
<h1 align=center>您的课程成绩是:<%=score%>
</h1>
</body>
</html>
