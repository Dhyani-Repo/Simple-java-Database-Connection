package com.dev.testing;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
//import java.sql.Connection;
//import java.sql.Statement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import com.dev.connectivity.Dao;

import java.util.Base64;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class testing2 extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the cookie named "EncodedUrlCookie"
		Cookie[] cookies = request.getCookies();
		String encodedUrl = null;

		// Retrieve the value of the "UserCredentials" cookie
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        if (cookie.getName().equals("UserCredentials")) {
		            encodedUrl = cookie.getValue();
		            break;
		        }
		    }
		}

		// URL decode the cookie value if it's not null
		if (encodedUrl != null) {
		    try {
		        byte[] decodedUrl = Base64.getDecoder().decode(encodedUrl);
		        String DecodedString = new String(decodedUrl);    
		        // Output the decoded URL to the response
		        response.setContentType("text/html");
		        PrintWriter out = response.getWriter();
		        out.println("Decoded URL: " + DecodedString);
		        out.close();
		    } catch (UnsupportedEncodingException e) {
		        System.err.println("Error decoding URL: " + e.getMessage());
		    }
		} else {
		    // Handle case where "UserCredentials" cookie is not found
		    response.setContentType("text/html");
		    PrintWriter out = response.getWriter();
		    out.println("No cookie named 'UserCredentials' found.");
		    out.close();
		}
    }
	
	
}
