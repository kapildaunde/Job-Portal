package com.deepak.backend;

import com.deepak.connection.DbConnection;
import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class EditProfileAbout extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        String email  = (String) session.getAttribute("session_email");
        String name   = req.getParameter("name1");
        String city   = req.getParameter("city1");
        String gender = req.getParameter("gender1");
        String title  = req.getParameter("title1");
        String skills = req.getParameter("skills1");

        Connection con = null;
        try {
            con = DbConnection.getConnect();
            con.setAutoCommit(false);

            PreparedStatement ps1 = con.prepareStatement(
                "UPDATE register SET name=?, city=?, gender=? WHERE email=?");
            ps1.setString(1, name); ps1.setString(2, city);
            ps1.setString(3, gender); ps1.setString(4, email);
            int i1 = ps1.executeUpdate();

            PreparedStatement ps2 = con.prepareStatement(
                "UPDATE about_user SET about=?, skills=? WHERE email=?");
            ps2.setString(1, title); ps2.setString(2, skills); ps2.setString(3, email);
            int i2 = ps2.executeUpdate();

            if (i1 > 0 && i2 > 0) {
                con.commit();
                session.setAttribute("session_name",   name);
                session.setAttribute("session_gender", gender);
                session.setAttribute("session_city",   city);
                session.setAttribute("session_title",  title);
                session.setAttribute("session_skills", skills);
                resp.sendRedirect("profile.jsp");
            } else {
                con.rollback();
                req.setAttribute("error_message", "Update failed.");
                req.getRequestDispatcher("edit-profile-about.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            if (con != null) { try { con.rollback(); } catch (Exception ee) { ee.printStackTrace(); } }
            req.setAttribute("error_message", e.getMessage());
            req.getRequestDispatcher("edit-profile-about.jsp").forward(req, resp);
        } finally {
            if (con != null) { try { con.close(); } catch (Exception e) { e.printStackTrace(); } }
        }
    }
}
