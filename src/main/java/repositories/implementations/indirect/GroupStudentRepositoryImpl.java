package repositories.implementations.indirect;

import DTOS.GroupStudent;
import repositories.implementations.database.RepositoryImpl;
import repositories.interfaces.Repository;
import repositories.interfaces.indirect.GroupStudentRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GroupStudentRepositoryImpl implements GroupStudentRepository {
    private final Repository repository = new RepositoryImpl();

    @Override
    public GroupStudent getGroupStudentBySID(long id) {
        return queryOne("SELECT * FROM groupstudent where studentid=" + id + "LIMIT 1");

    }

    @Override
    public List<GroupStudent> getGroupStudentByGID(long id) {
        return query("SELECT * FROM groupstudent where groupid=" + id);
    }

    @Override
    public void add(GroupStudent entity) {
        String sql = "INSERT INTO groupstudent(groupid,studentid) values(?,?)";
        try {
            PreparedStatement preparedStatement = repository.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, entity.getGroupid());
            preparedStatement.setLong(2, entity.getStudentid());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(GroupStudent entity) {

    }

    @Override
    public void remove(GroupStudent entity) {
        String sql = "DELETE FROM groupstudent where groupid=? and studentid=?";
        try {
            PreparedStatement preparedStatement = repository.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, entity.getGroupid());
            preparedStatement.setLong(2, entity.getStudentid());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<GroupStudent> query(String sql) {
        Statement stmt = null;
        List<GroupStudent> groupStudents = new ArrayList<>();
        try {
            stmt = repository.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                GroupStudent groupStudent = new GroupStudent(
                        rs.getLong("groupid"),
                        rs.getLong("studentid")
                );
                groupStudents.add(groupStudent);
            }
            return groupStudents;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public GroupStudent queryOne(String sql) {
        Statement stmt = null;
        try {
            stmt = repository.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return new GroupStudent(
                        rs.getLong("groupid"),
                        rs.getLong("studentid")
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
