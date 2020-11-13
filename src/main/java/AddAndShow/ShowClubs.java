package AddAndShow;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ShowClubs")
public class ShowClubs extends HttpServlet {
    Connection connection;
    PreparedStatement statement;
    ResultSet resultSet;
    int numberOfColumns;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String clubId = request.getParameter("clubId");
        String clubDescription = request.getParameter("clubDescription");
        String clubName = request.getParameter("clubName");


        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/final", "root", "");
            String sql;
            sql = "select * from Clubs";
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);


            out.println("<table cellspacing='0' width='350px' border='1'>");
            out.println("<tr>");
            out.println("<td>Club ID: </td>");
            out.println("<td>Club name: </td>");
            out.println("<td>Description: </td>");
            out.println("<td>Edit</td>");
            out.println("<td>Delete</td>");
            out.println("</tr>");

            while (resultSet.next()) {
                out.println("<tr>");
                out.println("<td>" + resultSet.getString("id") + "</td>");
                out.println("<td>" + resultSet.getString("name") + "</td>");
                out.println("<td>" + resultSet.getString("description") + "</td>");

                out.println("<td>" + "<a href='ReturnClubs?clubId=" + resultSet.getString("id") + "'> Edit </a>" + "</td>");
                out.println("<td>" + "<a href='DeleteClubs?clubId=" + resultSet.getString("id") + "'> Delete </a>" + "</td>");

                out.println("</tr>");
            }

            out.println("</table>");


        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ShowClubs.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex){
            out.println("<center><h2><font color='red'> Something is wrong! </font></h2></center>");
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}