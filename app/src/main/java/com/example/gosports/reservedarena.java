package com.example.gosports;

public class reservedarena {

    int bookerID;
    String arenaname;
    String date;
    String time;

    public reservedarena(){

    }

    public reservedarena(int bookerID, String arenaname, String date, String time) {
        this.bookerID = bookerID;
        this.arenaname = arenaname;
        this.date = date;
        this.time = time;
    }

    public String getArenaname() {
        return arenaname;
    }

    public void setArenaname(String arenaname) {
        this.arenaname = arenaname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getBookerID() {
        return bookerID;
    }

    public void setBookerID(int bookerID) {
        this.bookerID = bookerID;
    }
}
