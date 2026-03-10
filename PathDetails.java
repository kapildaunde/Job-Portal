package com.deepak.backend;

import javax.servlet.ServletContext;

/**
 * Update these paths to match your server's actual deployment directory.
 * For Tomcat, typically: C:/Program Files/Apache Software Foundation/Tomcat 9.0/webapps/JobPortal/
 */
public class PathDetails {

    // Update this base path to your actual deployment location
    private static final String BASE = "C:/JobPortal/";

    public static final String PROFILE_PIC_PATH    = BASE + "profilepics/";
    public static final String RESUME_PATH         = BASE + "resumes/";
    public static final String RESUME_BUILDER_PATH = BASE + "resumes-builder/";
    public static final String COMPANY_LOGO_PATH   = BASE + "company-logo/";
}
