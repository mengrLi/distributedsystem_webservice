package Responses;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import Domain.CampusName;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;


@Getter
@RequiredArgsConstructor
public class GetTimeSlotCountResponse {
    private final Map<String, Integer> roomCounts;

    public static Map<String, Integer> parseResponse(String responseMessage) {
        return new GsonBuilder().create().fromJson(responseMessage, GetTimeSlotCountResponse.class).roomCounts;
    }

    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this, GetTimeSlotCountResponse.class);
    }
}
