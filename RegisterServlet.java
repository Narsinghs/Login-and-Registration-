package com.project.register;

import java.io.*;  
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;  
  @WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {  
public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
  
response.setContentType("text/html");  
PrintWriter out = response.getWriter();  

//Retrieve username and Password
String name=request.getParameter("name");  
String password=request.getParameter("password");  

try{  
Class.forName("com.mysql.jdbc.Driver");  
//Create Connection
Connection con=DriverManager.getConnection( "jdbc:mysql://localhost:3306/register", "root","Narsingh_@$1" );  
  
//Create Statement for inserting details to table
PreparedStatement ps=con.prepareStatement("insert into USERS(name,password) values(?,?)");  
  
ps.setString(1,name);  
ps.setString(2,password);  
          
int i=ps.executeUpdate();  
if(i>0)  
out.print("You are successfully registered.... Please login to continue");  
request.getRequestDispatcher("index.html").include(request, response);
}catch (Exception e2) {System.out.println(e2);}  
          
out.close();  
}  
}  