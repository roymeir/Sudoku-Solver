/*Java Program to solve Sudoku problem using Backtracking*/
import java.util.Scanner;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.*;


public class Solver extends Board implements ActionListener {

    int[][] puzzle;
    int N; // number of columns/rows.
    int SRN; // square root of N
    int K; // No. Of missing digits

    // Constructor
    Solver(int N, int K) {
        this.N = N;
        this.K = K;

        // Compute square root of N
        Double SRNd = Math.sqrt(N);
        SRN = SRNd.intValue();

        puzzle = new int[N][N];
    }

    // Sudoku Generator
    public void fillValues() {
        // Fill the diagonal of SRN x SRN matrices
        fillDiagonal();

        // Fill remaining blocks
        fillRemaining(0, SRN);

        // Remove Randomly K digits to make game
        removeKDigits();
    }

    // Fill the diagonal SRN number of SRN x SRN matrices
    void fillDiagonal() {

        for (int i = 0; i < N; i = i + SRN)

            // for diagonal box, start coordinates->i==j
            fillBox(i, i);
    }

    // Returns false if given 3 x 3 block contains num.
    boolean unUsedInBox(int rowStart, int colStart, int num) {
        for (int i = 0; i < SRN; i++)
            for (int j = 0; j < SRN; j++)
                if (puzzle[rowStart + i][colStart + j] == num)
                    return false;

        return true;
    }

    // Fill a 3 x 3 matrix.
    void fillBox(int row, int col) {
        int num;
        for (int i = 0; i < SRN; i++) {
            for (int j = 0; j < SRN; j++) {
                do {
                    num = randomGenerator(N);
                }
                while (!unUsedInBox(row, col, num));

                puzzle[row + i][col + j] = num;
            }
        }
    }

    // Random generator
    int randomGenerator(int num) {
        return (int) Math.floor((Math.random() * num + 1));
    }

    boolean isValid( /*int[][] puzzle,*/ int row, int col, int input) {
        // check if row contains the input
        for (int d = 0; d < puzzle.length; d++) {

            /* return false if input already appears in row */
            if (puzzle[row][d] == input) {
                return false;
            }
        }
        // check if column contains the input
        for (int r = 0; r < puzzle.length; r++) {
            /* return false if input already appears in column */
            if (puzzle[r][col] == input) {
                return false;
            }
        }

        // check if square contains the input
        int sqrt = (int) Math.sqrt(puzzle.length);
        int bRow = row - row % sqrt;
        int bCol = col - col % sqrt;
        for (int r = bRow; r < bRow + sqrt; r++) {
            for (int d = bCol; d < bCol + sqrt; d++) {
                if (puzzle[r][d] == input) {
                    return false;
                }
            }
        }
        return true;
    }

    // A recursive function to fill remaining
    // matrix
    boolean fillRemaining(int i, int j) {
        // System.out.println(i+" "+j);
        if (j >= N && i < N - 1) {
            i = i + 1;
            j = 0;
        }
        if (i >= N && j >= N)
            return true;

        if (i < SRN) {
            if (j < SRN)
                j = SRN;
        } else if (i < N - SRN) {
            if (j == (int)(i / SRN) * SRN)
                j = j + SRN;
        } else {
            if (j == N - SRN) {
                i = i + 1;
                j = 0;
                if (i >= N)
                    return true;
            }
        }

        for (int num = 1; num <= N; num++) {
            if (isValid(i, j, num)) {
                puzzle[i][j] = num;
                if (fillRemaining(i, j + 1))
                    return true;

                puzzle[i][j] = 0;
            }
        }
        return false;
    }

    boolean solve_sudoku( /*int[][] puzzle, int N*/ ) {
        /*
         * This is the actual code that solves the puzzle.
         * If no solution exists, the function will return false.
         */
        int row = -1;
        int col = -1;
        /* Initialized to -1 so as to not point to a specific row or column */
        boolean isEmpty = true;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (puzzle[i][j] == 0) {
                    /* First instance of an empty cell, represented by a 0 */
                    row = i;
                    col = j;
                    isEmpty = false;
                    break;
                }
            }

