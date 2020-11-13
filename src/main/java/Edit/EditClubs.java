package Edit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "EditClubs")
public class EditClubs extends HttpServlet {
    Connection connection;
    PreparedStatement statement;
    ResultSet resultSet;
    int numberOfColumns;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();


        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/final","root","");

            String clubId = request.getParameter("clubId");
            String clubName = request.getParameter("clubName");
            String clubDescription = request.getParameter("clubDescription");



            statement = connection.prepareStatement("update Clubs set name = ?, description = ? where id = ?");
            statement.setString(1,clubName);
            statement.setString(2,clubDescription);
            statement.setString(3,clubId);



            numberOfColumns = statement.executeUpdate();
            out.println("<font color='green'> The club has been edited! </font>");
            out.println("<h4>Editing time: " + new Date(session.getCreationTime()) + "</h4>");





        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditClubs.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex){
            out.println("<font color='red'> Something is wrong! </font>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
