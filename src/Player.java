/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class Player {
    private String who;
    private String marker;

    public Player(String who) {
        this.who = who;
        if(this.who.equals("cpu")){
            this.marker = "X";
        }
        if(this.who.equals("human")){
            this.marker = "O";
        }
    }

    public String getWho() {
        return who;
    }

    public String getMarker() {
        return marker;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }
    
    
    
}
