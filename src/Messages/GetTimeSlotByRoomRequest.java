package Messages;


import com.google.gson.GsonBuilder;
import Domain.CampusName;
import Domain.Room;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import Responses.GetTimeSlotByRoomResponse;

import java.util.Calendar;
import java.util.Map;

@RequiredArgsConstructor
@Getter
public class GetTimeSlotByRoomRequest {
    private final Calendar date;
    private final CampusName campus;

       public Map<String, Room> sendRequest(ws_client.wst.Server campusInterface) {
        String responseMessage = campusInterface.getAvailableTimeSlotByRoom(toString());
        return GetTimeSlotByRoomResponse.parseResponse(responseMessage);
    }
    public Map<String, Room> sendRequest(ws_client.kkl.Server campusInterface) {
        String responseMessage = campusInterface.getAvailableTimeSlotByRoom(toString());
        return GetTimeSlotByRoomResponse.parseResponse(responseMessage);
    }
    public Map<String, Room> sendRequest(ws_client.dvl.Server campusInterface) {
        String responseMessage = campusInterface.getAvailableTimeSlotByRoom(toString());
        return GetTimeSlotByRoomResponse.parseResponse(responseMessage);
    }


    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this, GetTimeSlotByRoomRequest.class);
    }

    public static GetTimeSlotByRoomRequest parseRequest(String responseMessage) {
        return new GsonBuilder().create().fromJson(responseMessage, GetTimeSlotByRoomRequest.class);
    }


}
