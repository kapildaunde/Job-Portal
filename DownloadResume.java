package com.deepak.backend;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class DownloadResume extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileName = req.getParameter("fn");
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        try (FileInputStream fis = new FileInputStream(PathDetails.RESUME_PATH + fileName);
             OutputStream out = resp.getOutputStream()) {
            byte[] buf = new byte[4096]; int n;
            while ((n = fis.read(buf)) != -1) out.write(buf, 0, n);
        }
    }
}
