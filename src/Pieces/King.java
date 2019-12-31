package Pieces;

import GUI.Player;

public class King extends Piece {
    public King(int x, int y, Player player) {
        super(x, y, player);
    }

    @Override
    public boolean validMove(int finalX, int finalY, Object[][] pieces) {
        return false;
    }

    @Override
    public String toString() {
        return null;
    }
}
