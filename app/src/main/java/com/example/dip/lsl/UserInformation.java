package com.example.dip.lsl;

public class UserInformation {
    public String name;
    public String uid;
    public String level;

    UserInformation(){

    }
    UserInformation(String name){
        this.name=name;
    }

    UserInformation(String name, String uid ,String level ){
        this.name=name;
        this.uid=uid;
        this.level=level;

    }

    public String getName(){
        return name;
    }
    public String getUid(){
        return uid;
    }
    public String getLevel(){
        return level;
    }
}
