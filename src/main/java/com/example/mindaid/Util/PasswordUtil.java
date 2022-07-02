package com.example.mindaid.Util;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
@Service
public class PasswordUtil {
    public static String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
}
