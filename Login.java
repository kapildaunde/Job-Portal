package com.deepak.backend;

import com.deepak.connection.DbConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email  = req.getParameter("email1");
        String pass   = req.getParameter("pass1");
        String remMe  = req.getParameter("rememberme1");

        Connection con = null;
        try {
            con = DbConnection.getConnect();
            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM register WHERE email=? AND password=?");
            ps.setString(1, email);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Set remember-me cookies
                if ("rememberme".equals(remMe)) {
                    Cookie ck1 = new Cookie("cookie_email", email);
                    ck1.setMaxAge(60 * 60 * 24 * 365);
                    resp.addCookie(ck1);
                    Cookie ck2 = new Cookie("cookie_status", "true");
                    ck2.setMaxAge(60 * 60 * 24 * 365);
                    resp.addCookie(ck2);
                }
                // Load user data into session
                RequestDispatcher rd = req.getRequestDispatcher("GetUserData");
                rd.include(req, resp);
                resp.sendRedirect("profile.jsp");
            } else {
                req.setAttribute("error_message", "Invalid email or password.");
                RequestDispatcher rd1 = req.getRequestDispatcher("header.jsp");   rd1.include(req, resp);
                RequestDispatcher rd2 = req.getRequestDispatcher("menubar.jsp");  rd2.include(req, resp);
                RequestDispatcher rd3 = req.getRequestDispatcher("error.jsp");    rd3.include(req, resp);
                RequestDispatcher rd4 = req.getRequestDispatcher("logindiv.jsp"); rd4.include(req, resp);
                RequestDispatcher rd5 = req.getRequestDispatcher("footer.jsp");   rd5.include(req, resp);
            }
        } catch (Exception e) {
            req.setAttribute("error_message", "Server error: " + e.getMessage());
            RequestDispatcher rd1 = req.getRequestDispatcher("header.jsp");   rd1.include(req, resp);
            RequestDispatcher rd2 = req.getRequestDispatcher("menubar.jsp");  rd2.include(req, resp);
            RequestDispatcher rd3 = req.getRequestDispatcher("error.jsp");    rd3.include(req, resp);
            RequestDispatcher rd4 = req.getRequestDispatcher("logindiv.jsp"); rd4.include(req, resp);
            RequestDispatcher rd5 = req.getRequestDispatcher("footer.jsp");   rd5.include(req, resp);
        } finally {
            if (con != null) { try { con.close(); } catch (Exception e) { e.printStackTrace(); } }
        }
    }
}
