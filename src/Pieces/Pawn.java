package Pieces;

import GUI.Player;

public class Pawn extends Piece {

    private String id;
    private int x;
    private int y;

    public Pawn(int x, int y, Player player) {
        super(x, y, player);
    }


    public boolean validMove(int finalX, int finalY) {
        if (hasNotMoved()) {
            return this.x - finalX <= 2 && this.y == finalY;
        } else {
            return this.x - finalX <= 1 && this.y == finalY;
        }

    }

    private boolean hasNotMoved() {
        if (this.player.getID() == 0 && this.x == 6) {
            return true;
        } else return this.player.getID() == 1 && this.x == 1;
    }

    public String toString(){
        return "pawn";
    }


}
