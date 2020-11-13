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

@WebServlet(name = "ShowNews")
public class ShowNews extends HttpServlet {
    Connection connection;
    PreparedStatement statement;
    ResultSet resultSet;
    int numberOfColumns;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String newsTitle = request.getParameter("newsTitle");
        String newsDescription = request.getParameter("newsDescription");
        String newsPublisher = request.getParameter("newsPublisher");
        String newsId = request.getParameter("newsId");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/final", "root", "");
            String sql;
            sql = "select * from News";
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);


            out.println("<table cellspacing='0' width='350px' border='1'>");
            out.println("<tr>");
            out.println("<td>News ID: </td>");
            out.println("<td>News title: </td>");
            out.println("<td>Description: </td>");
            out.println("<td>Publisher: </td>");
            out.println("<td>Edit</td>");
            out.println("<td>Delete</td>");
            out.println("</tr>");

            while (resultSet.next()) {
                out.println("<tr>");
                out.println("<td>" + resultSet.getString("id") + "</td>");
                out.println("<td>" + resultSet.getString("title") + "</td>");
                out.println("<td>" + resultSet.getString("description") + "</td>");
                out.println("<td>" + resultSet.getString("publisher") + "</td>");


                out.println("<td>" + "<a href='ReturnNews?newsId=" + resultSet.getString("id") + "'> Edit </a>" + "</td>");
                out.println("<td>" + "<a href='DeleteNews?newsId=" + resultSet.getString("id") + "'> Delete </a>" + "</td>");

                out.println("</tr>");
            }

            out.println("</table>");


        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ShowNews.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex){
            out.println("<center><h2><font color='red'> Something is wrong! </font></h2></center>");
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
