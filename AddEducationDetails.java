package com.deepak.backend;

import com.deepak.connection.DbConnection;
import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AddEducationDetails extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email  = (String) req.getSession().getAttribute("session_email");
        String school = req.getParameter("school1");
        String degree = req.getParameter("degree1");
        String year   = req.getParameter("year1");
        String grade  = req.getParameter("grade1");
        String desc   = req.getParameter("description1");

        Connection con = null;
        try {
            con = DbConnection.getConnect();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO education(email, school, degree, year, grade, description) VALUES(?,?,?,?,?,?)");
            ps.setString(1, email); ps.setString(2, school); ps.setString(3, degree);
            ps.setString(4, year);  ps.setString(5, grade);  ps.setString(6, desc);
            if (ps.executeUpdate() > 0) { con.commit(); resp.sendRedirect("profile.jsp"); }
            else { con.rollback(); req.setAttribute("error_message", "Failed to add education."); req.getRequestDispatcher("add-profile-education.jsp").forward(req, resp); }
        } catch (Exception e) {
            if (con != null) { try { con.rollback(); } catch (Exception ee) { ee.printStackTrace(); } }
            req.setAttribute("error_message", e.getMessage());
            req.getRequestDispatcher("add-profile-education.jsp").forward(req, resp);
        } finally {
            if (con != null) { try { con.close(); } catch (Exception e) { e.printStackTrace(); } }
        }
    }
}
