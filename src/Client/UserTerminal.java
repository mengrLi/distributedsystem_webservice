package Client;

import com.sun.istack.internal.Nullable;
import Domain.CampusName;
import Domain.Format;
import Domain.Room;
import Domain.TimeSlot;
import com.sun.xml.internal.bind.v2.model.core.ID;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import Client.StudentClient;


public class UserTerminal implements Runnable {

    private UserInterface client;

    private boolean admin;


    private CampusName campusOfTheID = null;

    private CampusName campusOfInterest = null;


    private int id;


    private boolean exit;


    public UserTerminal() {
    }

    public static void main(String[] args) {
        UserTerminal ui = new UserTerminal();
        Thread thread = new Thread(ui);
        thread.start();
    }

    private void init() {
        System.out.println("Enter your username:");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String username = null;
            do {
                if (username != null) System.err.println("Invalid username");
                username = bufferedReader.readLine();
            } while (!connectToServer(username));

            System.out.println("Hello, " + (admin ? "administrator" : "student") + " " + id + "\n"
                    + "You are logging to " + campusOfTheID.name);

            exit = false;
            do {
                System.out.println("Please enter your command:");
                String input = bufferedReader.readLine();
                parseInput(input.toLowerCase(), bufferedReader);
            } while (!exit);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * User always connects to his own server at log in
     * <p>
     * Determine what kind of connection should be used, parse id info
     *
     * @param username String user id
     * @return correctness of input user id
     */
    private boolean connectToServer(String username) {
        if (username.length() != 8) return false;
        String type = username.substring(3, 4).toUpperCase();
        String campus = username.substring(0, 3).toUpperCase();
        try {
            id = Integer.parseInt(username.substring(4));
        } catch (NumberFormatException e) {
            return false;
        }
        campusOfTheID = CampusName.getCampusName(campus);
        if (campusOfTheID == null) return false;

        if (type.equals("A")) {
            //client = new AdminClient(campusOfTheID, id);
            //admin = client.checkID();
            return admin;

        } else {
            client = new StudentClient(campusOfTheID, id);
            return true;
        }


    }


    /**
     * parsing the input, decide which action to take
     *
     * @param input  console input
     * @param reader buffered reader
     * @throws IOException io exception
     */
    private void parseInput(String input, BufferedReader reader) throws IOException {

        switch (input) {
            case "book":
                bookRoom(reader);
                break;
            case "check":
                checkAvailability(reader);
                break;
            case "cancel":
                cancelRoom(reader);
                break;
            case "change":
                changeReservation(reader);
                break;
            case "exit":
                exit = true;
                break;
            default:
                System.err.println("Please enter \"book\" to book a room\n" +
                        "\"cancel\" to cancel a booked room\n" +
                        "\"check\" to check the availability of a room\n" +
                        "\"change\" to change reservation\n" +
                        "\"exit\" to exit\n");
                break;
        }
    }


    /**
     * input date from buffered reader
     *
     * @param reader buffered reader
     * @return Calendar object containing the correct date and time
     * @throws IOException buffered reader io exception
     */
    private Calendar inputDate(BufferedReader reader) throws IOException {
        String dateString;
        String[] delim;
        boolean validDate;
        Calendar calendar = Calendar.getInstance();

        do {
            println("Please enter the date (yyyy/mm/dd) :");
            dateString = reader.readLine();
            delim = dateString.split("/");
            validDate = parseDate(delim, calendar);
        } while (!validDate);
        return calendar;
    }

    /**
     * parse string date input
     *
     * @param dateDelim delimited string input
     * @param calendar  calendar to be modified by reference
     * @return success or failure of parsing
     */private boolean parseDate(String[] dateDelim, Calendar calendar) {
        if (dateDelim.length != 3) {
            printlnErr("Invalid date format");
            return false;
        }
        try {
            int year, month, day;
            year = Integer.parseInt(dateDelim[0]);
            month = Integer.parseInt(dateDelim[1]) - 1;
            day = Integer.parseInt(dateDelim[2]);
            if (month < 0 || month > 12 || day > 31 || day < 0) {
                printlnErr("Invalid date input : " + month + " " + day);
                return false;
            } else {
                if (month == 4 || month == 6 || month == 9 || month == 11) {
                    if (day > 30) {
                        printlnErr("Invalid day input " + day);
                        return false;
                    }
                } else if (month == 2) {
                    if (year % 4 == 0 && day > 29) {
                        printlnErr("Invalid day input for even February " + day);
                        return false;
                    } else if (year % 4 != 0 && day > 28) {
                        printlnErr("Invalid day input for odd February " + day);
                        return false;
                    }
                }
            }
            calendar.set(year, month, day, 0, 0, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            return true;
        } catch (NumberFormatException e) {
            printlnErr("Invalid date format");
            return false;
        }
    }


    /*-*****************************************************************************************************************
     *   STUDENT
     ******************************************************************************************************************/

    /**
     * @param reader buffered reader
     * @return all available rooms with its info
     * @throws IOException io exception
     */
    @Nullable
    private void checkAvailability(BufferedReader reader) throws IOException {
        //select date
        Calendar calendar = inputDate(reader);
        //show select room
        Map<String, Integer> get = getAvailableRooms(calendar);
        for (Map.Entry<String, Integer> entry : get.entrySet()) {
            print(entry.getKey() + " " + entry.getValue() + "; ");
        }
        println("");
    }

    /**
     * Student book room
     *
     * @param reader buffered reader
     * @throws IOException io exception
     */
    private void bookRoom(BufferedReader reader) throws IOException {
        //select campus
        if (selectCampus(reader) == null) return;
        //select date
        Calendar calendar = inputDate(reader);
        //show available rooms
        Map<String, Room> availableRoom = getAvailableRooms(campusOfInterest, calendar);
        //select room
        Room room;
        if (availableRoom != null) room = selectRoom(reader, availableRoom);
        else return;

        //select slot
        TimeSlot slot;
        if (room != null) slot = selectSlot(reader, room);
        else return;

        if (slot == null) return;
        println("Booking ID : "
                + client.bookRoom(campusOfInterest, room.getRoomNumber(), calendar, slot, campusOfTheID, id));

        campusOfInterest = null;//reset campusOfInterest after use
    }

    private void changeReservation(BufferedReader reader) throws IOException {

        println("Please enter your reservation information");
        println("Please enter your bookingID");
        String bookingID = reader.readLine();

        if (selectCampus(reader) == null) return;

        //select date
        Calendar calendar = inputDate(reader);
        //show available rooms
        Map<String, Room> availableRoom = getAvailableRooms(campusOfInterest, calendar);
        //select room
        Room room;
        if (availableRoom != null) {
            room = selectRoom(reader, availableRoom);
        } else {
            return;
        }
        TimeSlot slot;
        if (room != null){ slot = selectSlot(reader, room);}
        else {return;}

        if (slot == null) {return;}

        String newBookingId=client.changeReservation(campusOfInterest, room.getRoomNumber(), calendar, slot, bookingID,campusOfTheID, id);
        if(bookingID.equalsIgnoreCase(newBookingId)){

            System.out.println("change reservation false, your original bookingID is:"+bookingID);
        }
        else {
            System.out.println("change reservation successful, new booking ID is:" + newBookingId);
        }

        campusOfInterest = null;//reset campusOfInterest after use
    }

    /**
     * cancel room booking function
     *
     * @param reader buffered reader
     * @throws IOException io exception
     */
    private void cancelRoom(BufferedReader reader) throws IOException {
        println("Please enter your reservation code");
        String input = reader.readLine();
        if (client.cancelBooking(input)) println("Your booking has been canceled");
        else printlnErr("Your booking cannot be canceled");

    }

    /**
     * private select campus method
     *
     * @param reader buffered reader
     * @return null if select exit, no operation will be done
     * @throws IOException buffered reader io exception
     */
    @Nullable
    private CampusName selectCampus(BufferedReader reader) throws IOException {
        String input;
        do {
            println("Please select in which campus you would like to book a room?\n" +
                    "\"dorval\" , \"westmount\" or \"kirkland\"\n");
            input = reader.readLine().toLowerCase();
            switch (input) {
                case "dorval":
                    campusOfInterest = CampusName.DORVAL;
                    return campusOfInterest;
                case "westmount":
                    campusOfInterest = CampusName.WESTMOUNT;
                    return campusOfInterest;
                case "kirkland":
                    campusOfInterest = CampusName.KIRKLAND;
                    return campusOfInterest;
                case "exit":
                    println("Exit");
                    return null;
                default:
                    printlnErr("Invalid input " + input);
            }
        } while (campusOfInterest == null);
        return null;
    }

    private Map<String, Integer> getAvailableRooms(Calendar date) {
        return client.getAvailableTimeSlot(date);
    }

    /**
     * get all available rooms
     *
     * @param calendar Date of interest
     * @return Map of rooms that are available
     */
    @Nullable
    public Map<String, Room> getAvailableRooms(CampusName campus, Calendar calendar) {

        Map<String, Room> availableRooms = client.getAvailableTimeSlot(calendar, campus);

        if (availableRooms == null) {
            printlnErr("No room is available on " + Format.formatDate(calendar));
            return null;
        } else {
            /*
            Display only the available ones
             */
            println(Format.formatDate(calendar));
            for (Map.Entry<String, Room> entry : availableRooms.entrySet()) {
                println("Room number - " + entry.getKey());
                for (TimeSlot slot : entry.getValue().getTimeSlots()) {
                    if (slot.getStudentID() == null) {
                        println("\t" + Format.formatTime(slot.getStartTime()) +
                                "\tto\t" +
                                Format.formatTime(slot.getEndTime()));
                    }
                }
            }
            return availableRooms;
        }
    }


    /**
     * helper function
     * Select room from the available ones
     *
     * @param reader        buffered reader
     * @param availableRoom available room list
     * @return selected Room
     * @throws IOException io exception
     */
    @Nullable
    private Room selectRoom(BufferedReader reader, Map<String, Room> availableRoom) throws IOException {
        String input;
        do {
            println("Select the available slot by inputting the room number:");
            input = reader.readLine().toLowerCase();
            for (Map.Entry<String, Room> entry : availableRoom.entrySet()) {
                if (entry.getKey().toLowerCase().equals(input)) {
                    int counter = 1;
                    int availableCounter = 0;
                    println("Available slots of room " + entry.getKey() + " are:");
                    for (TimeSlot slot : entry.getValue().getTimeSlots()) {
                        if (slot.getStudentID() == null) { //available
                            println(counter + ":\t" +
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
                        println("No room is available on the selected date");
                        return null;
                    }
                }
            }
            if (!input.equals("exit")) printlnErr(input + " is not found");
        } while (!input.equals("exit"));
        return null;
    }

    /**
     * private helper function to select the time slot
     *
     * @param reader buffered reader
     * @param room   the room containing all the slot
     * @return Time slot of interest
     * @throws IOException io exception
     */
    @Nullable
    private TimeSlot selectSlot(BufferedReader reader, Room room) throws IOException {
        String input;
        int index;
        do {
            println("Please enter the time slot index, enter 0 to forfeit");
            input = reader.readLine().toLowerCase();
            try {
                index = Integer.parseInt(input);
                if (index == 0) return null;
                else {
                    TimeSlot slot = room.getTimeSlots().get(index - 1);
                    println("Selected time slot is : " +
                            Format.formatTime(slot.getStartTime()) +
                            " to " +
                            Format.formatTime(slot.getEndTime())
                    );
                    return slot;
                }
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                printlnErr("Please enter a valid number");
                index = -1;
            }
        } while (index == -1);
        return null;
    }


    private void println(String s)
     {
        System.out.println(s);
    }

    private void printlnErr(String s) {
        System.err.println(s);
    }

    private void print(String s) {
        System.out.print(s);
    }

    private void printErr(String s) {
        System.err.print(s);
    }

    @Override
    public void run() {
        init();
        System.out.println("You are logged out");
    }
}

