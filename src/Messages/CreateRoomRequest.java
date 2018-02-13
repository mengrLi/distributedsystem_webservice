package Messages;

import com.google.gson.GsonBuilder;
import Domain.TimeSlot;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import Responses.CreateRoomResponse;

import java.util.Calendar;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class CreateRoomRequest {
    private final String roomNumber;
    private final Calendar date;
    private final List<TimeSlot> list;
    private final String fullID;

    public static CreateRoomRequest parseRequest(String json) {
        return new GsonBuilder().create().fromJson(json, CreateRoomRequest.class);
    }

    public List<List<TimeSlot>> sendRequest(ws_client.wst.Server campusInterface) {
        String responseMessage = campusInterface.createRoom(toString());
        return CreateRoomResponse.parseResponse(responseMessage);
    }
    public List<List<TimeSlot>> sendRequest(ws_client.kkl.Server campusInterface) {
        String responseMessage = campusInterface.createRoom(toString());
        return CreateRoomResponse.parseResponse(responseMessage);
    }
    public List<List<TimeSlot>> sendRequest(ws_client.dvl.Server campusInterface) {
        String responseMessage = campusInterface.createRoom(toString());
        return CreateRoomResponse.parseResponse(responseMessage);
    }

    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this, CreateRoomRequest.class);
    }
}
