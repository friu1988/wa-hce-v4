/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capa.presentacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player implements Serializable {

    private String name;

    private int number;

    private String country;

    private List<PlayerDetails> details = new ArrayList<PlayerDetails>();

    public Player() {
    }

    public Player(String name) {
        this.name = name;
    }

    public Player(String name, int number, String country) {
        this.name = name;
        this.number = number;
        this.country = country;
    }

    public Player(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<PlayerDetails> getDetails() {
        return details;
    }

    public void setDetails(List<PlayerDetails> details) {
        this.details = details;
    }

    public int getTotalRuns() {
        int sum = 0;

        for (PlayerDetails d : details) {
            sum += d.getRuns();
        }

        return sum;
    }

    public int getTotalWickets() {
        int sum = 0;

        for (PlayerDetails d : details) {
            sum += d.getWickets();
        }

        return sum;
    }

    public int getTotalCatches() {
        int sum = 0;

        for (PlayerDetails d : details) {
            sum += d.getCatches();
        }

        return sum;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Player)) {
            return false;
        }

        return ((Player) obj).getNumber() == this.number;
    }

    @Override
    public int hashCode() {
        int hash = 1;

        return hash * 31 + name.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }
}