            if (!isEmpty) {
                /* Break out of the external loop and not only the internal one */
                break;
            }
        }
        /*
         * If we have no more empty cells, return true.
         * This will serve as the ending point of all recursive calls.
         */
        if (isEmpty) {
            return true;
        }
        /*
         * If we do have empty cells, we use backtracking to enter the correct digit in
         * every empty cell
         */
        for (int number = 1; number <= N; number++) {
            if (isValid(row, col, number)) {
                puzzle[row][col] = number;
                if (solve_sudoku( /*puzzle, N*/ )) {
                    /*
                     * This will return true if the value we entered is correct. It may have been
                     * correct on entry,
                     * but it could create an error once all empty cells are filled.
                     */
                    return true;
                } else {
                    /*
                     * The addition was valid at the time of insertion, but once other cells were
                     * filled we found a problem.
                     * This means the value we put in the cell was not the right one and we empty
                     * the cell to try again.
                     */
                    puzzle[row][col] = 0;
                }
            }
        }
        return false; // If we've reached this point, there is not solution available for the given
        // board
    }

    // Remove the K no. of digits to
    // complete game
    public void removeKDigits() {
        int count = K;
        while (count != 0) {
            int cellId = randomGenerator(N * N) - 1;

            // System.out.println(cellId);
            // extract coordinates i and j
            int i = (cellId / N);
            int j = cellId % 9;
            if (j != 0)
                j = j - 1;

            // System.out.println(i+" "+j);
            if (puzzle[i][j] != 0) {
                count--;
                puzzle[i][j] = 0;
            }
        }
    }

    // Print sudoku
    public void printSudoku() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.print(puzzle[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }
    public static void fillTable(int[][] puzzle, JTable table) {
        for (int i = 0; i < puzzle.length; i++) {
            for (int j = 0; j < puzzle[0].length; j++) {

            }
        }
    }
    public static void play(int level) {
        // System.out.println("Difficulty Glossary:\n\n Hard - 50/81 blank spaces\n Medium - 35/81 blank spaces\n Easy - 20/81 blank spaces\n");
        // System.out.println("Choose your desired difficulty:\nFor Hard, enter 1.\nFor Medium, press 2.\nFor Easy, press 3.\nIf your input doesn't match one of these digits, the board generated will be on easy mode.");
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
        // createBoard(sudoku.puzzle);
        // System.out.println();
        // sudoku.printSudoku();
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
                    "Difficulty Glossary:\n\n Hard - 50/81 blank spaces\n Medium - 35/81 blank spaces\n Easy - 20/81 blank spaces\n\nChoose your desired difficulty:\n\tFor Hard, enter 1.\n\tFor Medium, press 2.\n\tFor Easy, press 3.\nIf your input doesn't match one of these digits, the board generated will be on easy mode.",
                    "Swing Tester",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    "1"
                );
                play(Integer.parseInt(result));
                // if (result != null && result.length() > 0) {
                //     label.setText("You selected:" + result);
                // } else {
                //     label.setText("None selected");
                // }
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
        String level = difficulty.getText();

        final JFrame frame = new JFrame("Sudoku");
        frame.add(input);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(centeredGrid);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }


    public static void button(Solver sudoku) {
        JFrame frame = new JFrame("Click Button To Show Solution");
        JButton btn = new JButton("Solution");
        btn.setBounds(70, 80, 100, 30);
        //Change button text on click
        // btn.addActionListener(new ActionListener() {
        //     public void actionPerformed(ActionEvent ae) {
        //         sudoku.solve_sudoku();
        //         sudoku.printSudoku();
        //     }
        // });
        frame.add(btn);
        frame.setSize(250, 250);
        frame.setLayout(null);
        frame.setVisible(true);

    }


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