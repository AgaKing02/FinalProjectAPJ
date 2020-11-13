package services.interfaces;

import models.Group;
import DTOS.GroupStudent;
import models.User;
import repositories.interfaces.indirect.EntityService;

import java.util.List;

public interface GroupService extends EntityService<Group> {
    List<Group> getAllGroups();

    List<Group> getGroupsByYear(int year);

    Group getGroupById(long id);

    Group getGroupByName(String name);

    List<User> getStudentsByGroup(Group group);

    Group getGroupByStudent(User student);

    void removeUserFromGroup(GroupStudent groupStudent);

    void addUserToGroup(GroupStudent groupStudent);
}
