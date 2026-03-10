package com.deepak.backend;
import com.deepak.connection.DbConnection;
import java.io.*;
import java.sql.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
public class UpdateResume extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = (String) req.getSession().getAttribute("session_email");
        String fileName = null;
        try {
            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
            FileItem item = items.get(0);
            fileName = new File(item.getName()).getName();
            item.write(new File(PathDetails.RESUME_PATH + fileName));
        } catch (Exception e) { e.printStackTrace(); }
        Connection con = null;
        try {
            con = DbConnection.getConnect(); con.setAutoCommit(false);
            PreparedStatement ps1 = con.prepareStatement("SELECT * FROM resumes WHERE email=?");
            ps1.setString(1, email);
            ResultSet rs = ps1.executeQuery();
            PreparedStatement ps2;
            if (rs.next()) {
                ps2 = con.prepareStatement("UPDATE resumes SET path=? WHERE email=?");
                ps2.setString(1, fileName); ps2.setString(2, email);
            } else {
                ps2 = con.prepareStatement("INSERT INTO resumes(email, path) VALUES(?,?)");
                ps2.setString(1, email); ps2.setString(2, fileName);
            }
            if (ps2.executeUpdate() > 0) { con.commit(); resp.sendRedirect("profile.jsp"); }
            else { con.rollback(); resp.sendRedirect("upload-resume.jsp"); }
        } catch (Exception e) {
            if (con != null) { try { con.rollback(); } catch (Exception ee) { ee.printStackTrace(); } }
        } finally { if (con != null) { try { con.close(); } catch (Exception e) { e.printStackTrace(); } } }
    }
}
