/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capa.presentacion;

import java.io.Serializable;

/**
 *
 * @author FREDDY
 */
public class PlayerDetails implements Serializable {

    private String year;

    private int runs;

    private int wickets;

    private int catches;

    public PlayerDetails() {
    }

    public PlayerDetails(String year, int runs, int wickets, int catches) {
        this.year = year;
        this.runs = runs;
        this.wickets = wickets;
        this.catches = catches;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getRuns() {
        return runs;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public int getWickets() {
        return wickets;
    }

    public void setWickets(int wickets) {
        this.wickets = wickets;
    }

    public int getCatches() {
        return catches;
    }

    public void setCatches(int catches) {
        this.catches = catches;
    }

}
