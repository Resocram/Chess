package Pieces;

import GUI.Player;


public class Knight extends Piece {
    public Knight(int x, int y, Player player) {
        super(x, y, player);
    }

    @Override
    public boolean validMove(int finalX, int finalY, Object[][] pieces) {
        Piece toMove = (Piece) pieces[finalX][finalY];
        int distanceX = Math.abs(finalX - this.x);
        int distanceY = Math.abs(finalY - this.y);
        if ((distanceX == 1 && distanceY == 2) || (distanceX == 2 && distanceY == 1)) {
            if (toMove == null) {
                return true;
            } else {
                return !(toMove.player == this.player);
            }
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "knight";
    }
}
