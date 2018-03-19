/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class Move {
    private Player player;
    private int[] position;

    public Move(Player player, int[] position) {
        this.player = player;
        this.position = position;
    }
    
    public Player getPlayer() {
        return player;
    }

    public int[] getPosition() {
        return position;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Move{" + "player=" + player + ", position=" + position + '}';
    }
    
    
}
