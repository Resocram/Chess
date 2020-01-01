package Pieces;

import GUI.Player;

public abstract class Piece {
    public int x;
    public int y;
    public Player player;


    public Piece(int x, int y, Player player) {
        this.x = x;
        this.y = y;
        this.player = player;
    }

    public abstract boolean validMove(int finalX, int finalY, Object[][] pieces);

    boolean detectDiagonalCollision(int finalX, int finalY, Object[][] pieces) {
        boolean right = (finalY - this.y) > 0;
        boolean up = (this.x - finalX) > 0;
        if (right && up) {
            int i = this.x - 1;
            int j = this.y + 1;
            while (i != finalX && j != finalY) {
                if (!(pieces[i][j] == null)) {
                    return true;
                }
                i--;
                j++;
            }
        } else if (right) {
            int i = this.x + 1;
            int j = this.y + 1;
            while (i != finalX && j != finalY) {
                if (!(pieces[i][j] == null)) {
                    return true;
                }
                i++;
                j++;
            }
        } else if (up) {
            int i = this.x - 1;
            int j = this.y - 1;
            while (i != finalX && j != finalY) {
                if (!((pieces[i][j]) == null)) {
                    return true;
                }
                i--;
                j--;
            }
        } else {
            int i = this.x + 1;
            int j = this.y - 1;
            while (i != finalX && j != finalY) {
                if (!((pieces[i][j]) == null)) {
                    return true;
                }
                i++;
                j--;
            }
        }
        return false;
    }

    boolean detectXCollision(int finalX, int finalY, Object[][] pieces) {
        int small = finalY;
        int large = this.y;
        if (small > large) {
            small = this.y;
            large = finalY;
        }
        small++;
        while (small < large) {
            if (!(pieces[finalX][small] == null)) {
                return true;
            }
            small++;
        }
        return false;
    }

    boolean detectYCollision(int finalX, int finalY, Object[][] pieces) {
        int small = finalX;
        int large = this.x;
        if (small > large) {
            small = this.x;
            large = finalX;
        }
        small++;
        while (small < large) {
            if (!(pieces[small][finalY] == null)) {
                return true;
            }
            small++;
        }
        return false;
    }


    public abstract String toString();


}
