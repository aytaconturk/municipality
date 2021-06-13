package com.example.userregistration;

import java.util.ArrayList;

public class ListDetails {

    public static ArrayList<Model> getList(){

        ArrayList<Model> problemList = new ArrayList<>();

        problemList.add(new Model("title1", "description1"));
        problemList.add(new Model("title2", "description2"));
        problemList.add(new Model("title3", "description3"));
        problemList.add(new Model("title4", "description4"));
        problemList.add(new Model("title5", "description5"));

        return problemList;
    }
}
