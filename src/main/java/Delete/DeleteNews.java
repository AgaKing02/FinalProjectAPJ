package Delete;

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

@WebServlet(name = "DeleteNews")
public class DeleteNews extends HttpServlet {
    Connection connection;
    PreparedStatement statement;
    ResultSet resultSet;
    int numberOfColumns;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String newsId = request.getParameter("newsId");
        HttpSession session = request.getSession();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/final","root","");


            statement = connection.prepareStatement("delete from News where id = ?");
            statement.setString(1,newsId);
            numberOfColumns = statement.executeUpdate();
            out.println("<font color='green'> The news has been deleted! </font>");
            out.println("<h4>Deleting time: " + new Date(session.getCreationTime()) + "</h4>");


        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DeleteNews.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex){
            out.println("<font color='red'> Something is wrong! </font>");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
