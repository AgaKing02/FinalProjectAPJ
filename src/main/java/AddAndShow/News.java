package AddAndShow;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "/News")
public class News extends HttpServlet {
    Connection connection;
    PreparedStatement statement;
    int numberOfColumns;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/final","root","");

            String newsTitle = request.getParameter("newsTitle");
            String newsDescription = request.getParameter("newsDescription");
            String newsPublisher = request.getParameter("newsPublisher");
            String newsId = request.getParameter("newsId");

            statement = connection.prepareStatement("insert into news(id, title, description, publisher)values(?,?,?,?) ");
            statement.setString(1,newsId);
            statement.setString(2,newsTitle);
            statement.setString(3,newsDescription);
            statement.setString(4,newsPublisher);
            numberOfColumns = statement.executeUpdate();

            out.println("<center>");
            out.println("<h2><font color='green'> The news has been added! </font></h2>");
            out.println("<h4>Adding time: " + new Date(session.getCreationTime()) + "</h4>");
            out.println("</center>");


        } catch (ClassNotFoundException ex) {
            Logger.getLogger(News.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex){
            out.println("<center><h2><font color='red'> Something is wrong! </font></h2></center>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
