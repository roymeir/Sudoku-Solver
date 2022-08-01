# Sudoku-Solver
I really like sudoku puzzles. I was doing one today and noticed the repetitive nature of the task of solving it. 
That intrigued me and made me want to write code that solves the puzzle on its own.

The current state of this program is that once you run the code, you can choose a difficulty level that determines the number of empty spaces you'll have to fill out on the generated board. 
Once you choose the difficulty, a JPanel opens with text fields containing the generated board and a matrix of digits to choose from.
Users can click the buttons to enter digits into empty cells and typing is blocked so the only input method is through the buttons on the screen.

My next challenge will be to only enable data entries in cells that were empty to begin with so the computer generated cells cannot be changed. 
Also, if the player gets a cell wrong, they'll be notified and recieve a strike. 
Three strikes means you lose the game.
Stay tuned! 
