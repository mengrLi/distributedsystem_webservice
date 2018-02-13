package Main;

import Domain.CampusName;
import Server.Server;

import javax.xml.ws.Endpoint;
public class ServerStart {
    public static void turnOnServerNew() {

        Server dorval = new Server();
        Server kirkland = new Server();
        Server westmount = new Server();


        String addressDVL = "http://localhost:8000/dvl";
        String addressKKL = "http://localhost:8001/kkl";
        String addressWST = "http://localhost:8002/wst";
        Endpoint.publish(addressDVL, dorval);
        Endpoint.publish(addressKKL, kirkland);
        Endpoint.publish(addressWST, westmount);

       // System.out.println("aa");
        dorval.initServer(CampusName.DORVAL);
        kirkland.initServer(CampusName.KIRKLAND);
        westmount.initServer(CampusName.WESTMOUNT);

    }

    public static void main(String[] args) {
        turnOnServerNew();
    }

}
