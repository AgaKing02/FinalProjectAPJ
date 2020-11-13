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

@WebServlet(name = "EditNews")
public class EditNews extends HttpServlet {
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

            String newsId = request.getParameter("newsId");
            String newsTitle = request.getParameter("newsTitle");
            String newsDescription = request.getParameter("newsDescription");
            String newsPublisher = request.getParameter("newsPublisher");


            statement = connection.prepareStatement("update News set title = ?, description = ?, publisher = ? where id = ?");
            statement.setString(1,newsTitle);
            statement.setString(2,newsDescription);
            statement.setString(3,newsPublisher);
            statement.setString(4,newsId);


            numberOfColumns = statement.executeUpdate();
            out.println("<font color='green'> The news has been edited! </font>");
            out.println("<h4>Editing time: " + new Date(session.getCreationTime()) + "</h4>");





        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditNews.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex){
            out.println("<font color='red'> Something is wrong! </font>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
