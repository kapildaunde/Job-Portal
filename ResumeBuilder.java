package com.deepak.backend;

import com.deepak.connection.DbConnection;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.http.*;

public class ResumeBuilder extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        String name       = (String) session.getAttribute("session_name");
        String email      = (String) session.getAttribute("session_email");
        String profilepic = (String) session.getAttribute("session_profilepic");

        String address     = req.getParameter("address1");
        String career      = req.getParameter("career1");
        String hobbies     = req.getParameter("hobbies1");
        String dob         = req.getParameter("dob1");
        String genderMar   = req.getParameter("gendermaritial1");
        String language    = req.getParameter("language1");

        try {
            String pdfName = email + ".pdf";
            Document doc   = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream(PathDetails.RESUME_BUILDER_PATH + pdfName));
            doc.open();

            // Title
            doc.add(new Paragraph("RESUME", FontFactory.getFont("Arial", 20, Font.BOLD, BaseColor.DARK_GRAY)));
            doc.add(new Paragraph(" "));

            // Profile image + name
            try {
                Image img = Image.getInstance(PathDetails.PROFILE_PIC_PATH + profilepic);
                img.scaleAbsolute(90f, 90f);
                doc.add(img);
            } catch (Exception e) { /* image optional */ }
            doc.add(new Paragraph(name, FontFactory.getFont("Arial", 14, Font.BOLD)));
            doc.add(new Paragraph("(" + email + ")", FontFactory.getFont("Arial", 10)));
            doc.add(new Paragraph(" "));

            // Career Objective
            doc.add(new Paragraph("Career Objective", FontFactory.getFont("Arial", 11, Font.ITALIC, BaseColor.BLUE)));
            doc.add(new Paragraph(career != null ? career : ""));
            doc.add(new Paragraph(" "));

            // Education Table
            doc.add(new Paragraph("Educational Qualifications", FontFactory.getFont("Arial", 11, Font.ITALIC, BaseColor.BLUE)));
            PdfPTable eduTable = new PdfPTable(4);
            eduTable.setSpacingBefore(10);
            for (String h : new String[]{"Year", "School/College", "Degree", "Grade"}) eduTable.addCell(h);
            Connection con1 = DbConnection.getConnect();
            PreparedStatement ps1 = con1.prepareStatement("SELECT * FROM education WHERE email=?");
            ps1.setString(1, email);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                eduTable.addCell(rs1.getString("year"));
                eduTable.addCell(rs1.getString("school"));
                eduTable.addCell(rs1.getString("degree"));
                eduTable.addCell(rs1.getString("grade"));
            }
            con1.close();
            doc.add(eduTable);
            doc.add(new Paragraph(" "));

            // Experience Table
            doc.add(new Paragraph("Experience", FontFactory.getFont("Arial", 11, Font.ITALIC, BaseColor.BLUE)));
            PdfPTable expTable = new PdfPTable(4);
            expTable.setSpacingBefore(10);
            for (String h : new String[]{"Year", "Company", "Location", "Job Title"}) expTable.addCell(h);
            Connection con2 = DbConnection.getConnect();
            PreparedStatement ps2 = con2.prepareStatement("SELECT * FROM experience WHERE email=?");
            ps2.setString(1, email);
            ResultSet rs2 = ps2.executeQuery();
            while (rs2.next()) {
                expTable.addCell(rs2.getString("year"));
                expTable.addCell(rs2.getString("company"));
                expTable.addCell(rs2.getString("location"));
                expTable.addCell(rs2.getString("job_title"));
            }
            con2.close();
            doc.add(expTable);
            doc.add(new Paragraph(" "));

            // Hobbies
            doc.add(new Paragraph("Hobbies", FontFactory.getFont("Arial", 11, Font.ITALIC, BaseColor.BLUE)));
            doc.add(new Paragraph(hobbies != null ? hobbies : ""));
            doc.add(new Paragraph(" "));

            // Personal Info
            doc.add(new Paragraph("Personal Information", FontFactory.getFont("Arial", 11, Font.ITALIC, BaseColor.BLUE)));
            doc.add(new Paragraph("Date of Birth : " + (dob != null ? dob : "")));
            doc.add(new Paragraph("Gender / Marital Status : " + (genderMar != null ? genderMar : "")));
            doc.add(new Paragraph("Language Proficiency : " + (language != null ? language : "")));
            doc.add(new Paragraph("Address : " + (address != null ? address : "")));
            doc.add(new Paragraph(" "));

            // Declaration
            doc.add(new Paragraph("DECLARATION", FontFactory.getFont("Arial", 11, Font.ITALIC, BaseColor.BLUE)));
            doc.add(new Paragraph("I hereby declare that the above information is true to the best of my knowledge."));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph("Name : " + name));
            doc.add(new Paragraph("Date : " + new SimpleDateFormat("dd/MM/yyyy").format(new Date())));

            doc.close();
            resp.sendRedirect("profile.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("profile.jsp");
        }
    }
}
