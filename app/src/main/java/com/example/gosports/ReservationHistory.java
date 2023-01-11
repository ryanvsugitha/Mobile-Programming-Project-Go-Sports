package com.example.gosports;

public class ReservationHistory {

    private int userID;
    private String arenaName;
    private String arenaDate;
    private String arenaTime;

    public ReservationHistory(int userID, String arenaName, String arenaDate, String arenaTime) {
        this.userID = userID;
        this.arenaName = arenaName;
        this.arenaDate = arenaDate;
        this.arenaTime = arenaTime;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getArenaName() {
        return arenaName;
    }

    public void setArenaName(String arenaName) {
        this.arenaName = arenaName;
    }

    public String getArenaDate() {
        return arenaDate;
    }

    public void setArenaDate(String arenaDate) {
        this.arenaDate = arenaDate;
    }

    public String getArenaTime() {
        return arenaTime;
    }

    public void setArenaTime(String arenaTime) {
        this.arenaTime = arenaTime;
    }
}
