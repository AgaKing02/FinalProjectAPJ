package repositories.interfaces.indirect;

import DTOS.ClubStudent;

import java.util.List;

public interface ClubStudentRepository extends EntityRepository<ClubStudent> {
    ClubStudent getClubStudentBySID(long id);

    List<ClubStudent> getClubStudentByCID(long id);
}
