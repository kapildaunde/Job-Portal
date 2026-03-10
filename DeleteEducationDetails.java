package com.deepak.backend;
import com.deepak.connection.DbConnection;
import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class DeleteEducationDetails extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id1");
        Connection con = null;
        try {
            con = DbConnection.getConnect(); con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("DELETE FROM education WHERE id=?");
            ps.setString(1, id);
            if (ps.executeUpdate() > 0) { con.commit(); resp.sendRedirect("profile.jsp"); }
            else { con.rollback(); req.setAttribute("error_message", "Delete failed."); req.getRequestDispatcher("profile.jsp").forward(req, resp); }
        } catch (Exception e) {
            if (con != null) { try { con.rollback(); } catch (Exception ee) { ee.printStackTrace(); } }
        } finally { if (con != null) { try { con.close(); } catch (Exception e) { e.printStackTrace(); } } }
    }
}
