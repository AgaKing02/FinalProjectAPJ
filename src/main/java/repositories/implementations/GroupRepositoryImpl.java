package repositories.implementations;

import DTOS.GroupStudent;
import models.Group;
import models.User;
import repositories.implementations.database.RepositoryImpl;
import repositories.implementations.indirect.GroupStudentRepositoryImpl;
import repositories.interfaces.GroupRepository;
import repositories.interfaces.Repository;
import repositories.interfaces.UserRepository;
import repositories.interfaces.indirect.GroupStudentRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GroupRepositoryImpl implements GroupRepository {
    private final GroupStudentRepository groupStudentRepository = new GroupStudentRepositoryImpl();
    private final UserRepository userRepository = new UserRepositoryImpl();
    private final Repository repository = new RepositoryImpl();

    @Override
    public List<Group> getAllGroups() {
        return query("SELECT * FROM groups");
    }

    @Override
    public List<Group> getGroupsByYear(int year) {
        return query("SELECT * FROM groups WHERE year=" + year);
    }

    @Override
    public Group getGroupById(long id) {
        return queryOne("SELECT * FROM groups WHERE id=" + id + "LIMIT 1");
    }

    @Override
    public Group getGroupByName(String name) {
        return queryOne("SELECT * FROM groups WHERE name='" + name + "' LIMIT 1");
    }

    @Override
    public List<User> getStudentsByGroup(Group group) {
        List<User> userList = new ArrayList<>();
        List<GroupStudent> groupStudents = groupStudentRepository.getGroupStudentByGID(group.getId());
        groupStudents.forEach(e -> userList.add(userRepository.getUserById(e.getStudentid())));
        return userList;
    }

    @Override
    public Group getGroupByStudent(User student) {
        GroupStudent groupStudent = groupStudentRepository.getGroupStudentBySID(student.getId());
        return getGroupById(groupStudent.getGroupid());
    }


    @Override
    public void removeUserFromGroup(GroupStudent groupStudent) {
        groupStudentRepository.remove(groupStudent);
    }

    @Override
    public void addUserToGroup(GroupStudent groupStudent) {
        groupStudentRepository.add(groupStudent);
    }

    @Override
    public void add(Group entity) {
//        private long id;
//        public String name;
//        public int year;
//        public List<User> students;
        String sql = "INSERT INTO groups(name ,year) values(?,?)";
        try {
            PreparedStatement preparedStatement = repository.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getYear());
            preparedStatement.execute();
            if (entity.getStudents() != null) {
                Group group = getGroupByName(entity.getName());
                entity.getStudents().forEach(e -> groupStudentRepository.add(new GroupStudent(group.getId(), e.getId())));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void update(Group entity) {
        String sql = "UPDATE groups SET name=?,year=? where id=?";
        try {
            PreparedStatement preparedStatement = repository.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getYear());
            preparedStatement.setLong(3, entity.getId());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void remove(Group entity) {
        String sql = "DELETE FROM groups where id=?";
        try {
            PreparedStatement preparedStatement = repository.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, entity.getId());
            groupStudentRepository.getGroupStudentByGID(entity.getId()).forEach(groupStudentRepository::remove);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public List<Group> query(String sql) {
        try {
            Statement stmt = repository.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            List<Group> groups = new ArrayList<>();
            while (rs.next()) {
                Group group = new Group(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getInt("year")
                );

                groupStudentRepository
                        .getGroupStudentByGID(group.getId())
                        .forEach(groupStudent -> group.addStudent(userRepository
                                .getUserById(groupStudent.getStudentid())));
                groups.add(group);

            }
            return groups;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Group queryOne(String sql) {
        try {
            Statement stmt = repository.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                Group group = new Group(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getInt("year")
                );
                groupStudentRepository
                        .getGroupStudentByGID(group.getId())
                        .forEach(groupStudent -> group.addStudent(userRepository
                                .getUserById(groupStudent.getStudentid())));

                return group;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
