package services.implementations;


import models.User;
import repositories.interfaces.UserRepository;
import services.interfaces.UserService;
import DTOS.LoginData;
import repositories.implementations.UserRepositoryImpl;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepo = new UserRepositoryImpl();

    public User getUserByID(long id) {
        return userRepo.getUserById(id);
    }

    public User getUserByUsername(String username) {
        return userRepo.getUserByUsername(username);
    }

    public void addUser(User user) {
        userRepo.add(user);
    }

    @Override
    public void updateUser(User user) {
        userRepo.update(user);
    }

    @Override
    public User checkUserExistence(LoginData userLoginData) {
        return userRepo.getUserByLoginData(userLoginData);
    }
}

