package com.dev.testing;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import java.sql.Statement;
import java.util.Base64;

import org.json.simple.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.dev.connectivity.Dao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class testing extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw = resp.getWriter();
		resp.setContentType("text/html");
		Connection connect = Dao.getConnection();
		if(connect!=null) {
			Statement stmt;
			try {
				stmt = connect.createStatement();
				ResultSet rs = stmt.executeQuery("select * from table1");
				while(rs.next()) {
					pw.println(" id\t: "+rs.getInt(1)+"\n name\t :"+rs.getString(2));
					pw.println();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			pw.println("Your Servlet doGet method is working......");
		}
		pw.close();	
	}
	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection connect = Dao.getConnection();
		if(connect != null) {
			try {				
				Statement stmt = connect.createStatement();
				int rs =  stmt.executeUpdate("insert into table1(Full_Name) values (\"Junoon\")");
				System.out.println("No of rows inserted : "+rs);
				
			}catch(Exception e) {
				System.out.println("Sql Error");
			}
		}
//		System.out.println("Hello Universe");
		String username = req.getParameter("mailing");
		String userpass = req.getParameter("pass");
		JSONObject Jobj = new JSONObject();
		Jobj.put("userid", username);
		Jobj.put("userpass", userpass);
		String Sstr = Jobj.toString();
		String encodedValue = Base64.getEncoder().encodeToString(Sstr.getBytes());
		System.out.println(Sstr);
		System.out.println(Jobj);
		Cookie userCookie = new Cookie("UserCredentials", encodedValue);
        userCookie.setMaxAge(3600); 
        userCookie.setPath("/"); 
        resp.addCookie(userCookie);
		PrintWriter pw = resp.getWriter();
		resp.setContentType("text/html");
		System.out.println(username);
		System.out.println(userpass);
		pw.println("Your Servlet doPost method is working...");
		pw.println("User Name : "+ username);
		pw.println("User Pass : "+ userpass);
		pw.close();
	}
}
