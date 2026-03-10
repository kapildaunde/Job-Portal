package com.deepak.backend;

import com.deepak.connection.DbConnection;
import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class GetUserData extends HttpServlet {

    protected void doService(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email;
        try {
            email = req.getParameter("email1");
            if (email == null || email.equals("null")) email = (String) req.getAttribute("email1");
        } catch (Exception e) {
            email = (String) req.getAttribute("email1");
        }

        Connection con = null;
        try {
            con = DbConnection.getConnect();

            PreparedStatement ps1 = con.prepareStatement("SELECT * FROM register WHERE email=?");
            ps1.setString(1, email);
            ResultSet rs1 = ps1.executeQuery();

            if (rs1.next()) {
                HttpSession session = req.getSession();
                session.setAttribute("session_name",   rs1.getString("name"));
                session.setAttribute("session_email",  email);
                session.setAttribute("session_gender", rs1.getString("gender"));
                session.setAttribute("session_city",   rs1.getString("city"));
                session.setAttribute("session_fields", rs1.getString("field"));

                // About
                PreparedStatement ps2 = con.prepareStatement("SELECT * FROM about_user WHERE email=?");
                ps2.setString(1, email);
                ResultSet rs2 = ps2.executeQuery();
                String title = "", skills = "";
                if (rs2.next()) { title = rs2.getString("about"); skills = rs2.getString("skills"); }
                session.setAttribute("session_title",  title);
                session.setAttribute("session_skills", skills);

                // Profile pic
                PreparedStatement ps3 = con.prepareStatement("SELECT path FROM profile_pics WHERE email=?");
                ps3.setString(1, email);
                ResultSet rs3 = ps3.executeQuery();
                String pic = "profilepic.png";
                if (rs3.next()) pic = rs3.getString("path");
                session.setAttribute("session_profilepic", pic);

                resp.sendRedirect("profile.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) { try { con.close(); } catch (Exception e) { e.printStackTrace(); } }
        }
    }

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException { doService(req, resp); }
    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { doService(req, resp); }
}
