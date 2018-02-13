package Messages;

import Domain.CampusName;
import com.google.gson.GsonBuilder;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Getter
public class CancelBookingRequest {
    private final String bookingId;
    private final CampusName campus;
    private final int id;

    public boolean sendResquest(ws_client.wst.Server campusInterface) {
        return campusInterface.cancelBooking(toString());
    }
    public boolean sendResquest(ws_client.kkl.Server campusInterface) {
        return campusInterface.cancelBooking(toString());
    }
    public boolean sendResquest(ws_client.dvl.Server campusInterface) {
        return campusInterface.cancelBooking(toString());
    }
    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this, CancelBookingRequest.class);
    }

    public static CancelBookingRequest parseRequest(String requestMessage) {
        return new GsonBuilder().create().fromJson(requestMessage, CancelBookingRequest.class);
    }
}
