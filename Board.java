import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Board {

    private static void createWindow() {    
        JFrame frame = new JFrame("Swing Tester");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createUI(frame);
        frame.setSize(560, 200);      
        frame.setLocationRelativeTo(null);  
        frame.setVisible(true);
     }
  
     private static void createUI(final JFrame frame){  
        JPanel panel = new JPanel();
        LayoutManager layout = new FlowLayout();  
        panel.setLayout(layout);       
  
        JButton button = new JButton("Play");
        final JLabel label = new JLabel();
        button.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
              String result = (String)JOptionPane.showInputDialog(
                 frame,
                 "Difficulty Glossary:\n\n Hard - 50/81 blank spaces\n Medium - 35/81 blank spaces\n Easy - 20/81 blank spaces\n\nChoose your desired difficulty:\n\tFor Hard, enter 1.\n\tFor Medium, press 2.\n\tFor Easy, press 3.\nIf your input doesn't match one of these digits, the board generated will be on easy mode.", 
                 "Swing Tester",            
                 JOptionPane.PLAIN_MESSAGE,
                 null,            
                 null, 
                 "1"
              );
              if(result != null && result.length() > 0){
                 label.setText("You selected:" + result);
              }else {
                 label.setText("None selected");
              }
           }
        });
  
        panel.add(button);
        panel.add(label);
        frame.getContentPane().add(panel, BorderLayout.CENTER);    
     }  

    public static void createBoard(int[][] puzzle) {
        final Border fieldBorder = BorderFactory.createLineBorder(Color.BLACK);

        final JPanel grid = new JPanel(new GridLayout(9, 2));
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                final JTextField field = new JTextField(2);
                field.setText(puzzle[i][j] + "");
                field.setHorizontalAlignment(JTextField.CENTER); //Center text horizontally in the text field.
                field.setBorder(fieldBorder); //Add the colored border.
                grid.add(field);
            }
        }

        final JPanel centeredGrid = new JPanel(new GridBagLayout());
        final JPanel input = new JPanel();
        centeredGrid.add(grid);

        JLabel entryMessage = new JLabel("Difficulty Glossary:\n\n Hard - 50/81 blank spaces\n Medium - 35/81 blank spaces\n Easy - 20/81 blank spaces\n");
        entryMessage.setBounds(10,20,80,25);
    input.add(entryMessage);

    JTextField difficulty = new JTextField(20);
    difficulty.setBounds(100, 20, 165, 25);
    input.add(difficulty);
    String level = difficulty.getText();   

        final JFrame frame = new JFrame("Sudoku");
        frame.add(input);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(centeredGrid);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(final String[] args) {
        createWindow();
        int[][] puzzle = {
            {
                1,
                2,
                3,
                4,
                5,
                6,
                7,
                8,
                9
            },
            {
                1,
                2,
                3,
                4,
                5,
                6,
                7,
                8,
                9
            },
            {
                1,
                2,
                3,
                4,
                5,
                6,
                7,
                8,
                9
            },
            {
                1,
                2,
                3,
                4,
                5,
                6,
                7,
                8,
                9
            },
            {
                1,
                2,
                3,
                4,
                5,
                6,
                7,
                8,
                9
            },
            {
                1,
                2,
                3,
                4,
                5,
                6,
                7,
                8,
                9
            },
            {
                1,
                2,
                3,
                4,
                5,
                6,
                7,
                8,
                9
            },
            {
                1,
                2,
                3,
                4,
                5,
                6,
                7,
                8,
                9
            },
            {
                1,
                2,
                3,
                4,
                5,
                6,
                7,
                8,
                9
            }
        };
        // createBoard(puzzle);
    }
}
