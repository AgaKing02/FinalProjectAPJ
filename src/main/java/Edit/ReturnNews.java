package Edit;

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

@WebServlet(name = "ReturnNews")
public class ReturnNews extends HttpServlet {
    Connection connection;
    PreparedStatement statement;
    ResultSet resultSet;
    int numberOfColumns;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String newsId = request.getParameter("newsId");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/final","root","");

            statement = connection.prepareStatement("select * from News where id = ?");
            statement.setString(1,newsId);
            resultSet = statement.executeQuery();

            while(resultSet.next()) {
                out.print("<h2>Edit the news</h2>");
                out.print("<form action='EditNews' method='POST'");
                out.print("<table>");
                out.print("<tr> <td>News ID: </td> <td><input type='text' name='newsId' id='newsId' value= '" + resultSet.getString("id") + "'/> </td></tr><br><br>");
                out.print("<tr> <td>News title: </td> <td><input type='text' name='newsTitle' id='newsTitle' value= '" + resultSet.getString("title") + "'/> </td></tr><br><br>");
                out.print("<tr> <td>Description: </td> <td><input type='text' name='newsDescription' id='newsDescription' value= '" + resultSet.getString("description") + "'/> </td></tr><br><br>");
                out.print("<tr> <td>Publisher: </td> <td><input type='text' name='newsPublisher' id='newsPublisher' value= '" + resultSet.getString("publisher") + "'/> </td></tr><br><br>");
                out.print("<tr> <td colspan='2'><input type='submit' value= 'Edit'/> </td> </tr>");
                out.print("</table>");
                out.print("</form>");


            }


        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReturnNews.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex){
            out.println("<font color='red'> Something is wrong! </font>");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
