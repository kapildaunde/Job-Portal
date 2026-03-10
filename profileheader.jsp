<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String name = (String) session.getAttribute("session_name");
    String profilePic = (String) session.getAttribute("session_profilepic");
    if (profilePic == null || profilePic.isEmpty()) profilePic = "profilepic.png";
%>
<div class="row header_bg">
    <div class="col-md-4">
        <a href="index.jsp">
            <img src="images/smartprogramminglogo.png" height="50" alt="Logo"/>
            <span class="logo_text_design"> Job Portal </span>
        </a>
    </div>
    <div class="col-md-6">
        <a href="profile.jsp" style="text-decoration:none; color:white; font-size:18px;">
            <img src="profilepics/<%= profilePic %>" height="30" style="border-radius:15px;" alt="profile"/>
            &nbsp;<%= name %>
        </a>
    </div>
    <div class="col-md-2">
        <a href="Logout" class="hyperlinks_design">Logout</a>
    </div>
</div>
