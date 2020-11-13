package repositories.interfaces;

import models.Club;
import models.User;
import repositories.interfaces.indirect.EntityRepository;
import DTOS.ClubStudent;

import java.util.List;

public interface ClubRepository extends EntityRepository<Club> {
    List<Club> getAllClubs();

    Club getClubByName(String event);

    Club getClubById(long id);

    List<User> getStudentsByClub(Club club);

    void removeUserFromClub(ClubStudent clubStudent);

    void addUserToClub(ClubStudent clubStudent);
}
