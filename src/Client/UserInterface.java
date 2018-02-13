package Client;

import Domain.CampusName;
import Domain.Room;
import Domain.TimeSlot;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;


import java.util.Calendar;
import java.util.List;
import java.util.Map;

public interface UserInterface{

    default boolean checkID() {
        throw new NotImplementedException();
    }

    default boolean createRoom(String roomNumber, Calendar date, List<TimeSlot> list){
        throw new NotImplementedException();
    }

    default boolean deleteRoom(String roomNumber, Calendar date, List<TimeSlot> list){
        throw new NotImplementedException();
    }

    default String bookRoom(CampusName campusOfInterest, String roomNumber, Calendar date, TimeSlot timeSlot, CampusName campusOfID, int id){
        throw new NotImplementedException();
    }

    default Map<String, Integer> getAvailableTimeSlot(Calendar date){
        throw new NotImplementedException();
    }

    default Map<String, Room> getAvailableTimeSlot(Calendar date, CampusName campusName){
        throw new NotImplementedException();
    }

    default boolean cancelBooking(String booking){
        throw new NotImplementedException();
    }

    default String changeReservation(CampusName new_campusname, String new_roomnumber, Calendar new_date,
                                     TimeSlot new_timeslot,String bookingID,  CampusName campusOfID, int id)  {
        throw new NotImplementedException();
    }
    void logout();

    default void closeLogFileHandler() {
        throw new NotImplementedException();
    }
}

