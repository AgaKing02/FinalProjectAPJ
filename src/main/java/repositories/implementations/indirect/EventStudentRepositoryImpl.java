package repositories.implementations.indirect;

import DTOS.EventStudent;
import repositories.implementations.database.RepositoryImpl;
import repositories.interfaces.Repository;
import repositories.interfaces.indirect.EventStudentRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EventStudentRepositoryImpl implements EventStudentRepository {
    private final Repository repository = new RepositoryImpl();

    @Override
    public EventStudent getEventStudentBySID(long id) {
        return queryOne("SELECT * FROM eventstudent where studentid=" + id + "LIMIT 1");
    }

    @Override
    public List<EventStudent> getEventStudentByEID(long id) {
        return query("SELECT * FROM eventstudent where eventid=" + id);
    }

    @Override
    public void add(EventStudent entity) {
        String sql = "INSERT INTO eventstudent(eventid,studentid) values(?,?)";
        try {
            PreparedStatement preparedStatement = repository.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, entity.getEventid());
            preparedStatement.setLong(2, entity.getStudentid());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void update(EventStudent entity) {

    }

    @Override
    public void remove(EventStudent entity) {
        String sql = "DELETE FROM eventstudent where eventid=? and studentid=?";
        try {
            PreparedStatement preparedStatement = repository.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, entity.getEventid());
            preparedStatement.setLong(2, entity.getStudentid());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<EventStudent> query(String sql) {
        Statement stmt = null;
        List<EventStudent> eventStudents = new ArrayList<>();
        try {
            stmt = repository.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                EventStudent eventStudent = new EventStudent(
                        rs.getLong("eventid"),
                        rs.getLong("studentid")
                );
                eventStudents.add(eventStudent);
            }
            return eventStudents;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }



    @Override
    public EventStudent queryOne(String sql) {
        Statement stmt = null;
        try {
            stmt = repository.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return new EventStudent(
                        rs.getLong("eventid"),
                        rs.getLong("studentid")
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

}
