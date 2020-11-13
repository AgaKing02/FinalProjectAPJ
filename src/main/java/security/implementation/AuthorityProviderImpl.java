package security.implementation;

import models.Role;
import models.User;
import security.interfaces.AuthorityProvider;
import services.implementations.UserServiceImpl;
import services.interfaces.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class AuthorityProviderImpl implements AuthorityProvider {
    private final UserService userService = new UserServiceImpl();

    @Override
    public boolean isAuthenticated(HttpServletRequest request, HttpServletResponse response) {
        boolean userExistence = false;
        String username = null;
        Cookie[] cookies = request.getCookies();
        //Displaying User name value from cookie
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("username")) {
                username = cookie.getValue();
                userExistence = true;
                break;
            }
        }
        return username != null;
    }

    @Override
    public boolean isAdministrator(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        String role = null;
        //Displaying User name value from cookie
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("username")) {
                User user = userService.getUserByUsername(cookie.getValue());
                role = user.getRole();
            }
        }
        return Objects.equals(role, Role.ADMIN.name());
    }
}
