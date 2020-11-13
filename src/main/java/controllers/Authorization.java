package controllers;

import DTOS.LoginData;
import models.User;
import services.implementations.UserServiceImpl;
import services.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Authorization")
public class Authorization extends HttpServlet {
    private final UserService userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LoginData userLoginData = new LoginData(request.getParameter("inputEmail"), request.getParameter("inputPassword"));

        if (checkUserExistence(userLoginData)) {
            Cookie cookie = new Cookie("username", userLoginData.getUsername());
            Cookie cookie1 = new Cookie("role", userService.getUserByUsername(userLoginData.getUsername()).getRole());
            response.addCookie(cookie);
            response.addCookie(cookie1);
            response.sendRedirect(request.getContextPath() + "/profile");

        } else {
            response.sendRedirect(request.getContextPath() + "/main?auth_error=true");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public boolean checkUserExistence(LoginData userLoginData) {
        User user = userService.checkUserExistence(userLoginData);
        return user != null;
    }
}
