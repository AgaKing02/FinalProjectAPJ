package services.interfaces;

import DTOS.ClubStudent;
import models.Club;
import models.User;
import repositories.interfaces.indirect.EntityService;

import java.util.List;

public interface ClubService extends EntityService<Club> {
    List<Club> getAllClubs();

    Club getClubByName(String event);

    Club getClubById(long id);

    List<User> getStudentsByClub(Club club);

    void removeUserFromClub(ClubStudent clubStudent);

    void addUserToClub(ClubStudent clubStudent);
}
