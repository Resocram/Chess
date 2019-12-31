package Pieces;

import GUI.Player;

public class Rook extends Piece {
    public Rook(int x, int y, Player player) {
        super(x, y, player);
    }

    @Override
    public boolean validMove(int finalX, int finalY, Object[][] pieces) {
        Piece toMove = (Piece) pieces[finalX][finalY];
        int distanceX = Math.abs(finalX-this.x);
        int distanceY = Math.abs(finalY-this.y);
        if(distanceX == 0 && distanceY != 0 ){
            if(!detectCollisionX(finalX,finalY,pieces)){
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
        }
        else if(distanceY == 0 && distanceX !=0){
            if(!detectCollisionY(finalX,finalY,pieces)){
                if(toMove ==null){
                    return true;
                }
                else {
                    return !(toMove.player.equals(this.player));
                }
            }else{
                return false;
            }
        }
        else{
            return false;
        }
    }
    private boolean detectCollisionX(int finalX, int finalY, Object[][] pieces){
        int small = finalY;
        int large = this.y;
        if(small>large){
            small=this.y;
            large=finalY;
        }
        small++;
        while(small<large){
            if(!(pieces[finalX][small] == null)){
                return true;
            }
            small++;
        }
        return false;
    }

    private boolean detectCollisionY(int finalX, int finalY, Object[][] pieces){
        int small = finalX;
        int large = this.x;
        if(small>large){
            small=this.x;
            large=finalX;
        }
        small++;
        while(small<large){
            if(!(pieces[small][finalY] == null)){
                return true;
            }
            small++;
        }
        return false;
    }

    @Override
    public String toString() {
        return "rook";
    }
}
