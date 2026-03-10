<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="CheckCookie"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register - Job Portal</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        function clearError(id) { document.getElementById(id).innerHTML = ""; }
        function validation() {
            var flag = true;
            var name2    = document.regform.name1.value;
            var email2   = document.regform.email1.value;
            var pass2    = document.regform.pass1.value;
            var gender2  = document.regform.gender1.value;
            var city2    = document.regform.city1.value;
            var nameP    = /^[a-zA-Z ]{3,30}$/;
            var emailP   = /^([a-zA-Z0-9])(([a-zA-Z0-9])*([\._\+-])*([a-zA-Z0-9]))*@(([a-zA-Z0-9\-])+(\.))+([a-zA-Z]{2,4})+$/;
            var passP    = /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,16}$/;
            if (!name2.match(nameP))  { document.getElementById('name_error').innerHTML  = "Name must be 3-30 letters."; flag = false; }
            if (!email2.match(emailP)){ document.getElementById('email_error').innerHTML = "Enter a valid email."; flag = false; }
            if (!pass2.match(passP))  { document.getElementById('pass_error').innerHTML  = "Password: 6-16 chars, needs a number and special character."; flag = false; }
            if (!gender2)             { document.getElementById('gender_error').innerHTML = "Please select gender."; flag = false; }
            if (city2 === "Select City") { document.getElementById('city_error').innerHTML = "Please select a city."; flag = false; }
            return flag;
        }
    </script>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="header.jsp"/>
    <jsp:include page="menubar.jsp"/>
    <%
        String errMsg = (String) request.getAttribute("error_message");
        if (errMsg != null) {
    %>
    <div class="alert alert-danger" style="text-align:center;"><%= errMsg %></div>
    <% } %>
    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <div class="reg_div_design">
                <form method="post" name="regform" onsubmit="return validation()" action="reg">
                    <br><h2>Register Here</h2><br>
                    <input type="text" placeholder="Full Name" name="name1" class="reglog_tf_design" onkeyup="clearError('name_error')"/><br>
                    <span id="name_error" style="color:red;font-size:12px;"></span><br>

                    <input type="text" placeholder="Email Address" name="email1" class="reglog_tf_design" onkeyup="clearError('email_error')"/><br>
                    <span id="email_error" style="color:red;font-size:12px;"></span><br>

                    <input type="password" placeholder="Password" name="pass1" class="reglog_tf_design" onkeyup="clearError('pass_error')"/><br>
                    <span id="pass_error" style="color:red;font-size:12px;"></span><br>

                    <b>Gender: </b>
                    <input type="radio" name="gender1" value="Male"/> Male &nbsp;
                    <input type="radio" name="gender1" value="Female"/> Female<br>
                    <span id="gender_error" style="color:red;font-size:12px;"></span><br>

                    <b>Fields of Interest: </b><br>
                    <input type="checkbox" name="field1" value="Information Technology"/> Information Technology<br>
                    <input type="checkbox" name="field1" value="Marketing"/> Marketing &nbsp;
                    <input type="checkbox" name="field1" value="Finance"/> Finance<br><br>

                    <select name="city1" class="reglog_tf_design">
                        <option>Select City</option>
                        <option value="Chandigarh">Chandigarh</option>
                        <option value="Panchkula">Panchkula</option>
                        <option value="Mohali">Mohali</option>
                        <option value="Delhi">Delhi</option>
                        <option value="Mumbai">Mumbai</option>
                        <option value="Bangalore">Bangalore</option>
                        <option value="Hyderabad">Hyderabad</option>
                        <option value="Pune">Pune</option>
                    </select><br>
                    <span id="city_error" style="color:red;font-size:12px;"></span><br>

                    <input type="submit" value="Register" class="btn btn-primary"/><br><br>
                    <p>Already have an account? <a href="login.jsp">Login here</a></p><br>
                </form>
            </div>
        </div>
        <div class="col-md-3"></div>
    </div>
    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>
