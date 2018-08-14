package com.xcodesystemsinterns.studentmanager;

/**
 * Created by Omar on 7/30/2018.
 */
public class DataModel {

    String name;
    String type;
    String rate;


    public DataModel(String name, String type, String rate ) {
        this.name=name;
        this.type=type;
        this.rate=rate;


    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getVersion_number() {
        return rate;
    }



}
