import java.util.ArrayList;
import java.util.Date;

public class Meeting {
    private String name;
    private Date meetingDate;
    private ArrayList<Person> attendees;
    private Person host;

    public Meeting(String name,Date date, Person host, ArrayList<Person>attendees) {
        this.name = name;
        this.meetingDate = date;
        this.host = host;
        this.attendees = attendees;
        this.attendees.add(host);


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



    public boolean equals(Meeting meeting ) {
        boolean m = this.meetingDate.equals(meeting.meetingDate) && this.host.equals(meeting.host) && this.attendees.equals(meeting.attendees);
        return m;
    }
    public boolean equaldate(Meeting meeting){
        boolean a = this.meetingDate.equals(meeting.meetingDate);
        return a;
    }

    public void addAttendee(Person m1) {
         boolean doesnotexist = true;
        for (Person attendee : attendees) {
            if (attendee.equals(m1)) {
                doesnotexist = false;
                break;
            }
        }
        if (!doesnotexist){
            attendees.add(m1);

        }else {
            m1.addMeeting(this);

        }


    }

    public boolean removeAttendee(Person m2 ) {
        boolean exist = true;
        for (Person attendee : attendees) {
            if (attendee.equals(m2)) {
                exist = false;
                break;
            }
        }
        if (!exist){
            attendees.remove(m2);
            m2.removeMeeting(this);
            return true;
        }

         return false;
    }

    public void removeAllAttendees() {
        attendees.clear();
    }

    @Override
    public String toString() {
        String p = "meeting name : " + this.name + "host : " + this.host + " , date : " + this.meetingDate + " attendees: ";

        for (int i = 0; i < attendees.size(); i++) {
             p = p  + attendees.get(i).getName() + " ";

        }
       return p;
    }


    public String getName() {
        return name;
    }

}
