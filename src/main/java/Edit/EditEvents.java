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

@WebServlet(name = "EditEvents")
public class EditEvents extends HttpServlet {
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

            String eventId = request.getParameter("eventId");
            String eventDescription = request.getParameter("eventDescription");
            String eventName = request.getParameter("eventName");


            statement = connection.prepareStatement("update Events set event = ?, description = ? where id = ?");
            statement.setString(1,eventName);
            statement.setString(2,eventDescription);
            statement.setString(3,eventId);



            numberOfColumns = statement.executeUpdate();
            out.println("<font color='green'> The event has been edited! </font>");
            out.println("<h4>Editing time: " + new Date(session.getCreationTime()) + "</h4>");





        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditEvents.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex){
            out.println("<font color='red'> Something is wrong! </font>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
