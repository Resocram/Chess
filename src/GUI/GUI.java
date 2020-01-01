package GUI;

import Pieces.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    private JButton[][] grid;
    private Object[][] pieces;
    private JFrame frame;
    private Player player1; //White
    private Player player2; //Black
    private Player turn;
    private int[] moveQueue;

    public GUI() {
        this.pieces = new Object[8][8];
        this.grid = new JButton[8][8];
        this.frame = new JFrame("Chess");
        this.player1 = new Player(0);
        this.player2 = new Player(1);
        this.turn = player1;
        this.moveQueue = new int[2];
        moveQueue[0] = -1;
        moveQueue[1] = -1;

    }

    public void setSize(int x, int y) {
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(x, y);
        frame.setLayout(new GridLayout(8, 8));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void setBoard() {
        ButtonHandler buttonHandler = new ButtonHandler();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                grid[i][j] = new JButton();
                frame.add(grid[i][j]);
                if ((i + j) % 2 != 0) {
                    grid[i][j].setBackground(Color.DARK_GRAY);
                }
                grid[i][j].addActionListener(buttonHandler);

            }
        }
    }

    public void setPieces() {
        setPawns();
        setKnights();
        setRooks();
        setBishops();
        setQueens();
        setKings();

    }

    private void setKings() {
        ImageIcon blackKing = new ImageIcon("./local/Black King.png");
        ImageIcon whiteKing = new ImageIcon("./local/White King.png");
        pieces[0][4] = new King(0, 4, player2);
        pieces[7][4] = new King(7, 4, player1);
        grid[0][4].setIcon(blackKing);
        grid[7][4].setIcon(whiteKing);
    }

    private void setQueens() {
        ImageIcon blackQueen = new ImageIcon("./local/Black Queen.png");
        ImageIcon whiteQueen = new ImageIcon("./local/White Queen.png");
        pieces[0][3] = new Queen(0, 3, player2);
        pieces[7][3] = new Queen(7, 3, player1);
        grid[0][3].setIcon(blackQueen);
        grid[7][3].setIcon(whiteQueen);
    }

    private void setBishops() {
        ImageIcon blackBishop = new ImageIcon("./local/Black Bishop.png");
        ImageIcon whiteBishop = new ImageIcon("./local/White Bishop.png");
        pieces[0][2] = new Bishop(0, 2, player2);
        pieces[0][5] = new Bishop(0, 5, player2);
        pieces[7][2] = new Bishop(7, 2, player1);
        pieces[7][5] = new Bishop(7, 5, player1);
        grid[0][2].setIcon(blackBishop);
        grid[0][5].setIcon(blackBishop);
        grid[7][2].setIcon(whiteBishop);
        grid[7][5].setIcon(whiteBishop);
    }

    private void setRooks() {
        ImageIcon blackRook = new ImageIcon("./local/Black Rook.png");
        ImageIcon whiteRook = new ImageIcon("./local/White Rook.png");
        pieces[0][0] = new Rook(0, 0, player2);
        pieces[0][7] = new Rook(0, 7, player2);
        pieces[7][0] = new Rook(7, 0, player1);
        pieces[7][7] = new Rook(7, 7, player1);
        grid[0][0].setIcon(blackRook);
        grid[0][7].setIcon(blackRook);
        grid[7][0].setIcon(whiteRook);
        grid[7][7].setIcon(whiteRook);

    }

    private void setKnights() {
        ImageIcon blackKnight = new ImageIcon("./local/Black Knight.png");
        ImageIcon whiteKnight = new ImageIcon("./local/White Knight.png");
        pieces[0][1] = new Knight(0, 1, player2);
        pieces[0][6] = new Knight(0, 6, player2);
        pieces[7][1] = new Knight(7, 1, player1);
        pieces[7][6] = new Knight(7, 6, player1);
        grid[0][1].setIcon(blackKnight);
        grid[0][6].setIcon(blackKnight);
        grid[7][1].setIcon(whiteKnight);
        grid[7][6].setIcon(whiteKnight);
    }

    private void setPawns() {
        ImageIcon blackPawn = new ImageIcon("./local/Black Pawn.png");
        ImageIcon whitePawn = new ImageIcon("./local/White Pawn.png");
        for (int i = 0; i < 8; i++) {
            pieces[1][i] = new Pawn(1, i, player2);
            pieces[6][i] = new Pawn(6, i, player1);
            grid[1][i].setIcon(blackPawn);
            grid[6][i].setIcon(whitePawn);
        }
    }

    private void performAction(int clickedI, int clickedJ) {
        //No clicked piece
        if (noMovedQueued()) {
            Piece toMove = getPiece(clickedI, clickedJ);
            if (!(toMove == null)) {
                if (checkIfPlayersPiece(toMove)) {
                    moveQueue[0] = clickedI;
                    moveQueue[1] = clickedJ;
                }
            }
        } else {
            Piece piece = getPiece(moveQueue[0], moveQueue[1]);
            int xKing = xCoordinateKing();
            int yKing = yCoordinateKing();
            boolean inCheck = inCheck(xKing, yKing, pieces);
            if (inCheck) {
                if (piece.validMove(clickedI, clickedJ, pieces)) {
                    Piece endPiece = pretendToMove(clickedI, clickedJ);
                    boolean stillInCheck = inCheck(xCoordinateKing(), yCoordinateKing(), pieces);
                    moveBack(clickedI, clickedJ, endPiece);
                    if (!stillInCheck) {
                        movePiece(clickedI, clickedJ);
                        switchPlayer();
                        moveQueue[0] = -1;
                        moveQueue[1] = -1;
                    }
                } else {
                    Piece toMove = getPiece(clickedI, clickedJ);
                    if (!(toMove == null)) {
                        if (checkIfPlayersPiece(toMove)) {
                            moveQueue[0] = clickedI;
                            moveQueue[1] = clickedJ;
                        }
                    }
                }

            } else {
                if (piece.validMove(clickedI, clickedJ, pieces)) {
                    Piece endPiece = pretendToMove(clickedI, clickedJ);
                    boolean getInCheck = inCheck(xCoordinateKing(), yCoordinateKing(), pieces);
                    moveBack(clickedI, clickedJ, endPiece);
                    if (!getInCheck) {
                        movePiece(clickedI, clickedJ);
                        switchPlayer();
                        moveQueue[0] = -1;
                        moveQueue[1] = -1;
                    }
                } else {
                    Piece toMove = getPiece(clickedI, clickedJ);
                    if (!(toMove == null)) {
                        if (checkIfPlayersPiece(toMove)) {
                            moveQueue[0] = clickedI;
                            moveQueue[1] = clickedJ;
                        }
                    }
                }
            }

        }
    }


    private boolean inCheck(int xKing, int yKing, Object[][] pieces) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = getPiece(i, j);
                if (piece != null) {
                    if (piece.validMove(xKing, yKing, pieces)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private int xCoordinateKing() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = getPiece(i, j);
                if (piece instanceof King && piece.player.equals(this.turn)) {
                    return i;
                }
            }
        }
        return -1;
    }

    private int yCoordinateKing() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = getPiece(i, j);
                if (piece instanceof King && piece.player.equals(this.turn)) {
                    return j;
                }
            }
        }
        return -1;
    }

    private Piece pretendToMove(int clickedI, int clickedJ) {

        Piece originalPiece = getPiece(moveQueue[0], moveQueue[1]);


        pieces[moveQueue[0]][moveQueue[1]] = null;
        pieces[clickedI][clickedJ] = originalPiece;

        return getPiece(moveQueue[0], moveQueue[1]);
    }

    private void moveBack(int clickedI, int clickedJ, Piece finalPiece) {
        Piece originalPiece = getPiece(clickedI, clickedJ);
        pieces[moveQueue[0]][moveQueue[1]] = originalPiece;
        pieces[clickedI][clickedJ] = finalPiece;

    }

    private Piece getPiece(int clickedI, int clickedJ) {
        return (Piece) pieces[clickedI][clickedJ];
    }

    private void switchPlayer() {
        if (this.turn.equals(player1)) {
            this.turn = player2;
        } else {
            this.turn = player1;
        }
    }

    private void movePiece(int clickedI, int clickedJ) {
        grid[moveQueue[0]][moveQueue[1]].setIcon(new ImageIcon("./local/Blank.png"));


        Piece piece = getPiece(moveQueue[0], moveQueue[1]);
        if (piece instanceof Pawn) {
            if (this.turn.getID() == 0) {
                grid[clickedI][clickedJ].setIcon(new ImageIcon("./local/White Pawn.png"));
            } else {
                grid[clickedI][clickedJ].setIcon(new ImageIcon("./local/Black Pawn.png"));
            }
            pieces[clickedI][clickedJ] = new Pawn(clickedI, clickedJ, this.turn);
        } else if (piece instanceof Knight) {
            if (this.turn.getID() == 0) {
                grid[clickedI][clickedJ].setIcon(new ImageIcon("./local/White Knight.png"));
            } else {
                grid[clickedI][clickedJ].setIcon(new ImageIcon("./local/Black Knight.png"));
            }
            pieces[clickedI][clickedJ] = new Knight(clickedI, clickedJ, this.turn);
        } else if (piece instanceof Rook) {
            if (this.turn.getID() == 0) {
                grid[clickedI][clickedJ].setIcon(new ImageIcon("./local/White Rook.png"));
            } else {
                grid[clickedI][clickedJ].setIcon(new ImageIcon("./local/Black Rook.png"));
            }
            pieces[clickedI][clickedJ] = new Rook(clickedI, clickedJ, this.turn);
        } else if (piece instanceof Bishop) {
            if (this.turn.getID() == 0) {
                grid[clickedI][clickedJ].setIcon(new ImageIcon("./local/White Bishop.png"));
            } else {
                grid[clickedI][clickedJ].setIcon(new ImageIcon("./local/Black Bishop.png"));
            }
            pieces[clickedI][clickedJ] = new Bishop(clickedI, clickedJ, this.turn);
        } else if (piece instanceof Queen) {
            if (this.turn.getID() == 0) {
                grid[clickedI][clickedJ].setIcon(new ImageIcon("./local/White Queen.png"));
            } else {
                grid[clickedI][clickedJ].setIcon(new ImageIcon("./local/Black Queen.png"));
            }
            pieces[clickedI][clickedJ] = new Queen(clickedI, clickedJ, this.turn);
        } else if (piece instanceof King) {
            if (this.turn.getID() == 0) {
                grid[clickedI][clickedJ].setIcon(new ImageIcon("./local/White King.png"));
            } else {
                grid[clickedI][clickedJ].setIcon(new ImageIcon("./local/Black King.png"));
            }
            pieces[clickedI][clickedJ] = new King(clickedI, clickedJ, this.turn);
        }

        pieces[moveQueue[0]][moveQueue[1]] = null;


    }

    private boolean noMovedQueued() {
        return moveQueue[0] == -1 && moveQueue[1] == -1;
    }

    private boolean checkIfPlayersPiece(Piece piece) {
        return piece.player.equals(this.turn);
    }

    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (source == grid[i][j]) {
                        performAction(i, j);
                        System.out.println(i + "" + j + pieces[i][j]);
                        return;
                    }
                }
            }
        }
    }


}
