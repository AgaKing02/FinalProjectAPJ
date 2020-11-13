package kz.team.se1908.repositories.implementations;

import kz.team.se1908.DTOS.EventStudent;
import kz.team.se1908.repositories.interfaces.EventStudentRepository;

import java.util.List;

public class EventStudentRepositoryImpl implements EventStudentRepository {
    @Override
    public EventStudent getEventStudentBySID(long id) {
        return queryOne("SELECT * FROM eventstudent where studentid="+id+"LIMIT 1");
    }

    @Override
    public List<EventStudent> getEventStudentByEID(long id) {
        return query("SELECT * FROM eventstudent where eventid="+id);
    }

    @Override
    public void add(EventStudent entity) {

    }

    @Override
    public void update(EventStudent entity) {

    }

    @Override
    public void remove(EventStudent entity) {

    }

    @Override
    public List<EventStudent> query(String sql) {
        return null;
    }

    @Override
    public EventStudent queryOne(String sql) {
        return null;
    }
}
