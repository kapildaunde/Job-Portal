package com.deepak.backend;

import com.deepak.connection.DbConnection;
import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class EditEducationDetails extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id1"), school = req.getParameter("school1"),
               degree = req.getParameter("degree1"), year = req.getParameter("year1"),
               grade = req.getParameter("grade1"), desc = req.getParameter("description1");
        Connection con = null;
        try {
            con = DbConnection.getConnect(); con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(
                "UPDATE education SET school=?, degree=?, year=?, grade=?, description=? WHERE id=?");
            ps.setString(1, school); ps.setString(2, degree); ps.setString(3, year);
            ps.setString(4, grade);  ps.setString(5, desc);   ps.setString(6, id);
            if (ps.executeUpdate() > 0) { con.commit(); resp.sendRedirect("profile.jsp"); }
            else { con.rollback(); req.setAttribute("error_message", "Update failed."); req.getRequestDispatcher("edit-profile-education.jsp").forward(req, resp); }
        } catch (Exception e) {
            if (con != null) { try { con.rollback(); } catch (Exception ee) { ee.printStackTrace(); } }
            req.setAttribute("error_message", e.getMessage());
            req.getRequestDispatcher("edit-profile-education.jsp").forward(req, resp);
        } finally { if (con != null) { try { con.close(); } catch (Exception e) { e.printStackTrace(); } } }
    }
}
