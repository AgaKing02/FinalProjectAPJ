package repositories.implementations;

import models.Club;
import models.User;
import repositories.implementations.database.RepositoryImpl;
import repositories.implementations.indirect.ClubStudentRepositoryImpl;
import repositories.interfaces.ClubRepository;
import repositories.interfaces.Repository;
import repositories.interfaces.UserRepository;
import DTOS.ClubStudent;
import repositories.interfaces.indirect.ClubStudentRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClubRepositoryImpl implements ClubRepository {
    private final ClubStudentRepository clubStudentRepository = new ClubStudentRepositoryImpl();
    private final UserRepository userRepository = new UserRepositoryImpl();
    private final Repository repository = new RepositoryImpl();

    @Override
    public List<Club> getAllClubs() {
        return query("SELECT * FROM clubs");

    }

    @Override
    public Club getClubByName(String name) {
        return queryOne("SELECT * FROM clubs WHERE name='" + name + "' LIMIT 1");
    }

    @Override
    public Club getClubById(long id) {
        return queryOne("SELECT * FROM clubs WHERE id=" + id + "LIMIT 1");
    }

    @Override
    public List<User> getStudentsByClub(Club club) {
        List<User> userList = new ArrayList<>();
        List<ClubStudent> clubStudents = clubStudentRepository.getClubStudentByCID(club.getId());
        clubStudents.forEach(e -> userList.add(userRepository.getUserById(e.getStudentid())));
        return userList;
    }


    @Override
    public void removeUserFromClub(ClubStudent clubStudent) {
        clubStudentRepository.remove(clubStudent);
    }

    @Override
    public void addUserToClub(ClubStudent clubStudent) {
        clubStudentRepository.add(clubStudent);
    }

    @Override
    public void add(Club entity) {
//        private long id;
//        public String event;
//        public String description;
//        public List<User> listeners;
        String sql = "INSERT INTO clubs(name,description) values(?,?)";
        try {
            PreparedStatement preparedStatement = repository.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getDescription());
            preparedStatement.execute();
            if (entity.getVolunteers() != null) {
                Club club=getClubByName(entity.getName());
                entity.getVolunteers().forEach(e -> clubStudentRepository.add(new ClubStudent(club.getId(), e.getId())));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void update(Club entity) {
        String sql = "UPDATE clubs SET name=?,description=? where id=?";
        try {
            PreparedStatement preparedStatement = repository.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getDescription());
            preparedStatement.setLong(3, entity.getId());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void remove(Club entity) {
        String sql = "DELETE FROM clubs where id=?";
        try {
            PreparedStatement preparedStatement = repository.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, entity.getId());
            clubStudentRepository.getClubStudentByCID(entity.getId()).forEach(clubStudentRepository::remove);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public List<Club> query(String sql) {
        try {
            Statement stmt = repository.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            List<Club> clubs = new ArrayList<>();
            while (rs.next()) {
                Club club = new Club(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("description")
                );
                clubStudentRepository
                        .getClubStudentByCID(club.getId())
                        .forEach(clubStudent -> club.addVolunteer(userRepository
                                .getUserById(clubStudent.getStudentid())));
                clubs.add(club);

            }
            return clubs;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Club queryOne(String sql) {
        try {
            Statement stmt = repository.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                Club club = new Club(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("description")
                );
                clubStudentRepository
                        .getClubStudentByCID(club.getId())
                        .forEach(clubStudent -> club.addVolunteer(userRepository
                                .getUserById(clubStudent.getStudentid())));
                return club;
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
