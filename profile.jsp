<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.deepak.connection.DbConnection, com.deepak.backend.PathDetails, java.sql.*, java.io.*" %>
<jsp:include page="CheckCookie"/>
<%
    String name       = (String) session.getAttribute("session_name");
    String emailS     = (String) session.getAttribute("session_email");
    String gender     = (String) session.getAttribute("session_gender");
    String city       = (String) session.getAttribute("session_city");
    String fields     = (String) session.getAttribute("session_fields");
    String profilePic = (String) session.getAttribute("session_profilepic");
    String title      = (String) session.getAttribute("session_title");
    String skills     = (String) session.getAttribute("session_skills");
    if (fields == null || fields.isEmpty()) fields = "--- Not set ---";
    if (title == null) title = "";
    if (skills == null) skills = "";
    if (profilePic == null || profilePic.isEmpty()) profilePic = "profilepic.png";
    if (name == null || name.isEmpty()) { response.sendRedirect("login.jsp"); return; }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Profile - <%= name %></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="profileheader.jsp"/>
<jsp:include page="menubar.jsp"/>

<div class="row">
    <div class="col-md-2"></div>
    <div class="col-md-8">

        <!-- Personal Info Card -->
        <div class="row" style="border:1px solid gray; box-shadow:0 0 2px gray; border-radius:3px;">
            <div class="col-md-2">
                <a href="edit-profile-pic.jsp" style="font-size:12px; float:right;"><span class="glyphicon glyphicon-pencil"></span></a>
                <img src="profilepics/<%= profilePic %>" height="100" style="border-radius:50px;" alt="Profile"/>
            </div>
            <div class="col-md-10">
                <h3 style="color:blue;"><%= name.toUpperCase() %>
                    <a href="edit-profile-about.jsp" style="font-size:12px; float:right;"><span class="glyphicon glyphicon-pencil"></span></a>
                </h3>
                <b><%= title %></b><br><br>
                <span style="color:#acaaaa;">Skills: </span><%= skills %><br>
                <span style="color:#acaaaa;">Gender: </span><%= gender %><br>
                <span style="color:#acaaaa;">City: </span><%= city %><br>
                <span style="color:#acaaaa;">Fields: </span><%= fields %><br>
            </div>
        </div><br>

        <!-- Education -->
        <div class="row" style="border:1px solid gray; box-shadow:0 0 2px gray; border-radius:3px;">
            <div class="col-md-12">
                <h4>Education Details
                    <a href="add-profile-education.jsp" style="font-size:12px; float:right;"><span class="glyphicon glyphicon-plus"></span></a>
                </h4>
                <%
                    Connection con1 = null;
                    try {
                        con1 = DbConnection.getConnect();
                        PreparedStatement ps1 = con1.prepareStatement("SELECT * FROM education WHERE email=?");
                        ps1.setString(1, emailS);
                        ResultSet rs1 = ps1.executeQuery();
                        while (rs1.next()) {
                            String eid = rs1.getString("id"), school = rs1.getString("school"),
                                   degree = rs1.getString("degree"), grade = rs1.getString("grade"), year = rs1.getString("year");
                %>
                <div class="row" style="background-color:#eeecec; margin:5px 0;">
                    <div class="col-md-2"><img src="images/school.png" height="60" alt="school"/></div>
                    <div class="col-md-10">
                        <a href="edit-profile-education.jsp?id=<%= eid %>" style="font-size:12px; float:right;"><span class="glyphicon glyphicon-pencil"></span></a>
                        <b><span class="glyphicon glyphicon-home"></span> &nbsp;<%= school %></b><br>
                        <span class="glyphicon glyphicon-book"></span> &nbsp;<%= degree %>
                        <span style="color:#858585;">(<%= year %>)</span><br>
                        <span class="glyphicon glyphicon-file"></span> &nbsp;<%= grade %>%
                    </div>
                </div>
                <% } } catch (Exception e) { out.print("<p style='color:red;'>Error: " + e.getMessage() + "</p>"); }
                   finally { if (con1 != null) { try { con1.close(); } catch (Exception e) {} } } %>
            </div>
        </div><br>

        <!-- Experience -->
        <div class="row" style="border:1px solid gray; box-shadow:0 0 2px gray; border-radius:3px;">
            <div class="col-md-12">
                <h4>Experience Details
                    <a href="add-profile-experience.jsp" style="font-size:12px; float:right;"><span class="glyphicon glyphicon-plus"></span></a>
                </h4>
                <%
                    Connection con2 = null;
                    try {
                        con2 = DbConnection.getConnect();
                        PreparedStatement ps2 = con2.prepareStatement("SELECT * FROM experience WHERE email=?");
                        ps2.setString(1, emailS);
                        ResultSet rs2 = ps2.executeQuery();
                        while (rs2.next()) {
                            String xid = rs2.getString("id"), company = rs2.getString("company"),
                                   location = rs2.getString("location"), jobTitle = rs2.getString("job_title"), yr = rs2.getString("year");
                %>
                <div class="row" style="background-color:#eeecec; margin:5px 0;">
                    <div class="col-md-2"><img src="images/school.png" height="60" alt="exp"/></div>
                    <div class="col-md-10">
                        <a href="edit-profile-experience.jsp?id=<%= xid %>" style="font-size:12px; float:right;"><span class="glyphicon glyphicon-pencil"></span></a>
                        <b><span class="glyphicon glyphicon-home"></span> &nbsp;<%= company %></b><br>
                        <span class="glyphicon glyphicon-map-marker"></span> &nbsp;<%= location %>
                        <span style="color:#858585;">(<%= yr %>)</span><br>
                        <span class="glyphicon glyphicon-briefcase"></span> &nbsp;<%= jobTitle %>
                    </div>
                </div>
                <% } } catch (Exception e) { out.print("<p style='color:red;'>Error: " + e.getMessage() + "</p>"); }
                   finally { if (con2 != null) { try { con2.close(); } catch (Exception e) {} } } %>
            </div>
        </div><br>

        <!-- Resume -->
        <div class="row" style="border:1px solid gray; box-shadow:0 0 2px gray; border-radius:3px;">
            <div class="col-md-12">
                <h4>Resume</h4>
                <div style="background-color:#ececec; padding:10px;">
                    <a href="upload-resume.jsp"><span class="glyphicon glyphicon-upload"></span> Upload Resume</a>
                    <%
                        Connection con3 = null;
                        try {
                            con3 = DbConnection.getConnect();
                            PreparedStatement ps3 = con3.prepareStatement("SELECT * FROM resumes WHERE email=?");
                            ps3.setString(1, emailS);
                            ResultSet rs3 = ps3.executeQuery();
                            if (rs3.next()) {
                                String fn = rs3.getString("path");
                    %>
                    &nbsp;&nbsp;<a href="DownloadResume?fn=<%= fn %>" class="btn btn-sm btn-success">
                        <span class="glyphicon glyphicon-download-alt"></span> Download Resume
                    </a>
                    <% } } catch (Exception e) { }
                       finally { if (con3 != null) { try { con3.close(); } catch (Exception e) {} } } %>
                </div>
                <div style="padding:10px;">
                    <a href="resume-builder-details.jsp"><span class="glyphicon glyphicon-pencil"></span> Resume Builder</a>
                    <%
                        File rbFile = new File(PathDetails.RESUME_BUILDER_PATH + emailS + ".pdf");
                        if (rbFile.exists()) {
                    %>
                    &nbsp;&nbsp;<a href="DownloadResumeBuilder?fn=<%= emailS %>.pdf" class="btn btn-sm btn-success">
                        <span class="glyphicon glyphicon-download-alt"></span> Download Built Resume
                    </a>
                    <% } %>
                </div>
            </div>
        </div><br>

    </div>
    <div class="col-md-2"></div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
