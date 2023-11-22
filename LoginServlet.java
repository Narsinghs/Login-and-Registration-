package com.project.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;  

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        
        //Retrieve username and Password
        String name=request.getParameter("name");
        String password=request.getParameter("password");
        try{  
            Class.forName("com.mysql.jdbc.Driver");  
            //Create Connection
            Connection con=DriverManager.getConnection(  "jdbc:mysql://localhost:3306/register", "root","Narsingh_@$1" );  
              
            //Create Statement for database query
            PreparedStatement ps = con.prepareStatement("select * from USERS where name=? and password=?");
            ps.setString(1, name);
            ps.setString(2, password);
            //execute query
            ResultSet rs =ps.executeQuery();
            //check if result exists
            if(rs.next()) {
                //if exists, then create session attribute and redirect user to authenticated page
                HttpSession session=request.getSession();
                session.setAttribute("logindetail", name+password);
                response.sendRedirect("home.html");
            }
            else{
                //if not exists, prompt user.
                out.print("<h4>Sorry, username or password is incorrect!</h4>");
                request.getRequestDispatcher("index.html").include(request, response);
            }
            }catch (Exception e2) {System.out.println(e2);}
        
        out.close();
    }
}