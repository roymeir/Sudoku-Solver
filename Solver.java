import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;
import javax.swing.text.TextAction;

@SuppressWarnings("serial")
public class Solver extends JPanel {
    private static final float FONT_SIZE = 24f;
    private int gridEdgeSize = 9;
    private List<JTextField> fieldList = new ArrayList<>();
    private boolean blockTextInput = true;
    private MyTextAction myTextAction = new MyTextAction("");
    private JPanel outerGridPanel = new JPanel(new GridLayout(3, 3, 3, 3));
    private JPanel[][] innerGridPanels = new JPanel[3][3];
    
    /* default constructor for creating empty solver objects */
    public Solver(){

    }
    public Solver(int[][] puzzle) {
        outerGridPanel.setBackground(Color.BLACK);
        outerGridPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                innerGridPanels[i][j] = new JPanel(new GridLayout(3, 3, 1, 1));
                innerGridPanels[i][j].setBackground(Color.BLACK);
                outerGridPanel.add(innerGridPanels[i][j]);
            }
        }

        for (int i = 0; i < gridEdgeSize; i++) {
            for (int j = 0; j < gridEdgeSize; j++) {
                JTextField textField = new JTextField(2);
                textField.setHorizontalAlignment(SwingConstants.CENTER);
                textField.setFont(textField.getFont().deriveFont(Font.BOLD, FONT_SIZE));
                textField.setBorder(null);
                if (puzzle[i][j] != 0) {
                    textField.setText(puzzle[i][j] + "");
                } else {
                    textField.setText("");
                }
                ((PlainDocument) textField.getDocument()).setDocumentFilter(new MyDocFilter());
                innerGridPanels[i / 3][j / 3].add(textField);
                fieldList.add(textField);
            }
        }

        JPanel numberPanel = new JPanel(new GridLayout(0, 3));
        for (int i = 1; i < 10; i++) {
            String text = String.valueOf(i);
            JButton button = new JButton(text);
            button.setFont(button.getFont().deriveFont(Font.BOLD, 2 * FONT_SIZE));
            button.addActionListener(myTextAction);
            numberPanel.add(button);
        }

        setLayout(new GridLayout(1, 2));
        add(outerGridPanel);
        add(numberPanel);
    }

    private static void createUI(final JFrame frame) {
        JPanel panel = new JPanel();
        JButton button = new JButton("Play");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String result = (String) JOptionPane.showInputDialog(
                    frame,
                    "Difficulty Glossary:\n\n Hard - 50/81 blank spaces\n Medium - 35/81 blank spaces\n Easy - 20/81 blank spaces\n\nChoose your desired difficulty:\n\tHard: 1\n\tMedium: 2\n\tEasy: 3\nIf your input doesn't match one of these digits, the board generated will be on easy mode.",
                    "Difficulty Glossary",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    "3"
                );
                Solver mainPanel = play(Integer.parseInt(result), frame);
                frame.add(mainPanel); 
                frame.pack();
                frame.remove(panel);
            }
        });

        panel.add(button);
        frame.add(panel);
    }

    public static Solver play(int level, final JFrame frame) {
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
                Board sudoku = new Board(N, K);
                sudoku.fillValues();
               Solver mainPanel = new Solver(sudoku.puzzle);
               return mainPanel;
            }

            

    private class MyTextAction extends TextAction {

        public MyTextAction(String name) {
            super(name);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String text = e.getActionCommand();
            JTextComponent selectedComponent = getFocusedComponent();
            
            if (selectedComponent != null) {
                blockTextInput = false;
                selectedComponent.setText(text);
                blockTextInput = true;
            }
        }
    }

    private class MyDocFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                throws BadLocationException {
            if (blockTextInput) {
                return;
            }
            super.insertString(fb, offset, string, attr);
        }

        @Override
        public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
            if (blockTextInput) {
                return;
            }
            super.remove(fb, offset, length);
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                throws BadLocationException {
            if (blockTextInput) {
                return;
            }
            super.replace(fb, offset, length, text, attrs);
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Sudoku Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            createUI(frame);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}