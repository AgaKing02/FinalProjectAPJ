package repositories.implementations;

import models.News;
import models.User;
import repositories.implementations.database.RepositoryImpl;
import repositories.interfaces.NewsRepository;
import repositories.interfaces.Repository;
import repositories.interfaces.UserRepository;

import javax.ws.rs.BadRequestException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NewsRepositoryImpl implements NewsRepository {
    private final Repository repository = new RepositoryImpl();
    private final UserRepository userRepository = new UserRepositoryImpl();

    @Override
    public List<News> getAllNews() {
        return query("SELECT * FROM news");

    }

    @Override
    public List<News> getNewsByUser(User user) {
        return query("SELECT * FROM news where publisher=" + user.getId());
    }

    @Override
    public News getNewsById(int id) {
        return queryOne("SELECT * FROM news WHERE id=" + id + "LIMIT 1");
    }

    @Override
    public User getNewsAuthor(News news) {
        return userRepository.getUserById(news.publisher.getId());
    }

    @Override
    public void removeNewsByUser(User user) {
        getNewsByUser(user).forEach(this::remove);
    }

    @Override
    public void add(News entity) {
        String sql = "INSERT into news(title,description,publisher) values(?,?,?)";
        try {
            PreparedStatement preparedStatement = repository.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setString(2, entity.getDescription());
            preparedStatement.setLong(3, entity.getPublisher().getId());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    //    public String title;
//    public String description;
//    public User publisher;
    @Override
    public void update(News entity) {
        String sql = "UPDATE news SET title=?,description=?,publisher=? where id=?";
        try {
            PreparedStatement preparedStatement = repository.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setString(2, entity.getDescription());
            preparedStatement.setLong(3, entity.getPublisher().getId());
            preparedStatement.setLong(4, entity.getId());

            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void remove(News entity) {
        String sql = "REMOVE FROM news WHERE id=?";
        try {
            PreparedStatement preparedStatement = repository.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, entity.getId());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<News> query(String sql) {
        try {
            Statement stmt = repository.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            List<News> news = new ArrayList<>();
            while (rs.next()) {
                News news1 = new News(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        userRepository.getUserById(rs.getLong("publisher"))
                );
                news.add(news1);
            }
            return news;
        } catch (SQLException ex) {
            throw new BadRequestException("Cannot run SQL statement: " + ex.getSQLState());
        }
    }

    @Override
    public News queryOne(String sql) {
        Statement stmt = null;
        try {
            stmt = repository.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return new News(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        userRepository.getUserById(rs.getLong("publisher"))
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
