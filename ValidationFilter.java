package com.deepak.backend;

import java.io.IOException;
import javax.servlet.*;

public class ValidationFilter implements Filter {

    @Override
    public void init(FilterConfig fc) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String name   = request.getParameter("name1");
        String email  = request.getParameter("email1");
        String pass   = request.getParameter("pass1");
        String gender = request.getParameter("gender1");
        String city   = request.getParameter("city1");

        ValidationServerSide v = new ValidationServerSide();

        boolean valid = true;
        if (!v.nameValidate(name))          { request.setAttribute("error_message", "Name is not valid (3-30 letters only)."); valid = false; }
        else if (!v.emailValidate(email))   { request.setAttribute("error_message", "Email is not valid."); valid = false; }
        else if (!v.passwordValidate(pass)) { request.setAttribute("error_message", "Password must be 6-16 characters (letters, numbers, @#_)."); valid = false; }
        else if (gender == null || gender.trim().isEmpty()) { request.setAttribute("error_message", "Please select gender."); valid = false; }
        else if (city == null || city.trim().isEmpty() || city.equals("Select City")) { request.setAttribute("error_message", "Please select city."); valid = false; }

        if (!valid) {
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {}
}
