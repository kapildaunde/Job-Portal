package com.deepak.backend;

import com.deepak.connection.DbConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Register extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String name   = req.getParameter("name1");
        String email  = req.getParameter("email1");
        String pass   = req.getParameter("pass1");
        String gender = req.getParameter("gender1");
        String city   = req.getParameter("city1");

        String[] fieldArr = req.getParameterValues("field1");
        String fields = "";
        if (fieldArr != null) {
            StringBuilder sb = new StringBuilder();
            for (String s : fieldArr) { sb.append(s).append(", "); }
            fields = sb.length() > 2 ? sb.substring(0, sb.length() - 2) : "";
        }

        Connection con = null;
        try {
            con = DbConnection.getConnect();
            con.setAutoCommit(false);

            PreparedStatement ps1 = con.prepareStatement(
                "INSERT INTO register(name, email, password, gender, field, city) VALUES(?,?,?,?,?,?)");
            ps1.setString(1, name);
            ps1.setString(2, email);
            ps1.setString(3, pass);
            ps1.setString(4, gender);
            ps1.setString(5, fields);
            ps1.setString(6, city);
            int i1 = ps1.executeUpdate();

            PreparedStatement ps2 = con.prepareStatement(
                "INSERT INTO about_user(email, about, skills) VALUES(?,?,?)");
            ps2.setString(1, email);
            ps2.setString(2, "");
            ps2.setString(3, "");
            int i2 = ps2.executeUpdate();

            PreparedStatement ps3 = con.prepareStatement(
                "INSERT INTO profile_pics(email, path) VALUES(?,?)");
            ps3.setString(1, email);
            ps3.setString(2, "profilepic.png");
            int i3 = ps3.executeUpdate();

            if (i1 > 0 && i2 > 0 && i3 > 0) {
                con.commit();
                HttpSession session = req.getSession();
                session.setAttribute("session_name",       name);
                session.setAttribute("session_email",      email);
                session.setAttribute("session_gender",     gender);
                session.setAttribute("session_city",       city);
                session.setAttribute("session_fields",     fields);
                session.setAttribute("session_title",      "");
                session.setAttribute("session_skills",     "");
                session.setAttribute("session_profilepic", "profilepic.png");
                resp.sendRedirect("profile.jsp");
            } else {
                con.rollback();
                req.setAttribute("error_message", "Registration failed. Please try again.");
                req.getRequestDispatcher("register.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            if (con != null) { try { con.rollback(); } catch (Exception ee) { ee.printStackTrace(); } }
            req.setAttribute("error_message", "Error: " + e.getMessage());
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        } finally {
            if (con != null) { try { con.close(); } catch (Exception e) { e.printStackTrace(); } }
        }
    }
}
