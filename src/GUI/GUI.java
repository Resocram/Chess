package GUI;

import Pieces.Pawn;
import Pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    private JButton[][] grid;
    private Object[][] piece;
    private JFrame frame;

    public GUI() {
        this.piece = new Object[8][8];
        this.grid = new JButton[8][8];
        this.frame = new JFrame("Chess");
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

    private void setKings(){
        ImageIcon blackKing = new ImageIcon("./local/Black King.png");
        ImageIcon whiteKing = new ImageIcon("./local/White King.png");
        grid[0][4].setIcon(blackKing);
        grid[7][4].setIcon(whiteKing);
    }

    private void setQueens(){
        ImageIcon blackQueen = new ImageIcon("./local/Black Queen.png");
        ImageIcon whiteQueen = new ImageIcon("./local/White Queen.png");
        grid[0][3].setIcon(blackQueen);
        grid[7][3].setIcon(whiteQueen);
    }

    private void setBishops(){
        ImageIcon blackBishop = new ImageIcon("./local/Black Bishop.png");
        ImageIcon whiteBishop = new ImageIcon("./local/White Bishop.png");
        grid[0][2].setIcon(blackBishop);
        grid[0][5].setIcon(blackBishop);
        grid[7][2].setIcon(whiteBishop);
        grid[7][5].setIcon(whiteBishop);
    }
    private void setRooks(){
        ImageIcon blackRook = new ImageIcon("./local/Black Rook.png");
        ImageIcon whiteRook = new ImageIcon("./local/White Rook.png");
        grid[0][0].setIcon(blackRook);
        grid[0][7].setIcon(blackRook);
        grid[7][0].setIcon(whiteRook);
        grid[7][7].setIcon(whiteRook);

    }

    private void setKnights(){
        ImageIcon blackKnight = new ImageIcon("./local/Black Knight.png");
        ImageIcon whiteKnight = new ImageIcon("./local/White Knight.png");
        grid[0][1].setIcon(blackKnight);
        grid[0][6].setIcon(blackKnight);
        grid[7][1].setIcon(whiteKnight);
        grid[7][6].setIcon(whiteKnight);
    }

    private void setPawns() {
        ImageIcon blackPawn = new ImageIcon("./local/Black Pawn.png");
        ImageIcon whitePawn = new ImageIcon("./local/White Pawn.png");
        for(int i =0; i<8;i++){
            grid[1][i].setIcon(blackPawn);
            grid[6][i].setIcon(whitePawn);
        }
    }

    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (source == grid[i][j]) {
                        System.out.println(i + "" + j);
                        return;
                    }
                }
            }
        }
    }


}
