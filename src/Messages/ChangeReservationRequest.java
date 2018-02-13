package Messages;

import  Domain.CampusName;
import Domain.TimeSlot;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Calendar;

@RequiredArgsConstructor
@Getter
public class ChangeReservationRequest {

    private final CampusName new_campusname;
    private final String new_roomnumber;
    private final Calendar new_date;
    private final TimeSlot new_timeSlot;
    private final String bookingID;
    private final CampusName campusOfId;
    private final int id;

    public String sendRequest(ws_client.wst.Server campusInterface) {
        return campusInterface.changeReservation(toString());
    }
    public String sendRequest(ws_client.kkl.Server campusInterface) {
        return campusInterface.changeReservation(toString());
    }
    public String sendRequest(ws_client.dvl.Server campusInterface) {
        return campusInterface.changeReservation(toString());
    }
    public static  ChangeReservationRequest parseRequest(String jsonMessage) {
        return new GsonBuilder().create().fromJson(jsonMessage, ChangeReservationRequest.class);
    }
    public String toString() {
        return new GsonBuilder().create().toJson(this, ChangeReservationRequest.class);
    }
}
