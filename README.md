# 8-PUZZLE-PROBLEM
This a program which solves the infamous 8 puzzle problem using Uniform Cost Search and A* Manhattan algorithm.

Algorithms Used:
1. Uniform Cost Search

Uniform cost search is the general version of breadth-first search where only the cost of each node is considered with the heuristic function h(n) evaluating to 0 at every node. For the 8 Puzzle game, since each operation takes an atomic step, the weight of every edge in the game tree is always 1. In this case, uniform cost search simply equals breadth-first search. In my program, in order to make all algorithms as general as possible, I implemented uniform cost search simply as A* search with a heuristic value (h_n) of 0.

2. A* with Misplaced Tile Heuristic

This heuristic function returns the number of misplaced tiles of a state. That is to say, it evaluates every tile of every game board to see if it is in the goal-state location. If not, h_n will increment by 1 for every tile. Hence the worst possible h_n for any 8 Puzzle is 9.

This heuristic encourages the program to expand nodes in the queue that have the most number of correctly placed tiles. This in most cases means a desirable state, although it would misevaluate some layouts such as the following example:
[[*, 2, 3],
[4, 5, 6],
[7, 8, 1]]
In this case, only two tiles are misplaced: the empty tile and the 1 tile. However, this state is actually quite difficult to solve and would take more moves than say the following example:
[[1, 2, 3],
[4, 6, *],
[7, 5, 8]]
We can determine by just looking that this case requires only 3 slides to solve (left, down, right), but because it has 4 misplaced tiles instead of 2, its heuristic distance is actually higher.

3. A* with Manhattan Heuristic

To solve this shortcoming, we use another heuristic that returns the sum of L1 distances of all tiles to its goal-position. This distance measure makes intuitive sense because tiles can only slide horizontally or vertically. Both previous examples under this heuristic would have an h_n value of 4. This is a much less biased evaluation of the states although it is still not perfect as we can clearly see in the first example if we were to move 1 back to the upper left corner, we would mess up the ordering of other tiles, so the second case should get a much lower value.
