package repositories.implementations.database;

import repositories.interfaces.Repository;

import javax.ws.rs.ServerErrorException;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RepositoryImpl implements Repository {
    @Override
    public Connection getConnection() {
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return DriverManager.getConnection("jdbc:mysql://localhost/final","root","");
        } catch (SQLException ex) {
            throw new ServerErrorException("Cannot connect to DB: " + ex.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}
