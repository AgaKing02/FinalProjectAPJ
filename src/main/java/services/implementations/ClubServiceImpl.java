package services.implementations;

import DTOS.ClubStudent;
import models.Club;
import models.User;
import repositories.implementations.ClubRepositoryImpl;
import repositories.interfaces.ClubRepository;
import services.interfaces.ClubService;

import java.util.List;

public class ClubServiceImpl implements ClubService {
    private final ClubRepository clubRepository = new ClubRepositoryImpl();

    @Override
    public List<Club> getAllClubs() {
        return clubRepository.getAllClubs();
    }

    @Override
    public Club getClubByName(String event) {
        return clubRepository.getClubByName(event);
    }

    @Override
    public Club getClubById(long id) {
        return clubRepository.getClubById(id);
    }

    @Override
    public List<User> getStudentsByClub(Club club) {
        return clubRepository.getStudentsByClub(club);
    }

    @Override
    public void removeUserFromClub(ClubStudent clubStudent) {
        clubRepository.removeUserFromClub(clubStudent);
    }

    @Override
    public void addUserToClub(ClubStudent clubStudent) {
        clubRepository.addUserToClub(clubStudent);
    }

    @Override
    public void add(Club entity) {
        clubRepository.add(entity);
    }

    @Override
    public void update(Club entity) {
        clubRepository.update(entity);
    }

    @Override
    public void remove(Club entity) {
        clubRepository.remove(entity);
    }
}
