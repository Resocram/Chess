package Pieces;

import GUI.Player;

public class Queen extends Piece {
    public Queen(int x, int y, Player player) {
        super(x, y, player);
    }

    @Override
    public boolean validMove(int finalX, int finalY, Object[][] pieces) {
        return false;
    }

    @Override
    public String toString() {
        return "queen";
    }
}
