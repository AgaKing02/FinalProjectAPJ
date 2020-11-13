package repositories.interfaces.indirect;

import DTOS.GroupStudent;

import java.util.List;

public interface GroupStudentRepository extends EntityRepository<GroupStudent> {
    GroupStudent getGroupStudentBySID(long id);

    List<GroupStudent> getGroupStudentByGID(long id);


}
