package Client;

import Domain.CampusName;
import Domain.TimeSlot;
import Domain.*;

import java.util.Calendar;
import java.util.Map;
import Messages.*;
import Messages.BookRoomRequest;
import Messages.CancelBookingRequest;

@SuppressWarnings("Duplicates")
public class StudentClient extends Client {



    public StudentClient(CampusName campus, int id) {
        super(campus, "s", id);
        System.out.println("Student client started with id " + FULL_ID);
        synchronized (LOG_LOCK) {
            LOG.info("\nStudent " + FULL_ID + " logged in");
        }

    }




    public String bookRoom(CampusName campusOfInterest,
                           String roomNumber,
                           Calendar date,
                           TimeSlot timeSlot,
                           CampusName campusOfID,
                           int id) {
        switch (CAMPUS) {
            case DORVAL:
                break;
            case KIRKLAND:
                break;
            case WESTMOUNT:
                break;
        }
        BookRoomRequest bookRoomRequest = new BookRoomRequest(
                campusOfInterest,
                roomNumber,
                date,
                timeSlot,
                campusOfID,
                id);
        String response = "ERROR";
        switch (CAMPUS) {
            case DORVAL:
                response = bookRoomRequest.sendRequest(dvlServer);
                break;
            case KIRKLAND:
                response = bookRoomRequest.sendRequest(kklServer);
                break;
            case WESTMOUNT:
                response = bookRoomRequest.sendRequest(wstServer);
                break;
        }
        synchronized (LOG_LOCK) {
            LOG.info("\nStudent " + FULL_ID
                    + "\nbooking room " + roomNumber
                    + "\nfrom " + timeSlot.getStartTime().getTime()
                    + "\nto " + timeSlot.getEndTime().getTime()
                    + "\nin " + campusOfInterest.name
                    + "\n---" + (response.startsWith("Error") ? "FAILED" : "SUCCEEDED"));

        }
        System.out.println("\nStudent " + FULL_ID
                + "\nbooking room " + roomNumber
                + "\nfrom " + timeSlot.getStartTime().getTime()
                + "\nto " + timeSlot.getEndTime().getTime()
                + "\nin " + campusOfInterest.name
                + "\n---" + (response.startsWith("Error") ? "FAILED" : "SUCCEEDED"));
        return response;
    }


    public String changeReservation(CampusName new_campusname, String new_roomnumber, Calendar new_date,
                                    TimeSlot new_timeslot,String bookingID,  CampusName campusOfID, int id)
    {


        switch (CAMPUS) {
            case DORVAL:
                break;
            case KIRKLAND:
                break;
            case WESTMOUNT:
                break;
        }
        String response= null;
        ChangeReservationRequest request = new ChangeReservationRequest(new_campusname,new_roomnumber,new_date,new_timeslot,bookingID,campusOfID,id);


        BookingInfo info = BookingInfo.decode(bookingID);

        switch (CAMPUS) {
            case DORVAL:
                response = request.sendRequest(dvlServer);
                break;
            case KIRKLAND:
                response = request.sendRequest(kklServer);
                break;
            case WESTMOUNT:
                response = request.sendRequest(wstServer);
                break;
        }
        return response;



    }

    public Map<String, Integer> getAvailableTimeSlot(Calendar date) {
        synchronized (LOG_LOCK) {
            LOG.info("\n" + FULL_ID + " check available time slots count of all campus on " + date.getTime());
        }
        switch (CAMPUS) {
            case DORVAL:
                return new GetTimeSlotCountRequest(date).sendRequest(dvlServer);
            case KIRKLAND:
                return new GetTimeSlotCountRequest(date).sendRequest(kklServer);
            case WESTMOUNT:
                return new GetTimeSlotCountRequest(date).sendRequest(wstServer);
        }
        return null;
    }





    public boolean cancelBooking(String bookingId) {
        switch (CAMPUS) {
            case DORVAL:
                break;
            case KIRKLAND:
                break;
            case WESTMOUNT:
                break;
        }
        boolean response = false;
        switch (CAMPUS) {
            case DORVAL:
                response = new CancelBookingRequest(bookingId, CAMPUS, ID).sendResquest(dvlServer);
                break;
            case KIRKLAND:
                response = new CancelBookingRequest(bookingId, CAMPUS, ID).sendResquest(kklServer);
                break;
            case WESTMOUNT:
                response = new CancelBookingRequest(bookingId, CAMPUS, ID).sendResquest(wstServer);
                break;
        }
        BookingInfo info = BookingInfo.decode(bookingId);
        synchronized (LOG_LOCK) {
            LOG.info("\nStudent " + FULL_ID
                    + "\ncanceling room " + info.getRoomName()
                    + "\nfrom " + info.getBookingStartTime().getTime()
                    + "\nto " + info.getBookingEndTime().getTime()
                    + "\nin " + info.getCampusOfInterestAbrev()
                    + "\nusing booking ID : " + bookingId
                    + "\n---" + (response ? "true" : "false"));
        }
        return response;
    }



}







