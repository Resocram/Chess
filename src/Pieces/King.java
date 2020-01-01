package Pieces;

import GUI.Player;

public class King extends Piece {
    public King(int x, int y, Player player) {
        super(x, y, player);
    }

    @Override
    public boolean validMove(int finalX, int finalY, Object[][] pieces) {
        Piece toMove = (Piece) pieces[finalX][finalY];
        int distanceX = Math.abs(finalX - this.x);
        int distanceY = Math.abs(finalY - this.y);
        if(distanceX<=1 && distanceY<=1 && !(distanceX==0 && distanceY==0)){
            if (toMove == null) {
                return true;
            } else {
                return !(toMove.player.equals(this.player));
            }
        }
        else{
            return false;
        }

    }

    @Override
    public String toString() {
        return "king";
    }
}
