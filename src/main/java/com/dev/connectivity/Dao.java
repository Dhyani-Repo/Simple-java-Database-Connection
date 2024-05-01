package com.dev.connectivity;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.Statement;

public class Dao {
	public static Connection con =null;
	static{
		try {			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project1","root","Dhyanies");
			
		}catch (Exception e) {
			System.out.println("There is some error");
			con = null;
		}
	}
	public static  Connection getConnection() {
		return con;
	}
}
