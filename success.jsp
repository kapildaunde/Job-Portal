<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String successMsg = (String) request.getAttribute("success_message");
    if (successMsg == null) successMsg = "Operation successful.";
%>
<div class="alert alert-success alert-dismissible" style="text-align:center;" role="alert">
    <strong>Success: </strong> <%= successMsg %>
    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
        <span aria-hidden="true">&times;</span>
    </button>
</div>
