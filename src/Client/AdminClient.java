package Client;

import Domain.CampusName;
import Domain.TimeSlot;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Calendar;
import java.util.List;
import Messages.CheckAdminIdRequest;
import Messages.CreateRoomRequest;
import Messages.DeleteRoomRequest;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@SuppressWarnings("Duplicates")
public class AdminClient extends Client {


    public AdminClient(CampusName campus, int id){
        super(campus, "a", id);
        System.out.println("Admin client started with id " + FULL_ID);
        synchronized (this.LOG_LOCK) {
            LOG.info("Admin " + FULL_ID + " logged in");
        }
    }


    public boolean checkID() {
        boolean response = false;
        switch (CAMPUS) {
            case DORVAL:
                response = new CheckAdminIdRequest(FULL_ID).sendRequest(dvlServer);
                break;
            case KIRKLAND:
                response = new CheckAdminIdRequest(FULL_ID).sendRequest(kklServer);
                break;
            case WESTMOUNT:
                response = new CheckAdminIdRequest(FULL_ID).sendRequest(wstServer);
                break;
        }
        System.out.println("checking ID " + FULL_ID + "from server");
        synchronized (this.LOG_LOCK) {
            if (response) LOG.info(" Administrator " + FULL_ID + " has logged into " + CAMPUS.name + " server");
            else LOG.info(FULL_ID + "is trying to access " + CAMPUS.name + ": Access DENIED");
        }
        return response;
    }
    public boolean createRoom(String roomNumber, Calendar date, List<TimeSlot> list){
        StringBuilder builder = new StringBuilder();
        builder.append(" :").append(FULL_ID)
                .append(" create room at ").append(CAMPUS.name)
                .append(" for ").append(date.getTime()).append("\n");
        for (TimeSlot slot : list)
            builder.append(" from ")
                    .append(slot.getStartTime().getTime()).append(" to ")
                    .append(slot.getEndTime().getTime()).append("\n");
        List<List<TimeSlot>> response = null;
        switch (CAMPUS) {
            case DORVAL:
                response = new CreateRoomRequest(roomNumber, date, list, FULL_ID)
                        .sendRequest(dvlServer);
                break;
            case KIRKLAND:
                response = new CreateRoomRequest(roomNumber, date, list, FULL_ID)
                        .sendRequest(kklServer);
                break;
            case WESTMOUNT:
                response = new CreateRoomRequest(roomNumber, date, list, FULL_ID)
                        .sendRequest(wstServer);
                break;
       }
        if (response == null) {
            LOG.info("ILLEGAL ACCESS OF SERVER USING INVALID ADMIN ID");
            return false;
        }
        if (response.get(0).size() == 0) {
            LOG.info(builder.append(" SUCCEEDED").toString());
            return true;
        } else {
            System.err.println("The following time slot was not successfully created");
            builder.append(" Partially succeeded with the following exception\n");
            for (TimeSlot slot : response.get(0))
                builder.append(" from ")
                        .append(slot.getStartTime().getTime())
                        .append(" to ")
                        .append(slot.getEndTime().getTime())
                        .append("\n");
            //log.info(builder.toString());
            return false;
        }

    }

    public boolean deleteRoom(String roomNumber, Calendar date, List<TimeSlot> list){
        StringBuilder builder = new StringBuilder();
        builder.append(" Administrator ").append(FULL_ID).append(" delete rooms from ")
                .append(CAMPUS.name).append(" server ")
                .append(" on ").append(date.getTime()).append(" for\n");
        for (TimeSlot slot : list)
            builder.append(" from ").append(slot.getStartTime().getTime()).append(" to ")
                    .append(slot.getEndTime().getTime()).append("\n");
        List<List<TimeSlot>> response = null;
        switch (CAMPUS) {
            case DORVAL:
                response = new DeleteRoomRequest(roomNumber, date, list, FULL_ID)
                        .sendRequest(dvlServer);
                break;
            case KIRKLAND:
                response = new DeleteRoomRequest(roomNumber, date, list, FULL_ID)
                        .sendRequest(kklServer);
                break;
            case WESTMOUNT:
                response = new DeleteRoomRequest(roomNumber, date, list, FULL_ID)
                        .sendRequest(wstServer);
                break;
        }
        if (response == null) {
            LOG.info("ILLEGAL ACCESS OF SERVER USING INVALID ADMIN ID");
            return false;
        }
        if (response.get(0).size() == 0) {
            LOG.info(builder.append(" SUCCEEDED").toString());
            return true;
        } else {
            System.err.println("The following time slot was not successfully deleted");
            builder.append("Partially succeeded with the following exception");
            for (TimeSlot slot : response.get(0)) {
                builder.append(" from ").append(slot.getStartTime().getTime()).append(" to ")
                        .append(slot.getEndTime().getTime()).append("\n");
                System.err.println(slot.toString());
            }
            LOG.severe(builder.toString());
            return false;
        }

    }



}

