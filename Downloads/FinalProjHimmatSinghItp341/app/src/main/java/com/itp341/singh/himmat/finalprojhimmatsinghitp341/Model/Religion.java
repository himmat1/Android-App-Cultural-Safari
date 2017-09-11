package com.itp341.singh.himmat.finalprojhimmatsinghitp341.Model;

import java.io.Serializable;

/**
 * Created by himmatsingh on 4/28/17.
 */

public class Religion implements Serializable{


    private String name;
    private String origin;
    private String prophets;
    private String beliefInGod;
    private String temples;
    private String url;
    private String holyBook;
    private String[] friends;

    public Religion(){

    }

    public String getUrl() {return url;}

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getProphets() {
        return prophets;
    }

    public void setProphets(String prophets) {
        this.prophets = prophets;
    }

    public String getBeliefInGod() {
        return beliefInGod;
    }

    public void setBeliefInGod(String beliefInGod) {
        this.beliefInGod = beliefInGod;
    }

    public String getHolyBook() {return holyBook;}

    public void setHolyBook(String holyBook) {this.holyBook = holyBook;}

    public String getTemples() {
        return temples;
    }

    public void setTemples(String temples) {
        this.temples = temples;
    }

    public String[] getFriends() {return friends;}

    public void setFriends(String[] friends) {this.friends = friends;}

}
