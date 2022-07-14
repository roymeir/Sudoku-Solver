/*Java Program to solve Sudoku problem using Backtracking*/
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.*;


public class Solver extends Board implements ActionListener {

    Solver(int N, int K) {
        super(N, K);
        //TODO Auto-generated constructor stub
    }

    public static void play(int level) {
        int N = 9, K = 0;
        switch (level) {
            case 1:
                K = 50;
                break;
            case 2:
                K = 35;
                break;

            default:
                K = 20;
                break;
        }
        Solver sudoku = new Solver(N, K);
        sudoku.fillValues();
        createBoard(sudoku.puzzle);
    }

    private static void createWindow() {
        JFrame frame = new JFrame("Swing Tester");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createUI(frame);
        frame.setSize(560, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void createUI(final JFrame frame) {
        JPanel panel = new JPanel();
        LayoutManager layout = new FlowLayout();
        panel.setLayout(layout);

        JButton button = new JButton("Play");
        final JLabel label = new JLabel();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String result = (String) JOptionPane.showInputDialog(
                    frame,
                    "Difficulty Glossary:\n\n Hard - 50/81 blank spaces\n Medium - 35/81 blank spaces\n Easy - 20/81 blank spaces\n\nChoose your desired difficulty:\n\tHard: 1\n\tMedium: 2\n\tEasy: 3\nIf your input doesn't match one of these digits, the board generated will be on easy mode.",
                    "Swing Tester",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    "3"
                );
                play(Integer.parseInt(result));
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
                if (puzzle[i][j] != 0) {
                    field.setText(puzzle[i][j] + "");
                } else {
                    field.setText("");
                }
                field.setHorizontalAlignment(JTextField.CENTER); //Center text horizontally in the text field.
                field.setBorder(fieldBorder); //Add the colored border.
                grid.add(field);
            }
        }

        final JPanel centeredGrid = new JPanel(new GridBagLayout());
        final JPanel input = new JPanel();
        centeredGrid.add(grid);

        JLabel entryMessage = new JLabel("Difficulty Glossary:\n\n Hard - 50/81 blank spaces\n Medium - 35/81 blank spaces\n Easy - 20/81 blank spaces\n");
        entryMessage.setBounds(10, 20, 80, 25);
        input.add(entryMessage);

        JTextField difficulty = new JTextField(20);
        difficulty.setBounds(100, 20, 165, 25);
        input.add(difficulty);
        final JFrame frame = new JFrame("Sudoku");
        frame.add(input);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(centeredGrid);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }


    // public static void button(Solver sudoku) {
    //     JFrame frame = new JFrame("Click Button To Show Solution");
    //     JButton btn = new JButton("Solution");
    //     btn.setBounds(70, 80, 100, 30);
    //     frame.add(btn);
    //     frame.setSize(250, 250);
    //     frame.setLayout(null);
    //     frame.setVisible(true);

    // }


    // Driver code
    public static void main(String[] args) {
        //play();
        createWindow();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }
}