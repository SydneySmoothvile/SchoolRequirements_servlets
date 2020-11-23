package servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class school extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter pw=response.getWriter();
        response.setContentType("text/html");        
        String tb=request.getParameter("table");    

        try
        {
             Class.forName("com.mysql.jdbc.Driver");
             Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/school_requirement","root","");
             Statement st=con.createStatement();
             System.out.println("connection established successfully...!!");     

             ResultSet rs=st.executeQuery("Select * from "+tb);

             pw.println("<table border=1>");
                 while(rs.next())
                 {
                     pw.println("<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getString(2)+"</td>"+
                                      "<td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td></tr>");
                 }
             pw.println("</table>");
             pw.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        }
    }
