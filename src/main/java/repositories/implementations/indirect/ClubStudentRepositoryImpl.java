package repositories.implementations.indirect;

import repositories.implementations.database.RepositoryImpl;
import repositories.interfaces.Repository;
import DTOS.ClubStudent;
import repositories.interfaces.indirect.ClubStudentRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClubStudentRepositoryImpl implements ClubStudentRepository {
    private final Repository repository = new RepositoryImpl();

    @Override
    public ClubStudent getClubStudentBySID(long id) {
        return queryOne("SELECT * FROM clubstudent where studentid=" + id + "LIMIT 1");
    }

    @Override
    public List<ClubStudent> getClubStudentByCID(long id) {
        return query("SELECT * FROM clubstudent where clubid=" + id);
    }

    @Override
    public void add(ClubStudent entity) {
        String sql = "INSERT INTO clubstudent(clubid,studentid) values(?,?)";
        try {
            PreparedStatement preparedStatement = repository.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, entity.getClubid());
            preparedStatement.setLong(2, entity.getStudentid());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void update(ClubStudent entity) {

    }

    @Override
    public void remove(ClubStudent entity) {
        String sql = "DELETE FROM clubstudent where clubid=? and studentid=?";
        try {
            PreparedStatement preparedStatement = repository.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, entity.getClubid());
            preparedStatement.setLong(2, entity.getStudentid());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<ClubStudent> query(String sql) {
        Statement stmt = null;
        List<ClubStudent> clubStudents = new ArrayList<>();
        try {
            stmt = repository.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ClubStudent clubStudent = new ClubStudent(
                        rs.getLong("clubid"),
                        rs.getLong("studentid")
                );
                clubStudents.add(clubStudent);
            }
            return clubStudents;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }



    @Override
    public ClubStudent queryOne(String sql) {
        Statement stmt = null;
        try {
            stmt = repository.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return new ClubStudent(
                        rs.getLong("clubid"),
                        rs.getLong("studentid")
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

}
