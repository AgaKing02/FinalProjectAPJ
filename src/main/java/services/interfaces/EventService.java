package services.interfaces;

import DTOS.EventStudent;
import models.Event;
import models.User;
import repositories.interfaces.indirect.EntityService;

import java.util.List;

public interface EventService extends EntityService<Event> {
    List<Event> getAllEvents();

    Event getEventByEvent(String event);

    Event getEventById(long id);

    List<User> getStudentsByEvent(Event event);

    void removeUserFromEvent(EventStudent eventStudent);

    void addUserToEvent(EventStudent eventStudent);
}
