package GUI;

public class Start {

    public static void main(String[] args) {
        GUI board = new GUI();
        board.setBoard();
        board.setPieces();
        board.setSize(500, 500);
    }
}
