package models;

import java.util.ArrayList;
import java.util.List;

public class Event {
    private long id;
    public String event;
    public String description;
    public List<User> listeners;

    public Event(String event, String description, List<User> listeners) {
        this.event = event;
        this.description = description;
        this.listeners = listeners;
    }

    public Event(long id, String event, String description, List<User> listeners) {
        this.id = id;
        this.event = event;
        this.description = description;
        this.listeners = listeners;
    }

    public Event(long id, String event, String description) {
        this.id = id;
        this.event = event;
        this.description = description;
        this.listeners=new ArrayList<>();
    }

    public long getId() {
        return id;
    }


    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getListeners() {
        return listeners;
    }

    public void setListeners(List<User> listeners) {
        this.listeners = listeners;
    }
    public void addListener(User user){
        if(user.getRole().equals(Role.STUDENT.name())){
            this.listeners.add(user);
        }else{
            throw new IllegalArgumentException("The students only can be added to " +this.getClass().getSimpleName());
        }
    }
    public void removeStudent(User user){
        this.listeners.removeIf(e->e.getUsername().equals(user.getUsername()));
    }
}
