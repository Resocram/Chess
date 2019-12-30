package Pieces;
import GUI.Player;

public abstract class Piece {
    int x;
    int y;
    Player player;


    public Piece(int x, int y, Player player) {
        this.x=x;
        this.y=y;
        this.player= player;
    }

    public abstract boolean validMove(int finalX, int finalY);

    public Player player() {
        return this.player;
    }

}
