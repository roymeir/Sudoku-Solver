import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;

public class Board {
    public static void createBoard(int[][] puzzle) {
        final Border fieldBorder = BorderFactory.createLineBorder(Color.BLACK);
        
        final JPanel grid = new JPanel(new GridLayout(9, 2));
        for (int i=0; i < 9; i++){
            for (int j=0; j < 9; j++){
                final JTextField field = new JTextField(2);
                field.setText(puzzle[i][j]+"");
                field.setHorizontalAlignment(JTextField.CENTER); //Center text horizontally in the text field.
                field.setBorder(fieldBorder); //Add the colored border.
                grid.add(field);
            }
        }
        
        final JPanel centeredGrid = new JPanel(new GridBagLayout());
        centeredGrid.add(grid);
        
        final JFrame frame = new JFrame("Sudoku");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(centeredGrid);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
    
    public static void main(final String[] args) {
        int [][] puzzle = {
            {1,2,3,4,5,6,7,8,9},
            {1,2,3,4,5,6,7,8,9},
            {1,2,3,4,5,6,7,8,9},
            {1,2,3,4,5,6,7,8,9},
            {1,2,3,4,5,6,7,8,9},
            {1,2,3,4,5,6,7,8,9},
            {1,2,3,4,5,6,7,8,9},
            {1,2,3,4,5,6,7,8,9},
            {1,2,3,4,5,6,7,8,9}
        };
        createBoard(puzzle);
    }
}