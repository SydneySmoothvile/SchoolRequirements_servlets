/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sydney Abuya
 */
public class insert extends HttpServlet {
        private static final long serialVersionUID = 1L; 
 
        @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
            /* TODO output your page here. You may use following sample code. */
           String name = request.getParameter("sch_name");  
          String location = request.getParameter("sch_location");  
          String email = request.getParameter("sch_email");
          String requirement = request.getParameter("requirement"); 
          
           try{    //load the driver
               Class.forName("com.mysql.jdbc.Driver");  
               //create connection object
               Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/school_requirement","root","");  
               // create the prepared statement object
               PreparedStatement ps=con.prepareStatement("insert into school(school_name,location,email,requirements) values(?,?,?,?)");  
  
               ps.setString(1,name);  
               ps.setString(2,location);  
               ps.setString(3,email); 
               ps.setString(4,requirement);  
                
               //ps.executeUpdate();  
               
               
//             Statement st=con.createStatement();
//             st.executeUpdate("insert into school(sch_name,sch_location,email,requirement) values('"+name+"','"+location+"','"+email+"','"+requirement+"')");
//            out.println("<html><body><h1>Successfully Inserted"
//                        + "</h1></body></html>");   
//            out.close();
            int i=ps.executeUpdate();  
            if(i>0)  
            out.print("You are successfully registered...");  
            else
                out.println("<html><body><h1>Failed"
                      + "</h1></body></html>");
          }
          catch (Exception ex)
          {
               ex.printStackTrace();
          }  
          //out.close();  
        }
    }

