package security.interfaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AuthorityProvider {
    boolean isAuthenticated(HttpServletRequest request, HttpServletResponse response);
    boolean isAdministrator(HttpServletRequest request, HttpServletResponse response);
}
