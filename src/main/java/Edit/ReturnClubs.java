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

@WebServlet(name = "ReturnClubs")
public class ReturnClubs extends HttpServlet {
    Connection connection;
    PreparedStatement statement;
    ResultSet resultSet;
    int numberOfColumns;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String cId = request.getParameter("clubId");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/final","root","");

            statement = connection.prepareStatement("select * from Clubs where id = ?");
            statement.setString(1,cId);
            resultSet = statement.executeQuery();

            while(resultSet.next()) {
                out.print("<h2>Edit the clubs</h2>");
                out.print("<form action='EditClubs' method='POST'");
                out.print("<table>");
                out.print("<tr> <td>Club ID: </td> <td><input type='text' name='clubId' id='clubId' value= '" + resultSet.getString("id") + "'/> </td></tr><br><br>");
                out.print("<tr> <td>Club name: </td> <td><input type='text' name='clubName' id='clubName' value= '" + resultSet.getString("name") + "'/> </td></tr><br><br>");
                out.print("<tr> <td>Description: </td> <td><input type='text' name='clubDescription' id='clubDescription' value= '" + resultSet.getString("description") + "'/> </td></tr><br><br>");
                out.print("<tr> <td colspan='2'><input type='submit' value= 'Edit'/> </td> </tr>");
                out.print("</table>");
                out.print("</form>");


            }


        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReturnClubs.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex){
            out.println("<font color='red'> Something is wrong! </font>");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
