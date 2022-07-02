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
                try (Scanner scan = new Scanner(System.in)) {
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
        }
        return sudoku_board;
    }
	
    public static boolean solve_sudoku(int[][]puzzle, int N){
        /* This is the actual code that solves the puzzle.
         * If no solution exists, the function will return false.
         */
        int row = -1;
		int col = -1;
        /* Initialized to -1 so as to not point to a specific row or column */
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

			if (!isEmpty) 
            { /* Break out of the external loop and not only the internal one */
				break;
			}
		}
		/* If we have no more empty cells, return true.
         * This will serve as the ending point of all recursive calls. 
         */
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
					/* This will return true if the value we entered is correct. It may have been correct on entry,
                    but it could create an error once all empty cells are filled. */ 
					return true;
				}
				else
				{
					/* The addition was valid at the time of insertion, but once other cells were filled we found a problem.
                     * This means the value we put in the cell was not the right one and we empty the cell to try again.
                     */
					puzzle[row][col] = 0;
				}
			}
		}
		return false;   //If we've reached this point, there is not solution available for the given board
    }
    public static void print_puzzle(int [][]puzzle, int N){
        System.out.println("Board after solution:");
        for (int i=0;i<9;i++)
        {    
            for (int j=0;j<9;j++)
            {
                System.out.print(puzzle[i][j]+" ");
            }
            System.out.println();  
        }       
    }
    // Driver Code
	public static void main(String args[])
	{
        int[][] puzzle = new int[][] {
			{ 3, 0, 5, 4, 0, 2, 0, 6, 0 },
			{ 4, 9, 0, 7, 6, 0, 1, 0, 8 },
			{ 6, 0, 0, 1, 0, 3, 2, 4, 5 },
			{ 0, 0, 3, 9, 0, 0, 5, 8, 0 },
			{ 9, 6, 0, 0, 5, 8, 7, 0, 3 },
			{ 0, 8, 1, 3, 0, 4, 0, 9, 2 },
			{ 0, 5, 0, 6, 0, 1, 4, 0, 0 },
			{ 2, 0, 0, 5, 4, 9, 0, 7, 0 },
			{ 1, 4, 9, 0, 0, 7, 3, 0, 6 }
		};
        System.out.println("Input board before solution:");
        print_puzzle(puzzle, puzzle.length);
        System.out.println("");

        if (solve_sudoku(puzzle, puzzle.length))    //Making sure the board was created successfully
        {
            print_puzzle(puzzle, puzzle.length);
        }
        else
           {
            System.out.println("This can't be solved");
           }
	}
}

