package Main;

import Client.AdminTerminal;
import Domain.CampusName;
import Server.Server;
import Client.UserTerminal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.registry.LocateRegistry;

public class main {

    public static void main(String[] args) {

        ServerStart.turnOnServerNew();



        UserTerminal ui = new UserTerminal();
        Thread threadUI = new Thread(ui);

        threadUI.start();
        try {
            threadUI.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
