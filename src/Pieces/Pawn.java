package Pieces;

import GUI.Player;

public class Pawn extends Piece {

    public Pawn(int x, int y, Player player) {
        super(x, y, player);
    }


    public boolean validMove(int finalX, int finalY, Object[][] pieces) {
        Piece toMove = (Piece) pieces[finalX][finalY];
        if(this.player.getID()==0){
            int distanceX = this.x - finalX;
            int distanceY = this.y - finalY;
            if (distanceX == 1 && distanceY == 0 && toMove ==null ) {
                return true;
            }
            else if(distanceX == 2 && distanceY == 0 && hasNotMoved() && toMove ==null ){
                return pieces[finalX+1][finalY] == null;
            }
            else if (distanceX == 1 && distanceY == -1 && !(toMove==null)){
                return !(toMove.player.getID() == this.player.getID());
            }
            else if (distanceX == 1 && distanceY == 1 && !(toMove==null)){
                return !(toMove.player.getID() == this.player.getID());
            }else{
                return false;
            }
        }
        else{
            int distanceX = finalX - this.x;
            int distanceY = finalY - this.y;
            if (distanceX == 1 && distanceY == 0 && toMove ==null ) {
                return true;
            }
            else if(distanceX == 2 && distanceY == 0 && hasNotMoved() && toMove ==null ){
                return pieces[finalX-1][finalY] == null;
            }
            else if (distanceX == 1 && distanceY == -1 && !(toMove==null)){
                return !(toMove.player.getID() == this.player.getID());
            }
            else if (distanceX == 1 && distanceY == 1 && !(toMove==null)){
                return !(toMove.player.getID() == this.player.getID());
            }else{
                return false;
            }
        }
    }

    private boolean hasNotMoved() {
        if (this.player.getID() == 0 && this.x == 6) {
            return true;
        } else return this.player.getID() == 1 && this.x == 1;
    }

    public String toString() {
        return "pawn";
    }


}
