package com.deepak.backend;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

public class CheckCookie extends HttpServlet {

    protected void doService(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        String email = (String) session.getAttribute("session_email");

        if (email == null || email.trim().isEmpty() || email.equals("null")) {
            Cookie[] cookies = req.getCookies();
            if (cookies != null) {
                for (Cookie c : cookies) {
                    if ("cookie_email".equals(c.getName())) {
                        email = c.getValue();
                        req.setAttribute("email1", email);
                        req.getRequestDispatcher("GetUserData").include(req, resp);
                        return;
                    }
                }
            }
        }
    }

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException { doService(req, resp); }
    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { doService(req, resp); }
}
