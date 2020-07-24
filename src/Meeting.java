import java.util.ArrayList;
import java.util.Date;

public class Meeting {
    private String name;
    private Date meetingDate;
    private ArrayList<Person> attendees;
    private Person host;

    public Meeting(String name, Date date, Person host, ArrayList<Person> attendees) {
        this.name = name;
        this.meetingDate = date;
        this.attendees = attendees; // TODO shouldn't you check if it is not empty?

        this.host = host;
        this.attendees.add(host);
        // TODO shouldn't it be also added to the list myMeeting?
    }

    public ArrayList<Person> getAttendees() {
        return attendees;
    }


    public Date getMeetingDate() {
        return meetingDate;
    }


    public Person getHost() {
        return host;
    }


    public boolean equals(Meeting meeting) {
        return this.meetingDate.equals(meeting.meetingDate) && this.host.equals(meeting.host) && this.attendees.equals(meeting.attendees);
    }

    public boolean equaldate(Meeting meeting) {
        return this.meetingDate.equals(meeting.meetingDate);
    }

    public void addAttendee(Person m1) {
        boolean exist = false;
        for (Person attendee : attendees) {
            if (attendee.equals(m1)) {
                exist = true;
                break;
            }
        }
        if (exist) {
            attendees.add(m1); // TODO this doesn't make sense. Why add him if he already exists?
        } else {
            m1.addMeeting(this);
        }
    }

    public boolean removeAttendee(Person m2) {
        boolean exist = false;
        for (Person attendee : attendees) {
            if (attendee.equals(m2)) {
                exist = true;
                break;
            }
        }
        if (exist) {
            attendees.remove(m2);
            m2.removeMeeting(this); // TODO what if he was the host?
            return true;
        }

        return false;
    }

    public void removeAllAttendees() {
        attendees.clear();
    }

    @Override
    public String toString() {
        String p = "meeting name: " + this.name + "\nhost : " + this.host + "\ndate: " + this.meetingDate + "\nattendees:\n";

        for (Person attendee : attendees) {
            p = p + attendee.toString() + "\n";
        }
        return p;
    }


    public String getName() {
        return name;
    }

}
