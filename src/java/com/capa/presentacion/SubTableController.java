/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capa.presentacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author FREDDY
 */
@ManagedBean(name = "subTableController")
@ViewScoped
public class SubTableController implements Serializable {

    private List<Player> players;

    public SubTableController() {
        populatePlayers();
    }

    public void populatePlayers() {
        players = new ArrayList<Player>();

        Player sachin = new Player("Sachin", 10, "India");
        sachin.getDetails().add(new PlayerDetails("2001", 800, 7, 12));
        sachin.getDetails().add(new PlayerDetails("2002", 933, 13, 11));
        sachin.getDetails().add(new PlayerDetails("2003", 900, 10, 33));
        sachin.getDetails().add(new PlayerDetails("2004", 600, 15, 8));
        sachin.getDetails().add(new PlayerDetails("2005", 1000, 22, 3));
        sachin.getDetails().add(new PlayerDetails("2006", 700, 5, 9));
        players.add(sachin);

        Player ponting = new Player("Ponting", 6, "Australia");
        ponting.getDetails().add(new PlayerDetails("2001", 900, 5, 11));
        ponting.getDetails().add(new PlayerDetails("2002", 600, 2, 13));
        ponting.getDetails().add(new PlayerDetails("2003", 700, 13, 9));
        ponting.getDetails().add(new PlayerDetails("2004", 907, 9, 8));
        ponting.getDetails().add(new PlayerDetails("2005", 988, 3, 20));
        ponting.getDetails().add(new PlayerDetails("2006", 400, 6, 16));
        players.add(ponting);

        Player lara = new Player("Lara", 10, "Trinidad");
        lara.getDetails().add(new PlayerDetails("2001", 600, 12, 22));
        lara.getDetails().add(new PlayerDetails("2002", 700, 9, 17));
        lara.getDetails().add(new PlayerDetails("2003", 999, 14, 9));
        lara.getDetails().add(new PlayerDetails("2004", 600, 7, 2));
        lara.getDetails().add(new PlayerDetails("2005", 888, 6, 5));
        lara.getDetails().add(new PlayerDetails("2006", 700, 22, 9));
        players.add(lara);
    }

    public List<Player> getPlayers() {
        return players;
    }

}
