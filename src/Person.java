import java.util.ArrayList;

public class Person {
    private int id;
    private String name;
    private static int count = 1;

   private ArrayList<Meeting> myMeeting;
   private ArrayList<Meeting> iOrganized;

    public Person(String name){
        myMeeting = new ArrayList<>();
        iOrganized = new ArrayList<>();
        this.name = name;
        this.id = count;
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


    public ArrayList<Meeting> getiOrganized() {
        return iOrganized;
    }


    public boolean equals(Person person){
        boolean k = this.name.equals(person.name) && this.id == person.id;
        return k;
    }


    public boolean addMeeting(Meeting mAdd){
        boolean y = true;
    for (int i = 0; i < myMeeting.size(); i++) {
        if (myMeeting.get(i).equaldate(mAdd)) {
            y = false;
            break;
        }
    }
        if (!y) {
            myMeeting.add(mAdd);
            y = true;
        } else {
        y =false;
        }
            return true;
        }

    public void removeMeeting(Meeting mRemove){
        int index = -1;
        for (int i = 0;i < myMeeting.size();i++)
            if (myMeeting.get(i).equals(mRemove)){
                index = i;
                break;
            }
        myMeeting.remove(index);
    }
    public void organizeMeeting(Meeting r){
            iOrganized.add(r);

    }

    public void cancelMeeting(Meeting o){
        int index = -1;
        for (int i = 0;i < iOrganized.size();i++){
            if (iOrganized.get(i).equals(o)){
                index = i;
                iOrganized.remove(index);
                break;
            }
            }
            for (int i = 0;i < o.getAttendees().size();i++){
            o.getAttendees().get(i).myMeeting.remove(index);
        }

            }

    public void displayMyMeetings() {
        for (int i = 0; i < myMeeting.size() ; i++) {
            System.out.println("meeting has host named : " + myMeeting.get(i).getHost() + " with date of : " +
                    myMeeting.get(i).getMeetingDate());
        }
    }


    public void displayMyOrgnizations() {
        for (int i = 0; i < iOrganized.size() ; i++) {
            System.out.println("meeting has host named : " + iOrganized.get(i).getHost() + " with date of : " +
                    iOrganized.get(i).getMeetingDate());
        }

    }


    @Override
    public String toString() {
        String p = "Name : " + this.name + " , id : " + this.id;
        return p;
    }
}
