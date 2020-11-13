package repositories.interfaces;

import models.User;
import repositories.interfaces.indirect.EntityRepository;
import DTOS.EventStudent;
import models.Event;

import java.util.List;

public interface EventRepository extends EntityRepository<Event> {

    List<Event> getAllEvents();

    Event getEventByEvent(String event);

    Event getEventById(long id);

    List<User> getStudentsByEvent(Event event);

    void removeUserFromEvent(EventStudent eventStudent);

    void addUserToEvent(EventStudent eventStudent);
}
