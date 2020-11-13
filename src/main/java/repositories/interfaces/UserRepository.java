package repositories.interfaces;


import models.User;
import repositories.interfaces.indirect.EntityRepository;
import DTOS.LoginData;

import java.util.List;

public interface UserRepository extends EntityRepository<User> {

    List<User> getAllUsers();

    User getUserById(long id);

    User getUserByUsername(String username);

    User getUserByLoginData(LoginData loginData);


}
