package Pieces;

import GUI.Player;

public class Bishop extends Piece {
    public Bishop(int x, int y, Player player) {
        super(x, y, player);
    }

    @Override
    public boolean validMove(int finalX, int finalY, Object[][] pieces) {
        Piece toMove = (Piece) pieces[finalX][finalY];
        int distanceX = Math.abs(finalX-this.x);
        int distanceY = Math.abs(finalY-this.y);
        if((Math.abs(distanceX)==Math.abs(distanceY)) && distanceX!=0){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "bishop";
    }
}
