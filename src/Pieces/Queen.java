package Pieces;

import GUI.Player;

public class Queen extends Piece {
    public Queen(int x, int y, Player player) {
        super(x, y, player);
    }

    @Override
    public boolean validMove(int finalX, int finalY, Object[][] pieces) {
        Piece toMove = (Piece) pieces[finalX][finalY];
        int distanceX = Math.abs(finalX - this.x);
        int distanceY = Math.abs(finalY - this.y);
        if ((Math.abs(distanceX) == Math.abs(distanceY)) && distanceX != 0) {
            if (!(detectDiagonalCollision(finalX, finalY, pieces))) {
                if (toMove == null) {
                    return true;
                } else {
                    return !(toMove.player.equals(this.player));
                }
            } else {
                return false;
            }
        } else if (distanceX == 0 && distanceY != 0) {
            if (!detectXCollision(finalX, finalY, pieces)) {
                if (toMove == null) {
                    return true;
                } else {
                    return !(toMove.player.equals(this.player));
                }
            } else {
                return false;
            }
        } else if (distanceY == 0 && distanceX != 0) {
            if (!detectYCollision(finalX, finalY, pieces)) {
                if (toMove == null) {
                    return true;
                } else {
                    return !(toMove.player.equals(this.player));
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "queen";
    }
}
