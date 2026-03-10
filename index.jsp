<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.deepak.connection.DbConnection, java.sql.*" %>
<jsp:include page="CheckCookie"/>
<%
    String email = (String) session.getAttribute("session_email");
    boolean loggedIn = (email != null && !email.trim().isEmpty() && !email.equals("null"));
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Job Portal - Find Your Dream Job</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container-fluid">
    <% if (loggedIn) { %><jsp:include page="profileheader.jsp"/>
    <% } else { %><jsp:include page="header.jsp"/><% } %>
    <jsp:include page="menubar.jsp"/>

    <div class="row" style="border:2px solid black; overflow:hidden;">
        <img src="images/jobbanner.jpg" style="width:100%;" alt="Job Banner"/>
    </div>

    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8 job_search_div">
            <h3>Find A Job at India's No.1 Job Site</h3>
            <form action="search-jobs-result.jsp" method="POST">
                <input type="text" name="technology1" placeholder="Technology / Job Title..." class="textfield_design"/>
                <input type="text" name="location1" placeholder="Location..." class="textfield_design"/>
                <input type="submit" value="Search" class="btn btn-primary"/>
            </form>
        </div>
        <div class="col-md-2"></div>
    </div>

    <div class="row">
        <div class="col-md-3">
            <ul>
                <li><a href="companies.jsp">Infosys</a></li>
                <li><a href="companies.jsp">Wipro</a></li>
                <li><a href="companies.jsp">IBM</a></li>
                <li><a href="companies.jsp">Google</a></li>
                <li><a href="companies.jsp">TCS</a></li>
            </ul>
        </div>
        <div class="col-md-6" style="background-color:#f9f8f9; margin-top:10px;">
            <h4 style="padding:10px 0 5px;">Latest Job Openings</h4>
            <div class="row">
            <%
                Connection con = null;
                try {
                    con = DbConnection.getConnect();
                    PreparedStatement ps = con.prepareStatement("SELECT * FROM jobs ORDER BY id DESC");
                    ResultSet rs = ps.executeQuery();
                    boolean hasJobs = false;
                    while (rs.next()) {
                        hasJobs = true;
                        String jid = rs.getString("id"), jobProfile = rs.getString("job_profile"),
                               company = rs.getString("company"), experience = rs.getString("experience"),
                               desc = rs.getString("description"), date1 = rs.getString("date1");
            %>
            <div class="col-md-12 display_job_div">
                <b><%= jobProfile %></b>
                <span style="font-size:12px; color:#9f9d9d; float:right;">Posted: <%= date1 %></span><br>
                <span style="color:#9f9d9d;">Company: </span> <%= company %><br>
                <span style="color:#9f9d9d;">Experience: </span> <%= experience %><br>
                <span style="color:#9f9d9d;">Description: </span> <%= desc %><br><br>
                <a href="job-description.jsp?jid=<%= jid %>" class="btn btn-sm btn-primary">See Full Details</a>
            </div>
            <%
                    }
                    if (!hasJobs) out.print("<p style='padding:15px; color:#999;'>No jobs posted yet.</p>");
                } catch (Exception e) { out.print("<p style='color:red;'>Error loading jobs: " + e.getMessage() + "</p>"); }
                finally { if (con != null) { try { con.close(); } catch (Exception e) {} } }
            %>
            </div>
        </div>
        <div class="col-md-3">
            <ul>
                <li><a href="simple-job-search.jsp">Java Developer</a></li>
                <li><a href="simple-job-search.jsp">Web Developer</a></li>
                <li><a href="simple-job-search.jsp">Data Analyst</a></li>
                <li><a href="simple-job-search.jsp">DevOps Engineer</a></li>
            </ul>
        </div>
    </div>

    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>
