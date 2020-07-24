import java.util.ArrayList;

public class Person {
    private int id;
    private String name;

    private ArrayList<Meeting> myMeeting;
    private ArrayList<Meeting> iOrganized;

    private static int count = 1;

    public Person(String name) {
        this.name = name;
        this.id = count;

        myMeeting = new ArrayList<>();
        iOrganized = new ArrayList<>();

        count++;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Meeting> getMyMeeting() {
        return myMeeting;
    }

    public void setMyMeeting(ArrayList<Meeting> myMeeting) {
        this.myMeeting = myMeeting;
    }

    public ArrayList<Meeting> getiOrganized() {
        return iOrganized;
    }

    public void setiOrganized(ArrayList<Meeting> iOrganized) {
        this.iOrganized = iOrganized;
    }


    public boolean equals(Person person) {
        return this.name.equals(person.name) && this.id == person.id;
    }

    // TODO this doesn't work
    public boolean addMeeting(Meeting mAdd) {
        for (int i = 0; i < myMeeting.size(); i++) {
            if (myMeeting.get(i).equaldate(mAdd)) {
                return false;
            } else {
                myMeeting.add(mAdd);
                return true;
            }

        }
        return false;
    }

    // TODO can be simplified
    public void removeMeeting(Meeting mRemove) {
        int index = -1;
        for (int i = 0; i < myMeeting.size(); i++)
            if (myMeeting.get(i).equals(mRemove)) {
                index = i;
                break;
            }
        myMeeting.remove(index);
    }

    // TODO what if it is already added?
    // TODO what about the host of the meeting?
    public void organizeMeeting(Meeting r) {
        iOrganized.add(r);
    }

    // TODO this has wrong behaviour. I can only cancel a meeting which is mine (i.e. in iOrganized list)
    public void cancelMeeting(Meeting o) {
        int index = -1;
        for (int i = 0; i < myMeeting.size(); i++) {
            if (myMeeting.get(i).equals(o)) {
                index = i;
                break;
            }
        }
        iOrganized.remove(index);

        // TODO removing meeting from attendees is wrong. You remove using removeMeeting, not index
        for (int i = 0; i < o.getAttendees().size(); i++) {
            o.getAttendees().get(i).myMeeting.remove(index);
        }
    }

    public void displayMyMeetings() {
        for (int i = 0; i < myMeeting.size(); i++) {
            System.out.println("meeting has host named : " + myMeeting.get(i).getHost() + " with date of : " +
                    myMeeting.get(i).getMeetingDate());
        }
    }

    public void displayMyOrgnizations() {
        for (Meeting meeting : iOrganized) {
            System.out.println("meeting has host named : " + meeting.getHost() + " with date of : " +
                    meeting.getMeetingDate());
        }
    }

    @Override
    public String toString() {
        String p = "Name : " + this.name + ", id : " + this.id;
        return p;
    }
}
