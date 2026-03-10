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

public class UpdateProfilePic extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String email = (String) session.getAttribute("session_email");
        String fileName = null;
        try {
            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
            FileItem item = items.get(0);
            fileName = new File(item.getName()).getName();
            item.write(new File(PathDetails.PROFILE_PIC_PATH + fileName));
        } catch (Exception e) { e.printStackTrace(); }

        Connection con = null;
        try {
            con = DbConnection.getConnect(); con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("UPDATE profile_pics SET path=? WHERE email=?");
            ps.setString(1, fileName); ps.setString(2, email);
            if (ps.executeUpdate() > 0) {
                con.commit();
                session.setAttribute("session_profilepic", fileName);
                resp.sendRedirect("edit-profile-pic.jsp");
            } else { con.rollback(); resp.sendRedirect("edit-profile-pic.jsp"); }
        } catch (Exception e) {
            if (con != null) { try { con.rollback(); } catch (Exception ee) { ee.printStackTrace(); } }
        } finally { if (con != null) { try { con.close(); } catch (Exception e) { e.printStackTrace(); } } }
    }
}
