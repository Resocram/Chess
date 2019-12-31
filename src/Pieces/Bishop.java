package Pieces;

import GUI.Player;

public class Bishop extends Piece {
    public Bishop(int x, int y, Player player) {
        super(x, y, player);
    }

    @Override
    public boolean validMove(int finalX, int finalY, Object[][] pieces) {
        Piece toMove = (Piece) pieces[finalX][finalY];
        int distanceX = Math.abs(finalX - this.x);
        int distanceY = Math.abs(finalY - this.y);
        if ((Math.abs(distanceX) == Math.abs(distanceY)) && distanceX != 0) {
            if(!(detectCollision(finalX,finalY,pieces))){
                if(toMove==null){
                    return true;
                }
                else{
                    return !(toMove.player.equals(this.player));
                }
            }
            else{
                return false;
            }
        }else{
            return false;
        }
    }

    private boolean detectCollision(int finalX, int finalY, Object[][] pieces) {
        boolean right = (finalY-this.y)> 0;
        boolean up = (this.x-finalX)>0;
        if(right&&up){
            int i = this.x-1;
            int j = this.y+1;
            while(i!=finalX && j!=finalY){
                if(!(pieces[i][j] == null)){
                    return true;
                }
                i--;
                j++;
            }
        }
        else if (right) {
            int i = this.x+1;
            int j = this.y+1;
            while(i!=finalX && j!=finalY){
                if(!(pieces[i][j] == null)){
                    return true;
                }
                i++;
                j++;
            }
        }
        else if(up){
            int i = this.x-1;
            int j = this.y-1;
            while(i!=finalX && j!=finalY){
                if(!((pieces[i][j])==null)){
                    return true;
                }
                i--;
                j--;
            }
        }
        else{
            int i = this.x+1;
            int j = this.y-1;
            while(i!=finalX && j!=finalY){
                if(!((pieces[i][j])==null)){
                    return true;
                }
                i++;
                j--;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "bishop";
    }
}
