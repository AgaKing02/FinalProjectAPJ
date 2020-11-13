package models;

public class News {
    private long id;
    public String title;
    public String description;
    public User publisher;

    public News(String title, String description, User publisher) {
        this.title = title;
        this.description = description;
        this.publisher = publisher;
    }

    public News(long id, String title, String description, User publisher) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.publisher = publisher;
    }

    public long getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getPublisher() {
        return publisher;
    }

    public void setPublisher(User publisher) {
        this.publisher = publisher;
    }
}
