import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    private JButton[][] grid;
    private JFrame frame;

    public GUI() {
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

    public void setPieces() {
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

    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (source == grid[i][j]) {
                        System.out.println(source);
                        return;
                    }
                }
            }
        }
    }


}
