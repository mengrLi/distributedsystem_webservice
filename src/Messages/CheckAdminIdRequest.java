package Messages;

import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CheckAdminIdRequest {
    private final String fullID;

    public boolean sendRequest(ws_client.wst.Server campusInterface) {
        return campusInterface.checkAdminId(toString());
    }
    public boolean sendRequest(ws_client.kkl.Server campusInterface) {
        return campusInterface.checkAdminId(toString());
    }
    public boolean sendRequest(ws_client.dvl.Server campusInterface) {
        return campusInterface.checkAdminId(toString());
    }
    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this, CheckAdminIdRequest.class);
    }

    public static CheckAdminIdRequest parseRequest(String requestMessage) {
        return new GsonBuilder().create().fromJson(requestMessage, CheckAdminIdRequest.class);
    }


}
