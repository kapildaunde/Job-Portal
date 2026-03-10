package com.deepak.backend;
import com.deepak.connection.DbConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class JobSearchByTechnology extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String tech = req.getParameter("technology");
        Connection con = null;
        try {
            con = DbConnection.getConnect();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM jobs WHERE job_profile LIKE ?");
            ps.setString(1, "%" + tech + "%");
            ResultSet rs = ps.executeQuery();
            boolean found = false;
            while (rs.next()) {
                found = true;
                String id = rs.getString("id"), job_profile = rs.getString("job_profile"),
                       company = rs.getString("company"), experience = rs.getString("experience"),
                       description = rs.getString("description"), date1 = rs.getString("date1"), time1 = rs.getString("time1");
                out.print("<div class=\"col-md-12 display_job_div\">" +
                    "<b>" + job_profile + "</b> <span style=\"font-size:12px;color:#9f9d9d;float:right\">(" + date1 + " &amp; " + time1 + ")</span><br>" +
                    "<span style=\"color:#9f9d9d\">Company: </span>" + company + "<br>" +
                    "<span style=\"color:#9f9d9d\">Experience: </span>" + experience + "<br>" +
                    "<span style=\"color:#9f9d9d\">Description: </span>" + description + "<br>" +
                    "<br><a href=\"job-description.jsp?jid=" + id + "\"> See Full Details </a></div>");
            }
            if (!found) out.print("<p style=\"color:#999; padding:20px;\">No jobs found for <b>" + tech + "</b></p>");
        } catch (Exception e) { out.print("<p style=\"color:red;\">Error: " + e.getMessage() + "</p>"); }
        finally { if (con != null) { try { con.close(); } catch (Exception e) { e.printStackTrace(); } } }
    }
}
