package test;

import Client.*;
import Client.UserTerminal;
import Domain.CampusName;
import Domain.Format;
import Domain.Room;
import Domain.TimeSlot;
import Messages.CreateRoomRequest;
import org.junit.Test;
import Server.*;
import org.junit.Test;

import java.io.*;
import java.security.PrivateKey;
import java.util.*;



public class AdminTest {
    private static Map<String, Room> getAvailableRooms(UserInterface client,CampusName campus, Calendar calendar) {

        Map<String, Room> availableRooms = client.getAvailableTimeSlot(calendar, campus);

        if (availableRooms == null) {
            return null;
        } else {
            /*
            Display only the available ones
             */
            for (Map.Entry<String, Room> entry : availableRooms.entrySet()) {
                for (TimeSlot slot : entry.getValue().getTimeSlots()) {
                    if (slot.getStudentID() == null) {
                    }
                }
            }
            return availableRooms;
        }
    }


    public static Calendar inputDate(String dateString) {
        String[] delim;
        boolean validDate;
        Calendar calendar = Calendar.getInstance();
        do {
            delim = dateString.split("/");
            validDate = parseDate(delim, calendar);
        } while (!validDate);
        return calendar;
    }
    private static boolean parseDate(String[] dateDelim, Calendar calendar) {
        if (dateDelim.length != 3) {
            return false;
        }
        try {
            int year, month, day;
            year = Integer.parseInt(dateDelim[0]);
            month = Integer.parseInt(dateDelim[1]) - 1;
            day = Integer.parseInt(dateDelim[2]);
            if (month < 0 || month > 12 || day > 31 || day < 0) {
                return false;
            } else {
                if (month == 4 || month == 6 || month == 9 || month == 11) {
                    if (day > 30) {
                        return false;
                    }
                } else if (month == 2) {
                    if (year % 4 == 0 && day > 29) {
                        return false;
                    } else if (year % 4 != 0 && day > 28) {
                        return false;
                    }
                }
            }
            calendar.set(year, month, day, 0, 0, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static Room selectRoom(String input, Map<String, Room> availableRoom) {
        for (Map.Entry<String, Room> entry : availableRoom.entrySet()) {
            if (entry.getKey().toLowerCase().equals(input)) {
                int counter = 1;
                int availableCounter = 0;
                System.out.println("Available slots of room " + entry.getKey() + " are:");
                for (TimeSlot slot : entry.getValue().getTimeSlots()) {
                    if (slot.getStudentID() == null) { //available
                        System.out.println(counter + ":\t" +
                                Format.formatTime(slot.getStartTime()) +
                                "\tto\t" +
                                Format.formatTime(slot.getEndTime())
                        );
                        ++availableCounter;
                    }
                    ++counter;
                }
                if (availableCounter != 0) {
                    return entry.getValue();
                } else {
                    System.out.println("No room is available on the selected date");
                    return null;
                }
            }
        }
        return null;
    }


    public static TimeSlot selectSlot(String input, Room room) {
        int index;
        index = Integer.parseInt(input);
        if (index == 0) return null;
        else {
            TimeSlot slot = room.getTimeSlots().get(index - 1);
            return slot;
        }
    }

    public static void main(String[] args) {




        List<Client> clientList = new LinkedList<>();
        Client kklAdmin = new AdminClient(CampusName.KIRKLAND, 1111);
        Client dvlAdmin = new AdminClient(CampusName.DORVAL, 1111);
        Client wstAdmin = new AdminClient(CampusName.WESTMOUNT, 1111);

        Client kklStudent1111 = new StudentClient(CampusName.KIRKLAND, 1111);


        Client dvlStudent1111 = new StudentClient(CampusName.DORVAL, 1111);


        Client wstStudent1111 = new StudentClient(CampusName.WESTMOUNT, 1111);

        clientList.add(kklAdmin);
        clientList.add(kklStudent1111);


        clientList.add(dvlAdmin);
        clientList.add(dvlStudent1111);

        clientList.add(wstAdmin);
        clientList.add(wstStudent1111);




//        File file = new File("");
//        try {
//            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
//            admin.createRoom(bufferedReader);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        /**
         * bookroomtest
         * */




        new Thread(new Runnable() {
            @Override
            public void run() {
                UserTerminal userTerminal = new UserTerminal();
                Integer id = Integer.parseInt("1111");
                CampusName campusOfTheID = CampusName.getCampusName("DVL");
                UserInterface client = new StudentClient(campusOfTheID, id);
                CampusName campusOfInterest = CampusName.KIRKLAND;
                String stringDate = "2017/12/1";
                Calendar calendar = inputDate(stringDate);
                Map<String, Room> availableRoom = getAvailableRooms(client,campusOfInterest, calendar);

                Room room = selectRoom("2", availableRoom);

                TimeSlot slot;
                slot = selectSlot("4", room);


                String bookId=client.bookRoom(campusOfInterest, room.getRoomNumber(), calendar, slot, campusOfTheID, id);
                System.out.println(bookId);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                UserTerminal userTerminal = new UserTerminal();
                Integer id = Integer.parseInt("1111");
                CampusName campusOfTheID = CampusName.getCampusName("DVL");
                UserInterface client = new StudentClient(campusOfTheID, id);
                CampusName campusOfInterest = CampusName.WESTMOUNT;
                String stringDate = "2017/12/1";
                Calendar calendar = inputDate(stringDate);
                Map<String, Room> availableRoom = getAvailableRooms(client,campusOfInterest, calendar);

                Room room = selectRoom("2", availableRoom);

                TimeSlot slot;
                slot = selectSlot("5", room);


                String bookId=client.bookRoom(campusOfInterest, room.getRoomNumber(), calendar, slot, campusOfTheID, id);
                System.out.println(bookId);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                UserTerminal userTerminal = new UserTerminal();
                Integer id = Integer.parseInt("1111");
                CampusName campusOfTheID = CampusName.getCampusName("DVL");
                UserInterface client = new StudentClient(campusOfTheID, id);
                CampusName campusOfInterest = CampusName.DORVAL;
                String stringDate = "2017/12/1";
                Calendar calendar = inputDate(stringDate);
                Map<String, Room> availableRoom = getAvailableRooms(client,campusOfInterest, calendar);

                Room room = selectRoom("2", availableRoom);

                TimeSlot slot;
                slot = selectSlot("1", room);


                String bookId=client.bookRoom(campusOfInterest, room.getRoomNumber(), calendar, slot, campusOfTheID, id);
                System.out.println(bookId);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                UserTerminal userTerminal = new UserTerminal();
                Integer id = Integer.parseInt("1111");
                CampusName campusOfTheID = CampusName.getCampusName("DVL");
                UserInterface client = new StudentClient(campusOfTheID, id);
                CampusName campusOfInterest = CampusName.DORVAL;
                String stringDate = "2017/12/1";
                Calendar calendar = inputDate(stringDate);
                Map<String, Room> availableRoom = getAvailableRooms(client,campusOfInterest, calendar);

                Room room = selectRoom("2", availableRoom);

                TimeSlot slot;
                slot = selectSlot("3", room);


                String bookId=client.bookRoom(campusOfInterest, room.getRoomNumber(), calendar, slot, campusOfTheID, id);
                System.out.println(bookId);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                UserTerminal userTerminal = new UserTerminal();
                Integer id = Integer.parseInt("1111");
                CampusName campusOfTheID = CampusName.getCampusName("KKL");
                UserInterface client = new StudentClient(campusOfTheID, id);
                CampusName campusOfInterest = CampusName.KIRKLAND;
                String stringDate = "2017/12/1";
                Calendar calendar = inputDate(stringDate);
                Map<String, Room> availableRoom = getAvailableRooms(client,campusOfInterest, calendar);

                Room room = selectRoom("1", availableRoom);

                TimeSlot slot;
                slot = selectSlot("4", room);


                String bookId=client.bookRoom(campusOfInterest, room.getRoomNumber(), calendar, slot, campusOfTheID, id);
                System.out.println(bookId);
            }
        }).start();

    }

}
