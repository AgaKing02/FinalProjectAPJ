package controllers;

import models.Role;
import models.User;
import security.implementation.AuthorityProviderImpl;
import security.interfaces.AuthorityProvider;
import services.implementations.UserServiceImpl;
import services.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Authentication")
public class Authentication extends HttpServlet {
    private final UserService userService=new UserServiceImpl();
    private final AuthorityProvider authorityProvider=new AuthorityProviderImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = new User();

        user.setName(request.getParameter("firstName"));
        user.setUsername(request.getParameter("username"));
        user.setSurname(request.getParameter("lastName"));
        user.setPassword(request.getParameter("txtPassword"));
        user.setRole(Role.STUDENT.name());

        if(userService.getUserByUsername(user.getUsername())!=null){
            response.sendRedirect(request.getContextPath()+"/signup?auth_error=duplicate");
        }else{
            userService.addUser(user);
            response.sendRedirect(request.getContextPath()+"/main");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (authorityProvider.isAuthenticated(request, response)) {
            response.sendRedirect(request.getContextPath() + "/profile");
        } else {
            if (request.getParameter("auth_error") != null) {
                request.setAttribute("error", "Username is already exists");
            }
            request.getRequestDispatcher("/signup.jsp").forward(request, response);
        }
    }
}
