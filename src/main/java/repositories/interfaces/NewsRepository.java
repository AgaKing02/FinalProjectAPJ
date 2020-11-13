package repositories.interfaces;

import models.User;
import repositories.interfaces.indirect.EntityRepository;
import models.News;

import java.util.List;

public interface NewsRepository extends EntityRepository<News> {

    List<News> getAllNews();

    List<News> getNewsByUser(User user);

    News getNewsById(int id);

    User getNewsAuthor(News news);

    void removeNewsByUser(User user);

}
