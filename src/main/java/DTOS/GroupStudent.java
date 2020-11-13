package DTOS;

public class GroupStudent {
    private long groupid;
    private long studentid;

    public GroupStudent(long groupid, long studentid) {
        this.groupid = groupid;
        this.studentid = studentid;
    }

    public long getGroupid() {
        return groupid;
    }

    public void setGroupid(long groupid) {
        this.groupid = groupid;
    }

    public long getStudentid() {
        return studentid;
    }

    public void setStudentid(long studentid) {
        this.studentid = studentid;
    }
}
