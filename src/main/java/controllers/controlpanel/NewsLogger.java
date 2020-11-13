package controllers.controlpanel;

import services.implementations.NewsServiceImpl;
import services.interfaces.NewsService;
import security.implementation.AuthorityProviderImpl;
import security.interfaces.AuthorityProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "NewsLogger")
public class NewsLogger extends HttpServlet {
    private final NewsService newsService = new NewsServiceImpl();
    private final AuthorityProvider authorityProvider = new AuthorityProviderImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        authorityProvider.isAuthenticated(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        authorityProvider.isAuthenticated(request, response);
    }
}
