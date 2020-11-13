package DTOS;

public class EventStudent {
    private long eventid;
    private long studentid;

    public EventStudent(long eventid, long studentid) {
        this.eventid = eventid;
        this.studentid = studentid;
    }

    public long getEventid() {
        return eventid;
    }

    public void setEventid(long eventid) {
        this.eventid = eventid;
    }

    public long getStudentid() {
        return studentid;
    }

    public void setStudentid(long studentid) {
        this.studentid = studentid;
    }
}
