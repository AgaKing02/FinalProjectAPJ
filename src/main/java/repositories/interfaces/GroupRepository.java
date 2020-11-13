package repositories.interfaces;

import DTOS.GroupStudent;
import models.Group;
import models.User;
import repositories.interfaces.indirect.EntityRepository;

import java.util.List;

public interface GroupRepository extends EntityRepository<Group> {

    List<Group> getAllGroups();

    List<Group> getGroupsByYear(int year);

    Group getGroupById(long id);

    Group getGroupByName(String name);

    List<User> getStudentsByGroup(Group group);

    Group getGroupByStudent(User student);

    void removeUserFromGroup(GroupStudent groupStudent);

    void addUserToGroup(GroupStudent groupStudent);

}
