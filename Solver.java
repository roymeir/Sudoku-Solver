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
		

	// Driver Code
	public static void main(String args[])
	{
       
	}
}

