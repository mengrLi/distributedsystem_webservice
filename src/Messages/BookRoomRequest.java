package Messages;

import Domain.CampusName;
import com.google.gson.GsonBuilder;

import Domain.TimeSlot;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Calendar;

@RequiredArgsConstructor
@Getter
public class BookRoomRequest {
    private final CampusName campusOfInterest;
    private final String roomNumber;
    private final Calendar date;
    private final TimeSlot timeSlot;
    private final CampusName campusOfId;
    private final int id;

    public String sendRequest(ws_client.dvl.Server campusInterface) {
        return campusInterface.bookRoom(toString());
    }

    public String sendRequest(ws_client.kkl.Server campusInterface) {
        return campusInterface.bookRoom(toString());
    }

    public String sendRequest(ws_client.wst.Server campusInterface) {
        return campusInterface.bookRoom(toString());
    }

    public static BookRoomRequest parseRequest(String jsonMessage) {
        return new GsonBuilder().create().fromJson(jsonMessage, BookRoomRequest.class);
    }

    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this, BookRoomRequest.class);
    }
}