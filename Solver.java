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
	
    public static boolean solve_sudoku(int[][]puzzle, int N){
        /* This is the actual code that solves the puzzle.
         * If no solution exists, the function will return false.
         */
        int row = -1;
		int col = -1;
		boolean isEmpty = true;
		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < N; j++)
			{
				if (puzzle[i][j] == 0)
				{
                    /* First instance of an empty cell, represented by a 0 */
					row = i;
					col = j;
					isEmpty = false;
					break;
				}
			}
			if (!isEmpty) { /* Break out of the external loop and not only the internal one */
				break;
			}
		}
		// No empty cells left
		if (isEmpty)
		{
			return true;
		}
		/* If we do have empty cells, we use backtracking to enter the correct digit in every empty cell */ 
		for (int number = 1; number <= N; number++)
		{
			if (isValid(puzzle, row, col, number))
			{
				puzzle[row][col] = number;
				if (solve_sudoku(puzzle, N))
				{
					// checks if the addition we made makes the board valid 
					return true;
				}
				else
				{
					// replace it
					puzzle[row][col] = 0;
				}
			}
		}
		return false;   //If we've reached this point, there is not solution available for the given board
    }

    // Driver Code
	public static void main(String args[])
	{
        int[][] board = new int[][] {       
            { 1, 5, 0, 0, 4, 2, 0, 0, 6 },
			{ 2, 7, 4, 5, 6, 0, 0, 1, 0 },
			{ 0, 0, 6, 0, 0, 7, 4, 0, 2 },
			{ 0, 1, 0, 0, 0, 0, 0, 4, 0 },
			{ 0, 0, 0, 0, 5, 0, 0, 0, 0 },
			{ 0, 6, 0, 4, 0, 3, 1, 9, 0 },
			{ 0, 2, 0, 6, 0, 5, 9, 0, 0 },
			{ 9, 8, 5, 0, 3, 0, 0, 6, 0 },
			{ 0, 4, 0, 2, 1, 9, 8, 3, 0 }
		};
       if (solve_sudoku(board, 9)){
        for (int i=0;i<9;i++){   //making sure the board was created successfully 
            for (int j=0;j<9;j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
           }
        
       }
       else{
        System.out.println("This can't be solved");
       }
       
	}
}

