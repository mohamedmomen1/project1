import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class test {
    static ArrayList<Meeting> allMeeting = new ArrayList<>();
    static ArrayList<Person> allattend = new ArrayList<>();
    static ArrayList<Person> alluser = new ArrayList<>();
    static Scanner m = new Scanner(System.in);
    static Person currentUser = new Person("");

    public static void main(String[] args) {
        String o;
        int choice = -1;

        while (true) {
            System.out.println("enter username: ");
            o = m.next();
            if (o.equals("-1")) {
                break;
            } else {
                alluser.add(new Person(o));
            }

        }
        boolean logIn = false;
        while (true) {

            if (logIn) {
                System.out.println("0. Login\n" +
                        "1. Create/Host new Meeting\n" +
                        "2. Cancel a meeting\n" +
                        "3. Attend an existing meeting\n" +
                        "4. Leave a meeting\n" +
                        "5. Display My Meetings\n" +
                        "6. Display Meetings organized by me\n" +
                        "7. Logout\n" +
                        "8. Exit");
                choice = m.nextInt();
                switch (choice) {
                    case 0:
                        System.out.println("you are already login");
                        break;

                    case 1:
                        createMeeting(currentUser);
                        break;

                    case 2:
                        cancelMeeting(currentUser);
                        break;

                    case 3:
                        attendMeeting(currentUser);
                        break;

                    case 4:
                        leaveMeeting(currentUser);
                        break;

                    case 5:
                        if(currentUser.getMyMeeting().size() == 0){
                            System.out.println("your meeting is empty");
                        }else {
                            System.out.println("List of meetings attended by " + currentUser.getName() + " : ");
                            currentUser.displayMyMeetings();
                        }
                        break;

                    case 6:
                        if (currentUser.getiOrganized().size() == 0){
                            System.out.println("You haven't organized any meeting yet!");
                        }else {
                            System.out.println("List of meetings organized by " + currentUser.getName() + " : ");
                            currentUser.displayMyOrgnizations();
                        }
                        break;

                    case 7:
                        logIn = false;
                        break;
                    case 8:
                        System.out.println("bye!");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Your choice is invalid ! Try again ");
                }

            } else {
                System.out.println("0. Login\n" +
                        "1. Create/Host new Meeting\n" +
                        "2. Cancel a meeting\n" +
                        "3. Attend an existing meeting\n" +
                        "4. Leave a meeting\n" +
                        "5. Display My Meetings\n" +
                        "6. Display Meetings organized by me\n" +
                        "7. Logout\n" +
                        "8. Exit");
                choice = m.nextInt();
                switch (choice) {
                    case 0:
                        System.out.println("Enter user name to login: ");
                        String k;
                        k = m.next();
                        int index = 0;
                        boolean istrue = false;
                        for (int i = 0; i < alluser.size(); i++)
                            if (alluser.get(i).getName().equals(k)) {
                                index = i;
                                istrue = true;
                                break;
                            }
                        if (istrue) {
                            currentUser = alluser.get(index);
                            logIn = true;
                            break;
                        }else {
                            System.out.println(" no such user exist");
                        }
                        break;

                    case 7:
                        System.out.println("you are already logged out");
                        break;
                    case 8:
                        System.exit(1);
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        System.out.println("you must log in to use program");
                        break;
                }


            }
        }
    }

    private static void createMeeting(Person host) {
        Scanner input = new Scanner(System.in);

        Date date = new Date();

        System.out.println("Enter the name of the meeting: ");
        String name = input.next();

        ArrayList<Person> list = new ArrayList<>();

        System.out.println("Enter the name: ");
        String attendeeName = input.next();
        Person attendee = new Person(attendeeName);
        list.add(attendee);

        Meeting meeting = new Meeting(name, date, host, list);
        host.organizeMeeting(meeting);
        allMeeting.add(meeting);
    }

    private static void cancelMeeting(Person host) {
        Scanner input = new Scanner(System.in);
        System.out.println("Meetings organized by you:\n");
        host.displayMyOrgnizations();

        System.out.println("Enter the name of the meeting you wish to cancel : ");
        String cancelMe = input.next();
        int index = -1;
        // TODO this is wrong. You display iOrganize of a host, not all meetings
        // done
        for (int i = 0; i < host.getiOrganized().size(); i++) {
            if (host.getiOrganized().get(i).getName().equals(cancelMe)) {
                index = i;
                break;
            }
        }

        // TODO what if he entered a wrong name?
        // fix by abe
        if (index == -1) {
            System.out.println("you entered a wrong name");
            return;
        }

        System.out.println("All attendees of " + host.getiOrganized().get(index).getName() + " have been removed. " + host.getiOrganized().get(index).getName() + " is cancelled.");
        host.getiOrganized().remove(index);

        host.cancelMeeting(host.getiOrganized().get(index));
    }

    private static void attendMeeting(Person user) {
        Scanner input = new Scanner(System.in);
        System.out.println("Meetings available : ");
        for (int i = 0; i < allMeeting.size(); i++) {
            System.out.println("Meeting's name : " + allMeeting.get(i).getName()
                    + " at " + allMeeting.get(i).getMeetingDate());

        }
        System.out.println("would you like to attend any one of them ? (y/n)");
        char yesNo = input.next().charAt(0);
        if (yesNo == 'y') {
            System.out.println("Enter the name of the meeting you wish to attend : ");
            String meetingToAttend = input.next();
            int index = -1;
            for (int i = 0; i < allMeeting.size(); i++) {
                if (allMeeting.get(i).getName().equals(meetingToAttend)) {
                    index = i;
                    break;
                }
            }
            allMeeting.get(index).addAttendee(user);
            System.out.println("ok, the meeting has been added to your agenda");
        }
    }

    private static void leaveMeeting(Person user) {
        System.out.println("These are the meetings that you are attending : ");
        user.displayMyMeetings();
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the name of the meeting you wish to leave : ");
        String meetingToLeave = input.next();
        int index = -1;
        for (int i = 0; i < user.getMyMeeting().size(); i++) {
            if (user.getMyMeeting().get(i).getName().equals(meetingToLeave)) {
                index = i;
                break;
            }
        }
        user.removeMeeting(user.getMyMeeting().get(index));
    }
}