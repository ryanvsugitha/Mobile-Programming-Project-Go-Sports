package com.example.gosports;

public class MyListData {

    private String arenaName;
    private int arenaImg;
    private String arenaDesc;
    private int userID;

    public MyListData(String arenaName, int arenaImg, String arenaDesc, int userID){
        this.arenaName = arenaName;
        this.arenaImg = arenaImg;
        this.arenaDesc = arenaDesc;
        this.userID = userID;
    }

    public String getArenaName() {
        return arenaName;
    }

    public void setArenaName(String arenaName) {
        this.arenaName = arenaName;
    }

    public int getArenaImg() {
        return arenaImg;
    }

    public void setArenaImg(int arenaImg) {
        this.arenaImg = arenaImg;
    }

    public String getArenaDesc() {
        return arenaDesc;
    }

    public void setArenaDesc(String arenaDesc) {
        this.arenaDesc = arenaDesc;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
