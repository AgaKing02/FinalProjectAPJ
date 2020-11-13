package services.interfaces;

import repositories.interfaces.indirect.EntityService;
import models.News;
import models.User;

import java.util.List;

public interface NewsService extends EntityService<News> {
    List<News> getAllNews();

    List<News> getNewsByUser(User user);

    News getNewsById(int id);

    User getNewsAuthor(News news);

    void removeNewsByUser(User user);
}
