package com.deepak.backend;
import com.deepak.connection.DbConnection;
import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class AddExperienceDetails extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email    = (String) req.getSession().getAttribute("session_email");
        String company  = req.getParameter("company1");
        String location = req.getParameter("location1");
        String year     = req.getParameter("year1");
        String jobTitle = req.getParameter("jobtitle1");
        String desc     = req.getParameter("description1");
        Connection con = null;
        try {
            con = DbConnection.getConnect(); con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO experience(email, company, location, year, job_title, description) VALUES(?,?,?,?,?,?)");
            ps.setString(1, email); ps.setString(2, company); ps.setString(3, location);
            ps.setString(4, year);  ps.setString(5, jobTitle); ps.setString(6, desc);
            if (ps.executeUpdate() > 0) { con.commit(); resp.sendRedirect("profile.jsp"); }
            else { con.rollback(); req.setAttribute("error_message", "Failed to add experience."); req.getRequestDispatcher("add-profile-experience.jsp").forward(req, resp); }
        } catch (Exception e) {
            if (con != null) { try { con.rollback(); } catch (Exception ee) { ee.printStackTrace(); } }
        } finally { if (con != null) { try { con.close(); } catch (Exception e) { e.printStackTrace(); } } }
    }
}
