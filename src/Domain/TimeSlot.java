package Domain;


import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import lombok.Getter;

import java.io.Serializable;
import java.util.Calendar;


public class TimeSlot implements Serializable, Comparable<TimeSlot>{
    @Getter
    public Calendar startTime;
    @Getter
    public Calendar endTime;
    @Getter
    public Integer studentID = null;
    @Getter
    public CampusName studentCampus = null;
    @Getter
    public String bookingID = null;


    @Expose
    public String start;
    @Expose
    public String finish;


    public TimeSlot(Calendar startTime, Calendar endTime){
        this.startTime = startTime;
        this.endTime = endTime;
        this.start = Format.formatTime(startTime);
        this.finish = Format.formatTime(endTime);
    }



    public void setEndTime(Calendar endTime){
        this.endTime = endTime;
        this.finish = Format.formatTime(endTime);
    }

    public long getStartMilli(){
        return startTime.getTimeInMillis();
    }


    public long getEndMilli(){
        return endTime.getTimeInMillis();
    }


    public void setStartTime(Calendar startTime){
        this.startTime = startTime;
        this.start = Format.formatTime(startTime);
    }

    public void setStudentID(CampusName campusOfID, Integer studentID, String bookingID){
        this.studentID = studentID;
        this.studentCampus = campusOfID;
        this.bookingID = bookingID;
    }

    public void cancelBooking(){
        //TODO reduce student booking count
        this.studentID = null;
        this.bookingID = null;
        this.studentCampus = null;
    }


    @Override
    public int compareTo(TimeSlot o){
        return (int) (this.startTime.getTimeInMillis() - o.startTime.getTimeInMillis());
    }

    @Override
    public boolean equals(Object obj){
        return this.startTime.equals(((TimeSlot) obj).startTime)
                && this.endTime.equals(((TimeSlot) obj).endTime);
    }

    public String toString(){
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create()
                .toJson(this, TimeSlot.class);
    }





}
