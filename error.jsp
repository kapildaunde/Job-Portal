<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String errorMsg = (String) request.getAttribute("error_message");
    if (errorMsg == null) errorMsg = "An error occurred.";
%>
<div class="alert alert-danger alert-dismissible" style="text-align:center;" role="alert">
    <strong>Error: </strong> <%= errorMsg %>
    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
        <span aria-hidden="true">&times;</span>
    </button>
</div>
