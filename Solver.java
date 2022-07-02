    /*Java Program to solve Sudoku problem using Backtracking*/
import java.util.Scanner; 
public class Solver
{
	public static boolean isValid(int[][] puzzle,int row, int col,int input)
	{
		// check if row contains the input
		for (int d = 0; d < puzzle.length; d++)
		{
			
			/* return false if input already appears in row*/
			if (puzzle[row][d] == input) {
				return false;
			}
		}
		// check if column contains the input
		for (int r = 0; r < puzzle.length; r++)
		{
			 /* return false if input already appears in column*/
			if (puzzle[r][col] == input)
			{
				return false;
			}
		}

        // check if square contains the input
		int sqrt = (int)Math.sqrt(puzzle.length);
		int bRow = row - row % sqrt;
		int bCol = col - col % sqrt;
		for (int r = bRow;
			r < bRow + sqrt; r++)
		{
			for (int d = bCol;
				d < bCol + sqrt; d++)
			{
				if (puzzle[r][d] == input)
				{
					return false;
				}
			}
		}
        return true;
    }
		
    public static int[][] sudokuGenerator(int N){
        /*gives user option to create customized sudoko board. Uses the isValid function 
        to make sure user input is valid before progressing to the next cell. The only input is the size of the board.
        If the user inputs 0, that means the given cell is to be left empty.*/
        int[][] sudoku_board = new int [N][N];
        for (int i=0;i<N;i++){
            for (int j=0; j<N; j++){
                System.out.println("Enter value of cell "+(i+1)+" "+(j+1));
                Scanner scan = new Scanner(System.in);
                int attempt = scan.nextInt();
                if (attempt == 0){
                    sudoku_board[i][j] = attempt;
                }
                else{
                    while (!isValid(sudoku_board, i, j, attempt)){
                        System.out.println("This entry is invalid. Try another number: ");
                        attempt = scan.nextInt();
                    }
                }
                sudoku_board[i][j] = attempt;    
            }
        }
        return sudoku_board;
    }
	// Driver Code
	public static void main(String args[])
	{
       int [][] board = sudokuGenerator(3);
       for (int i=0;i<3;i++){   //making sure the board was created successfully 
        for (int j=0;j<3;j++){
            System.out.print(board[i][j]+" ");
        }
        System.out.println();
       }
	}
}

