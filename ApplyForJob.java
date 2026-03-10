package com.deepak.backend;
import com.deepak.connection.DbConnection;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.http.*;
public class ApplyForJob extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = (String) req.getSession().getAttribute("session_email");
        String jid   = req.getParameter("jobid");
        String date1 = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        String time1 = new SimpleDateFormat("HH:mm:ss").format(new Date());
        Connection con = null;
        try {
            con = DbConnection.getConnect(); con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO applied_jobs(email, jobid, date1, time1) VALUES(?,?,?,?)");
            ps.setString(1, email); ps.setString(2, jid); ps.setString(3, date1); ps.setString(4, time1);
            if (ps.executeUpdate() > 0) {
                con.commit();
                req.setAttribute("success_message", "You have successfully applied for this job!");
                req.getRequestDispatcher("success.jsp").include(req, resp);
                req.getRequestDispatcher("job-description.jsp?jid=" + jid).include(req, resp);
            } else {
                con.rollback();
                req.setAttribute("error_message", "Job application failed. Please try again.");
                req.getRequestDispatcher("error.jsp").include(req, resp);
                req.getRequestDispatcher("job-description.jsp?jid=" + jid).include(req, resp);
            }
        } catch (Exception e) {
            if (con != null) { try { con.rollback(); } catch (Exception ee) { ee.printStackTrace(); } }
            req.setAttribute("error_message", "Error: " + e.getMessage());
            req.getRequestDispatcher("error.jsp").include(req, resp);
            req.getRequestDispatcher("job-description.jsp?jid=" + jid).include(req, resp);
        } finally { if (con != null) { try { con.close(); } catch (Exception e) { e.printStackTrace(); } } }
    }
}
