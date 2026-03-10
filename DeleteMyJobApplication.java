package com.deepak.backend;
import com.deepak.connection.DbConnection;
import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class DeleteMyJobApplication extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = (String) req.getSession().getAttribute("session_email");
        String jid   = req.getParameter("jobid");
        Connection con = null;
        try {
            con = DbConnection.getConnect(); con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("DELETE FROM applied_jobs WHERE email=? AND jobid=?");
            ps.setString(1, email); ps.setString(2, jid);
            if (ps.executeUpdate() > 0) { con.commit(); resp.sendRedirect("my-applied-jobs.jsp"); }
            else { con.rollback(); resp.sendRedirect("my-applied-jobs.jsp"); }
        } catch (Exception e) {
            if (con != null) { try { con.rollback(); } catch (Exception ee) { ee.printStackTrace(); } }
        } finally { if (con != null) { try { con.close(); } catch (Exception e) { e.printStackTrace(); } } }
    }
}
