import Client.UserInterface;
import Domain.TimeSlot;

import java.security.PublicKey;
import java.util.Calendar;
import java.util.List;

public class MuliThreadTest implements UserInterface {
    static UserInterface stub;
    String ThreadID;

    public MuliThreadTest(String n_ThreadID)
    {
        this.ThreadID= n_ThreadID;
    }



    @Override
    public void logout() {

    }

//    public static void main(String[] args) {
//
//        Thread testForCreatROOM[] = new Thread[10];
//        for(int i=0;i<10;i++){
//            testForCreateRoom[i] = new Thread(new MuliThreadTest("CreatRoom " + i));
//        }
//        for(int i=0;i<10;i++){
//            testForCreateDR[i].start();
//        }
//    }

}
