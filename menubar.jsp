<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String emailNav = (String) session.getAttribute("session_email");
    boolean loggedIn = (emailNav != null && !emailNav.trim().isEmpty() && !emailNav.equals("null"));
%>
<div class="row menubardiv">
    <div class="col-md-7" id="mynavbar">
        <ul>
            <li><a href="index.jsp">Home</a></li>
            <li><a href="simple-job-search.jsp">Search Jobs</a></li>
            <li><a href="companies.jsp">Companies</a></li>
            <% if (loggedIn) { %>
            <li><a href="my-applied-jobs.jsp">My Applied Jobs</a></li>
            <% } %>
        </ul>
    </div>
    <div class="col-md-5" id="mynavbar">
        <ul>
            <li><a href="about-us.jsp">About Us</a></li>
            <li><a href="contact-us.jsp">Contact Us</a></li>
        </ul>
    </div>
</div>
