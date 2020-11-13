package models;

import java.util.ArrayList;
import java.util.List;

public class Club {
    private long id;
    public String name;
    public String description;
    public List<User> volunteers;

    public Club(long id, String name, String description, List<User> volunteers) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.volunteers = volunteers;
    }

    public Club(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.volunteers=new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getVolunteers() {
        return volunteers;
    }

    public void setVolunteers(List<User> volunteers) {
        this.volunteers = volunteers;
    }

    public void addVolunteer(User user){
        if(user.getRole().equals(Role.STUDENT.name())){
            this.volunteers. add(user);
        }else{
            throw new IllegalArgumentException("The students only can be added to " +this.getClass().getSimpleName());
        }
    }
    public void removeVolunteer(User user){
        this.volunteers.removeIf(e->user.getUsername().equals(e.getUsername()));
    }

}
