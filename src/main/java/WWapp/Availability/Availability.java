package WWapp.Availability;

import WWapp.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "availabilities")
public class Availability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")  //relacja wiele do jednego
    private User user;

    private String start;
    private String end;
    private boolean monday;
    private boolean tuesday;
    private boolean wednesday;
    private boolean thursday;
    private boolean friday;
    private boolean saturday;
    private boolean sunday;
    private String signature;


    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public User getUser() {
        return user;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getStart() {
        return start;
    }
    public void setStart(String start) {
        this.start = start;
    }
    public String getEnd() {
        return end;
    }
    public void setEnd(String end) {
        this.end = end;
    }
    public boolean isMonday() {
        return monday;
    }
    public void setMonday(boolean monday) {
        this.monday = monday;
    }
    public boolean isTuesday() {
        return tuesday;
    }
    public void setTuesday(boolean tuesday) {
        this.tuesday = tuesday;
    }
    public boolean isWednesday() {
        return wednesday;
    }
    public void setWednesday(boolean wednesday) {
        this.wednesday = wednesday;
    }
    public boolean isThursday() {
        return thursday;
    }
    public void setThursday(boolean thursday) {
        this.thursday = thursday;
    }
    public boolean isFriday() {
        return friday;
    }
    public void setFriday(boolean friday) {
        this.friday = friday;
    }
    public boolean isSaturday() {
        return saturday;
    }
    public void setSaturday(boolean saturday) {
        this.saturday = saturday;
    }
    public boolean isSunday() {
        return sunday;
    }
    public void setSunday(boolean sunday) {
        this.sunday = sunday;
    }
    @Override
    public String toString() {
        return "Availability{" +
                " id=" + id +
                ", start='" + start + '\'' +
                ", end=" + end +
                ", monday='" + monday + '\'' +
                ", tuesday=" + tuesday +
                ", wednesday=" + wednesday +
                ", thursday=" + thursday +
                ", friday=" + friday +
                ", saturday=" + saturday +
                ", sunday=" + sunday +
                ", user =" + user +
                '}';
    }
    public void assignUser(User user) {
        this.user =user;
    }
}
