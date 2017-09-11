package com.itp341.singh.himmat.finalprojhimmatsinghitp341.Model;

import android.content.Context;

import com.itp341.singh.himmat.finalprojhimmatsinghitp341.R;

import java.util.ArrayList;

/**
 * Created by himmatsingh on 4/28/17.
 */

public class ReligionSingleton {

    //TODO instance variables
    private ArrayList<Religion> religions;
    private Context context;

    private String[] religionNames = {};
    private String[] religionDescriptions = {};
    private String[] religionURLs = {};
    private String[] religionProphets = {};
    private String[] religionGod = {};
    private String[] religionBook = {};
    private String[] religiousFriends = {};


    //store a static reference to the singleton
    private static ReligionSingleton singleton;


    //TODO private singleton constructor
    private ReligionSingleton(Context c) {
        context = c;
        //instantiate the data
        religions = new ArrayList<>();

        //add dummy data
        religionNames = c.getResources().getStringArray(R.array.religions);
        religionDescriptions = c.getResources().getStringArray(R.array.origins);
        religionURLs = c.getResources().getStringArray(R.array.urls);
        religionProphets = c.getResources().getStringArray(R.array.prophets);
        religionGod = c.getResources().getStringArray(R.array.belief);
        religionBook = c.getResources().getStringArray(R.array.book);
        religiousFriends = c.getResources().getStringArray(R.array.christ_friends);

        for(int i = 0; i<5; i++){
            Religion r = new Religion();
            r.setName(religionNames[i]);
            r.setOrigin(religionDescriptions[i]);
            r.setUrl(religionURLs[i]);
            r.setProphets(religionProphets[i]);
            r.setBeliefInGod(religionGod[i]);
            r.setHolyBook(religionBook[i]);
            if(i==0){r.setFriends(religiousFriends);}
            else if(i ==1){
                religiousFriends = c.getResources().getStringArray(R.array.islam_friends);
                r.setFriends(religiousFriends);
            }
            else if(i==2){
                religiousFriends = c.getResources().getStringArray(R.array.hindu_friends);
                r.setFriends(religiousFriends);
            }
            else if(i==3){
                religiousFriends = c.getResources().getStringArray(R.array.bud_friends);
                r.setFriends(religiousFriends);
            }
            else if(i==4){
                religiousFriends = c.getResources().getStringArray(R.array.sikh_friends);
                r.setFriends(religiousFriends);
            }
            religions.add(r);
        }


    }


    //TODO Singleton get method
    public static ReligionSingleton get(Context c) {
        //only one instance ever is created
        if (singleton == null) { //we have never created a singleton
            singleton = new ReligionSingleton(c.getApplicationContext());
        }
        return singleton;
    }


    //TODO getCoffeeShops (all items)
    public ArrayList<Religion> getReligions() {
        return religions;
    }


    //TODO getCoffeeShop (single item)
    public Religion getReligion(int position) {
        return religions.get(position);
    }


    //TODO addCoffeeShop
    public void addReligion(Religion cs) {
        religions.add(cs);
    }


    //TODO removeCoffeeShop
    public void removeReligion(int position) {
        if(position >=0 && position < religions.size()) {
            religions.remove(position);
        }
    }

    //TODO updateCoffeeShop
    public void updateReligion(Religion cs, int position) {
        if (position >= 0 && position < religions.size()) {
            religions.set(position, cs);
        }
    }
}
