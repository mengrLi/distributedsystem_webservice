package Messages;


import com.google.gson.GsonBuilder;
import Domain.CampusName;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import Responses.GetTimeSlotCountResponse;

import java.util.Calendar;
import java.util.Map;

@RequiredArgsConstructor
@Getter
public class GetTimeSlotCountRequest {
    private final Calendar date;

    public Map<String, Integer> sendRequest(ws_client.wst.Server campusInterface) {
        String responseMessage = campusInterface.getAvailableTimeSlotCount(toString());
        return GetTimeSlotCountResponse.parseResponse(responseMessage);
    }
    public Map<String, Integer> sendRequest(ws_client.kkl.Server campusInterface) {
        String responseMessage = campusInterface.getAvailableTimeSlotCount(toString());
        return GetTimeSlotCountResponse.parseResponse(responseMessage);
    }
    public Map<String, Integer> sendRequest(ws_client.dvl.Server campusInterface) {
        String responseMessage = campusInterface.getAvailableTimeSlotCount(toString());
        return GetTimeSlotCountResponse.parseResponse(responseMessage);
    }
    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this, GetTimeSlotCountRequest.class);
    }

    public static GetTimeSlotCountRequest parseRequest(String requestMessage) {
        return new GsonBuilder().create().fromJson(requestMessage, GetTimeSlotCountRequest.class);
    }

}
