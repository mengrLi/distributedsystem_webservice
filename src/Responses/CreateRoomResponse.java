package Responses;

import com.google.gson.GsonBuilder;
import Domain.TimeSlot;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CreateRoomResponse {
    private final List<List<TimeSlot>> room;

    public static List<List<TimeSlot>> parseResponse(String responseMessage) {
        return new GsonBuilder().create().fromJson(responseMessage, CreateRoomResponse.class).room;
    }

    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this, CreateRoomResponse.class);
    }


}
