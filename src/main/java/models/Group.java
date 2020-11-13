package models;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private long id;
    public String name;
    public int year;
    public List<User> students;


    public Group(String name, int year, List<User> students) {
        this.name = name;
        this.year = year;
        this.students = students;
    }

    public Group(long id, String name, int year, List<User> students) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.students = students;
    }

    public Group(long id, String name, int year) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.students=new ArrayList<>();
    }

    public Group() {
        this.students = new ArrayList<>();
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<User> getStudents() {
        return students;
    }

    public void setStudents(List<User> students) {
        this.students = students;
    }

    public void addStudent(User user) throws IllegalArgumentException {
        if (user.getRole().equals(Role.STUDENT.name())) {
            this.students.add(user);
        } else {
            throw new IllegalArgumentException("The students only can be added to " + this.getClass().getSimpleName());
        }
    }

    public void removeStudent(User user) {
        this.students.removeIf(e -> e.getUsername().equals(user.getUsername()));
    }

}
