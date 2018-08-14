package com.xcodesystemsinterns.studentmanager;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> Class1 = new ArrayList<String>();
        Class1.add("Assignment1: ");
        Class1.add("Assignment2:");
        Class1.add("Assignment3: ");
        Class1.add("Assignment3: ");


        List<String> Class2 = new ArrayList<String>();
        Class2.add("Assignment1: ");
        Class2.add("Assignment2:");
        Class2.add("Assignment3: ");
        Class2.add("Assignment3: ");

        List<String> Class3 = new ArrayList<String>();
        Class3.add("Assignment1: ");
        Class3.add("Assignment2:");
        Class3.add("Assignment3: ");
        Class3.add("Assignment3: ");

        expandableListDetail.put("Class 1", Class1);
        expandableListDetail.put("Class 2", Class2);
        expandableListDetail.put("Class 3", Class2);
        return expandableListDetail;
    }
}
