package Client;

import Domain.CampusName;
import Domain.Room;
import Domain.Lock;

import Client.UserInterface;
import Messages.GetTimeSlotByRoomRequest;
import java.util.Calendar;
import java.util.Map;

import Messages.GetTimeSlotByRoomRequest;

import java.io.IOException;
import java.util.Calendar;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;



public abstract class Client implements UserInterface{
    protected final CampusName CAMPUS;
    protected final int ID;
    protected final String FULL_ID;
    protected final Logger LOG;
    private FileHandler fileHandler;
    protected final Lock LOG_LOCK = new Lock();
    protected ws_client.dvl.Server dvlServer;
    protected ws_client.kkl.Server kklServer;
    protected ws_client.wst.Server wstServer;



    Client(CampusName campus, String type, int id) {
        this.CAMPUS = campus;
        this.ID = id;
        FULL_ID = campus.abrev + type + id;
        LOG = Logger.getLogger(FULL_ID + " " + Client.class);
        initLogger();
        switch (CAMPUS) {
            case DORVAL:
                dvlServer = new ws_client.dvl.ServerService().getServerPort();
                break;
            case KIRKLAND:
                kklServer = new ws_client.kkl.ServerService().getServerPort();
                break;
            case WESTMOUNT:
                wstServer = new ws_client.wst.ServerService().getServerPort();
                break;
        }
    }

    private void initLogger() {
        try {
            String dir = "src/client_log/";
            LOG.setUseParentHandlers(false);
            fileHandler = new FileHandler(dir + FULL_ID + ".LOG", true);
            LOG.addHandler(fileHandler);
            SimpleFormatter simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Map<String, Room> getAvailableTimeSlot(Calendar date, CampusName campus) {
        synchronized (this.LOG_LOCK) {
            LOG.info("\n" + FULL_ID + " check all available time slots on " + date.getTime() + " in " + campus.name);
        }
        switch (CAMPUS) {
           case DORVAL:
                return new GetTimeSlotByRoomRequest(date, campus).sendRequest(dvlServer);
            case KIRKLAND:
                return new GetTimeSlotByRoomRequest(date, campus).sendRequest(kklServer);
            case WESTMOUNT:
                return new GetTimeSlotByRoomRequest(date, campus).sendRequest(wstServer);
        }
        return null;
    }

    @Override
    public void logout() {
        synchronized (this.LOG_LOCK) {
            LOG.info("\n" + FULL_ID + "Logged out");
        }
        fileHandler.close();
    }

    @Override
    public void closeLogFileHandler() {
        fileHandler.close();
    }


}





