package repositories.interfaces;

import java.sql.Connection;

public interface Repository {
    Connection getConnection();
}
