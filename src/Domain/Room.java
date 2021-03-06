package Domain;
import java.io.IOException;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Room implements Serializable{
    private final String roomNumber;
    private final List<TimeSlot> timeSlots;


    public Room(String roomNumber){
        this.roomNumber = roomNumber;
        timeSlots = new LinkedList<>();
    }


    public List<List<TimeSlot>> addTimeSlots(List<TimeSlot> list){
        List<TimeSlot> failedInsertion = new LinkedList<>();
        List<TimeSlot> sucessInsertion = new LinkedList<>();
        synchronized(timeSlots){
            for(TimeSlot slot : list)
                if(!insert(slot)) failedInsertion.add(slot);
                else sucessInsertion.add(slot);
        }

        List<List<TimeSlot>> ret = new LinkedList<>();
        ret.add(failedInsertion);
        ret.add(sucessInsertion);

        Collections.sort(timeSlots);

        return ret;
    }


    public List<List<TimeSlot>> removeTimeSlots(List<TimeSlot> list){
        if(list.size() == 0) return null;
        int index = -1;
        List<List<TimeSlot>> ret = new LinkedList<>();
        List<TimeSlot> deletedSlots = new LinkedList<>();
        List<TimeSlot> notDeletedSlots = new LinkedList<>();
        synchronized(timeSlots){
            for(TimeSlot delSlot : list){
                for(TimeSlot currSlot : timeSlots){
                    if(currSlot.getStartMilli() == delSlot.getStartMilli()
                            && currSlot.getEndMilli() == delSlot.getEndMilli()){
                        index = timeSlots.indexOf(currSlot);
                        /*
                         * if student has booked this room, it will be deleted
                         */
                        if (currSlot.getStudentID() != null) {
                            CampusName studentCampus = currSlot.getStudentCampus();
                            int student_id = currSlot.getStudentID();
                            DatagramSocket socket;
                            try {
                                Calendar calendar = (Calendar) currSlot.getStartTime().clone();
                                calendar.set(Calendar.HOUR, 0);
                                calendar.set(Calendar.MINUTE, 0);
                                calendar.set(Calendar.SECOND, 0);
                                calendar.set(Calendar.MILLISECOND, 0);
                                calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
                                String message = "**remove-" + calendar.getTimeInMillis() + "-" + student_id;
                                System.out.println(calendar.getTime() + " " + calendar.getTimeInMillis());
                                byte[] messageByte = message.getBytes();
                                socket = new DatagramSocket();
                                InetAddress address = InetAddress.getByName("localhost");
                                DatagramPacket request = new DatagramPacket(messageByte, message.length(), address, studentCampus.inPort);
                                socket.send(request);

                                currSlot.cancelBooking();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                if(index != -1){
                    deletedSlots.add(timeSlots.remove(index));
                    System.err.println(delSlot.toString() + " has been deleted");
                }else notDeletedSlots.add(delSlot);
                index = -1;
            }
        }
        Collections.sort(timeSlots);
        ret.add(notDeletedSlots);
        ret.add(deletedSlots);
        return ret;
    }


    private boolean insert(TimeSlot slot){
        if(timeSlots.size() == 0){
            timeSlots.add(slot);
            return true;
        }
        for(int i = 0; i < timeSlots.size(); ++i){
            TimeSlot get = timeSlots.get(i);
            //time slots are sorted
            if(slot.getStartMilli() < get.getStartMilli()){
                if(slot.getEndMilli() < get.getStartMilli()){
                    //add in front
                    timeSlots.add(slot);
                    return true;
                }else{
                    //combine, ignored the new end time in this case
                    get.setStartTime(slot.getStartTime());
                    if(get.getStudentID() != null){
                        get.cancelBooking();//student's counter reduces in the method call
                    }
                    return true;
                }
            }else if(slot.getStartMilli() <= get.getEndMilli()){
                if(slot.getEndMilli() > get.getEndMilli()){
                    if(i == timeSlots.size() - 1){//last one in the current list
                        get.setEndTime(slot.getEndTime());
                        return true;
                    }else{
                        TimeSlot nextOne = timeSlots.get(i + 1);//next one
                        if(slot.getEndMilli() < nextOne.getStartMilli()){
                            get.setEndTime(slot.getEndTime());
                            if(get.getStudentID() != null) get.cancelBooking();
                            return true;
                        }else{
                            Calendar end = (Calendar) nextOne.getStartTime().clone();
                            end.add(Calendar.MINUTE, -1);
                            get.setEndTime(end);
                            return true;
                        }
                    }
                }else if(slot.getStartMilli() >= get.getStartMilli() &&
                        slot.getEndMilli() <= get.getStartMilli()){
                    //do nothing, this new slot is in the current one
                    return true;
                }
            }else{//new slot start time is after current slot's end time

                if(i == timeSlots.size() - 1){//i am at the last one, insert at the end
                    timeSlots.add(slot);
                    return true;
                }//else go to next slot in list
            }
        }
        return false;
    }

    public String getRoomNumber(){
        return roomNumber;
    }

    public List<TimeSlot> getTimeSlots(){
        synchronized(timeSlots){
            return timeSlots;
        }
    }


}
