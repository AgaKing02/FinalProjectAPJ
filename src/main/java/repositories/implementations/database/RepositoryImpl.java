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
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return DriverManager.getConnection("jdbc:postgresql://localhost:5433/week8practice", "postgres", "agahan02");
        } catch (SQLException ex) {
            throw new ServerErrorException("Cannot connect to DB: " + ex.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}
