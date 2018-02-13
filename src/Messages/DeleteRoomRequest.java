package Messages;
import com.google.gson.GsonBuilder;
import Domain.TimeSlot;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import Responses.DeleteRoomResponse;


import java.util.Calendar;
import java.util.List;

@RequiredArgsConstructor
@Getter

public class DeleteRoomRequest {

        private final String roomNumber;
        private final Calendar date;
        private final List<TimeSlot> list;
        private final String fullID;

        public List<List<TimeSlot>> sendRequest(ws_client.wst.Server campusInterface) {
            String responseMessage = campusInterface.deleteRoom(toString());
            return DeleteRoomResponse.parseResponse(responseMessage);
        }
    public List<List<TimeSlot>> sendRequest(ws_client.kkl.Server campusInterface) {
        String responseMessage = campusInterface.deleteRoom(toString());
        return DeleteRoomResponse.parseResponse(responseMessage);
    }
    public List<List<TimeSlot>> sendRequest(ws_client.dvl.Server campusInterface) {
        String responseMessage = campusInterface.deleteRoom(toString());
        return DeleteRoomResponse.parseResponse(responseMessage);
   }
        @Override
        public String toString() {
            return new GsonBuilder().create().toJson(this, DeleteRoomRequest.class);
        }

        public static DeleteRoomRequest parseRequest(String requestMessage) {
            return new GsonBuilder().create().fromJson(requestMessage, DeleteRoomRequest.class);
        }
}
