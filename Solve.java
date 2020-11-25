package ce213;

import java.util.*;

public class Solve {
    private PriorityQueue<  State> unexpand = new PriorityQueue<>(Comparator.comparing(State::getF_n));   // Sorting the PriorityQueue by f_n value.
    private ArrayList<State> expand = new ArrayList<>();   // ArrayList for expanded nodes.
    private ArrayList<State> allmoves = new ArrayList<>();   // ArrayList for storing the moves.

    private int[][] GoalState = {{1, 2, 3},
            {4, 5, 6},   // Goal grid representation.
            {7, 8, 0}};
    public int costs;

    // Calculates the all possible move options for 0.
    private ArrayList<State> allpossiblemoves(State state, int search)
    {
        ArrayList<State> moves = new ArrayList<>();
        Actions act = new Actions();

        int[][] gamegrid = state.getGamegrid();
        int cost = state.getCost();
        int x = -1;
        int y = -1;
        int heuristics = 0;

        // Calculating the cost according to the search algorithm
        if (search == 1)   // for UCS heuristics is 0
        {
            heuristics = 0;
        }else if (search == 2)  //for A* we are using A* Manhattan distance
        {
            for(int i=0; i < 3; i++)
                for(int j=0; j < 3; j++)
                {
                    int position = gamegrid[i][j];
                    for (int k = 0; k < 3; k++)
                        for (int l = 0; l < 3; l++)
                        {
                            if(GoalState[k][l] == position)
                            {
                                heuristics += Math.abs(k-i) + Math.abs(l-j);   // Calculating the heuristic value for A* search by Manhattan Distance
                            }
                        }
                }
        }
        //loop to iterate through the game grid and get the position of 0
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++)
            {
                if(gamegrid[i][j] == 0)    // Finding the exact position of the blank space represented by 0.
                {
                    x = i;
                    y = j;
                }
            }
        }
        //move left
        if(y - 1 >= 0)
        {
            int [][] currentGrid = new int[gamegrid.length][];
            act.clone(gamegrid, currentGrid);
            act.moves(currentGrid, x,y, "left");
            moves.add(new State(currentGrid,cost+1,heuristics));
        }
        // Move Down
        if(x + 1 < 3)
        {

            int [][] currentGrid = new int[gamegrid.length][];
            act.clone(gamegrid, currentGrid);
            act.moves(currentGrid, x, y, "down");
            moves.add(new State(currentGrid,cost+1,heuristics));
        }
        // Move Right
        if(y + 1 < 3)
        {

            int [][] currentGrid = new int[gamegrid.length][];
            act.clone(gamegrid, currentGrid);
            act.moves(currentGrid, x,y, "right");
            moves.add(new State(currentGrid,cost+1,heuristics));
        }
        // Mov Up
        if(x - 1 >= 0)
        {

            int [][] currentGrid = new int[gamegrid.length][];
            act.clone(gamegrid, currentGrid);
            act.moves(currentGrid,x,y,"up");
            moves.add(new State(currentGrid,cost+1,heuristics));
        }
        return moves;
    }
    //method to solve the problem
    private void Solver(int[][] initialState, int srch)
    {
        // Adding the initial grid to unexpand node and expand node lists.
        unexpand.add(new State(initialState,0,0));
        expand.add(new State(initialState,0,0));
        Actions act = new Actions();

        while(unexpand.size() > 0)   // As long as there are nodes to be expanded, continue solving
        {
            ArrayList<State> possiblemoves;
            State tempState = unexpand.peek();  // Gets the configuration of Game grid with smallest heuristic value.


            int[][] tempgameGrid = tempState.getGamegrid();
            int[][] currentgameGrid = new int[initialState.length][];
            for(int i = 0; i < initialState.length; i++)   // Cloning the tempgameGrid into currengametGrid
                currentgameGrid[i] = tempgameGrid[i].clone();

            State currentState = new State(currentgameGrid,tempState.getCost(),tempState.getHeuristics());
            //System.out.println("cost = "+ currentState.getCost());
            allmoves.add(currentState);   // Adding the move carried out.
            unexpand.remove();   // Removing the first item in the unexpanded list.
            costs = currentState.getCost();  //gets the number of moves or say number of rows in the tree

            if(act.ifequals(currentState.getGamegrid(), GoalState))
            {
                return;   // Terminate the loop if the goalstate condition has been reached.
            } else
            {  // Continue executing if the goalstate has not reached.
                possiblemoves = allpossiblemoves(currentState, srch);
                for(State state : possiblemoves)
                {
                    // Adding the grid configurations from the possible moves to the lists if it is a unique configuration.
                    if(!act.hasState(state, expand))
                    {
                        unexpand.add(state);
                        expand.add(state);
                    }
                }
            }
        }
    }

    public int[][] getGoalState()
    {
        return GoalState;
    }

    public void solution(int[][] initialState, int search) throws Exception
    {
        Solver(initialState, search);
    }

    public int getNodes()
    {
        return expand.size();
    }

    public ArrayList<State> getMoves()
    {
        ArrayList<State> ex = new ArrayList<>(allmoves);
        return ex;
    }
    public int getNodes2(){return unexpand.size();}

}


