/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sydney Abuya
 */
public class MyServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MyServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MyServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            
            // connecting to database
            Connection con = null;  
            Statement stmt = null;
            ResultSet rs = null;
            
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con =DriverManager.getConnection ("jdbc:mysql://localhost:3307/school_requirement","root","");
                stmt = con.createStatement();
                rs = stmt.executeQuery("SELECT * FROM school_requirement");
                
                // displaying records
                while(rs.next()){
                    out.println(rs.getString(1));
                    out.println("\t\t\t");
                    out.println(rs.getString(2));
                    out.println("<br>");
                    }
                } catch (SQLException e) {
                        throw new ServletException("Servlet Could not display records.", e);
                } catch (ClassNotFoundException e) {
                        throw new ServletException("JDBC Driver not found.", e);
                } finally {
                    try {
                        if(rs != null) {
                        rs.close();
                        rs = null;
                    }
                    if(stmt != null) {
                        stmt.close();
                        stmt = null;
                    }
                    if(con != null) {
                        con.close();
                        con = null;
                    }
                    } catch (SQLException e) {}
                }
                    out.close();
            }
        }
    }


