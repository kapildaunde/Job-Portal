package com.deepak.backend;
import com.deepak.connection.DbConnection;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.http.*;
public class ContactUs extends HttpServlet {
    protected void doService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name1"), email = req.getParameter("email1"),
               subject = req.getParameter("subject1"), message = req.getParameter("message1");
        String date1 = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        String time1 = new SimpleDateFormat("HH:mm:ss").format(new Date());
        Connection con = null;
        try {
            con = DbConnection.getConnect(); con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO contact_us(name, email, subject, message, date1, time1) VALUES(?,?,?,?,?,?)");
            ps.setString(1, name); ps.setString(2, email); ps.setString(3, subject);
            ps.setString(4, message); ps.setString(5, date1); ps.setString(6, time1);
            if (ps.executeUpdate() > 0) {
                con.commit();
                SendConfirmationMail.sendConfirmationMail(email, "Thank you for contacting us",
                    "Thank you " + name + "! Our team will contact you as soon as possible.");
                req.setAttribute("success_message", "Your message has been sent successfully!");
                req.getRequestDispatcher("success.jsp").include(req, resp);
                req.getRequestDispatcher("contact-us.jsp").include(req, resp);
            } else {
                con.rollback();
                req.setAttribute("error_message", "Message not sent. Please try again.");
                req.getRequestDispatcher("error.jsp").include(req, resp);
                req.getRequestDispatcher("contact-us.jsp").include(req, resp);
            }
        } catch (Exception e) {
            if (con != null) { try { con.rollback(); } catch (Exception ee) { ee.printStackTrace(); } }
            req.setAttribute("error_message", "Error: " + e.getMessage());
            req.getRequestDispatcher("error.jsp").include(req, resp);
            req.getRequestDispatcher("contact-us.jsp").include(req, resp);
        } finally { if (con != null) { try { con.close(); } catch (Exception e) { e.printStackTrace(); } } }
    }
    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException { doService(req, resp); }
    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { doService(req, resp); }
}
