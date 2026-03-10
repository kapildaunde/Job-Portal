<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="row">
    <div class="col-md-3"></div>
    <div class="col-md-6">
        <div class="reg_div_design">
            <form method="POST" action="login">
                <br><h2>Login Here</h2><br>
                <input type="text" placeholder="Enter Email" name="email1" class="reglog_tf_design"/><br><br>
                <input type="password" placeholder="Enter Password" name="pass1" class="reglog_tf_design"/><br><br>
                <input type="checkbox" name="rememberme1" value="rememberme"/> Remember Me<br><br>
                <input type="submit" value="Login" class="btn btn-primary"/><br><br>
                <p>Don't have an account? <a href="register.jsp">Register here</a></p><br>
            </form>
        </div>
    </div>
    <div class="col-md-3"></div>
</div>
