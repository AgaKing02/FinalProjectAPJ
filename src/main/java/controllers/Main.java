package controllers;

import security.implementation.AuthorityProviderImpl;
import security.interfaces.AuthorityProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Main")
public class Main extends HttpServlet {
    private final AuthorityProvider authorityProvider = new AuthorityProviderImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (authorityProvider.isAuthenticated(request, response)) {
            response.sendRedirect(request.getContextPath() + "/profile");
        } else {
            if (request.getParameter("auth_error") != null) {
                request.setAttribute("error", "Invalid Username or Password");
            }
            request.getRequestDispatcher("/main.jsp").forward(request, response);
        }
    }
}

