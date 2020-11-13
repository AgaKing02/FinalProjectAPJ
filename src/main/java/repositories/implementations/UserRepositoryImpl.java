package repositories.implementations;

import models.User;
import repositories.implementations.database.RepositoryImpl;
import repositories.interfaces.Repository;
import repositories.interfaces.UserRepository;
import DTOS.LoginData;

import javax.ws.rs.BadRequestException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private final Repository repository = new RepositoryImpl();

    @Override
    public List<User> getAllUsers() {
        return query("SELECT * FROM users");
    }

    @Override
    public User getUserById(long id) {
        return queryOne("SELECT * FROM users WHERE id=" + id + "LIMIT 1");
    }

    @Override
    public User getUserByUsername(String username) {
        return queryOne("SELECT * FROM users WHERE username='" + username + "' LIMIT 1");
    }

    @Override
    public User getUserByLoginData(LoginData loginData) {
        return queryOne("SELECT * FROM users WHERE username='" + loginData.getUsername() + "' AND password='" + loginData.getPassword() + "' LIMIT 1");
    }

    @Override
    public void add(User entity) {
        String sql = "INSERT INTO users(username,name,surname,password,role) values(?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = repository.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, entity.getUsername());
            preparedStatement.setString(2, entity.getName());
            preparedStatement.setString(3, entity.getSurname());
            preparedStatement.setString(4, entity.getPassword());
            preparedStatement.setString(5, entity.getRole());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    @Override
    public void update(User entity) {
        String sql = "UPDATE users SET username=?,name=?,surname=?,password=?,role=? where id=?";
        try {
            PreparedStatement preparedStatement = repository.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, entity.getUsername());
            preparedStatement.setString(2, entity.getName());
            preparedStatement.setString(3, entity.getSurname());
            preparedStatement.setString(4, entity.getPassword());
            preparedStatement.setString(5, entity.getRole());
            preparedStatement.setLong(6, entity.getId());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void remove(User entity) {
        String sql = "DELETE from users where id=?";
        try {
            PreparedStatement preparedStatement = repository.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, entity.getId());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    //        public String username;
//        public String name;
//        public String surname;
//        public String password;
//        private String role;
    @Override
    public List<User> query(String sql) {
        try {
            Statement stmt = repository.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            LinkedList<User> users = new LinkedList<>();
            while (rs.next()) {
                User user = new User(
                        rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("name"),
                        rs.getString("surname"),
//                        rs.getString("password"),
                        rs.getString("role")
                );
                users.add(user);
            }
            return users;
        } catch (SQLException ex) {
            throw new BadRequestException("Cannot run SQL statement: " + ex.getSQLState());
        }
    }

    @Override
    public User queryOne(String sql) {
        try {
            Statement stmt = repository.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return new User(
                        rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("name"),
                        rs.getString("surname"),
//                        rs.getString("password"),
                        rs.getString("role")
                );
            }
        } catch (SQLException ex) {
            throw new BadRequestException("Cannot run SQL statement: " + ex.getMessage());
        }
        return null;
    }
}
