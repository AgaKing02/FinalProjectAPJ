package DTOS;

public class ClubStudent {
    private long clubid;
    private long studentid;

    public ClubStudent(long eventid, long studentid) {
        this.clubid = eventid;
        this.studentid = studentid;
    }

    public long getClubid() {
        return clubid;
    }

    public void setClubid(long clubid) {
        this.clubid = clubid;
    }

    public long getStudentid() {
        return studentid;
    }

    public void setStudentid(long studentid) {
        this.studentid = studentid;
    }
}
