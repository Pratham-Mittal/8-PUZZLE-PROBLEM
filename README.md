# 8-PUZZLE-PROBLEM
This a program which solves the infamous 8 puzzle problem using Uniform Cost Search and A* Manhattan algorithm.

Algorithms Used:
1. Uniform Cost Search

Uniform cost search is the general version of breadth-first search where only the cost of each node is considered with the heuristic function h(n) evaluating to 0 at every node. For the 8 Puzzle game, since each operation takes an atomic step, the weight of every edge in the game tree is always 1. In this case, uniform cost search simply equals breadth-first search. In my program, in order to make all algorithms as general as possible, I implemented uniform cost search simply as A* search with a heuristic value (h_n) of 0.


2. A* with Manhattan Heuristic

To solve this shortcoming, we use another heuristic that returns the sum of L1 distances of all tiles to its goal-position. This distance measure makes intuitive sense because tiles can only slide horizontally or vertically. Both previous examples under this heuristic would have an h_n value of 4. This is a much less biased evaluation of the states although it is still not perfect as we can clearly see in the first example if we were to move 1 back to the upper left corner, we would mess up the ordering of other tiles, so the second case should get a much lower value.


For returning the possible tile moves we iterate through the game state matrix using nested for loops and check for the location of 0. When the location of 0 is found the it gets the position of 0 in matrix in variables x and y which stand for row in the matrix and column in the matrix respectively. For e.g. If the game state is like [1,0,3],
                                   [2,4,5],
                                   [7,6,8]
the position of x will be 1 and  y will be 2. Then we check for the possible moves, where all it can move left, right, up, down by cloning the current grid and then using a temporary variable to change the position of 0.
To move right we check if column y + 1 is less than 3 because only then it will be able to move right.
To move left we check if column y – 1 is greater than or equal to zero so that we can know that there is at least one column in the left.
To move up we check row x – 1 greater than or equal to zero so that we can know that there is at least one row above 0.
To move down we check if row x + 1 is less than 3 because only then it will be able to move down.


By using uniform cost search it took 30 moves and about 644 seconds to get the answer.
Note: My program prints all the states which will be in a tree without considering whether that expands or not, that’s the reason for it to print so many grids. The number of moves have been calculated using cost.

By using A* search it took 30 moves and about 9 seconds to get the answer..
Note: My program prints all the states which will be in a tree without considering whether that expands or not, that’s the reason for it to print so many grids. The number of moves have been calculated using cost.

