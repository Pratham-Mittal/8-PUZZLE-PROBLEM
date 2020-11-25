package ce213;

import java.util.ArrayList;

public class Actions {

    public boolean ifequals(int[][] cstate, int[][] ostate)
    {
        for(int i=0; i < 3; i++)   // Compares two grids/states to check whether they are same or not.
            for(int j=0; j < 3; j++)
                if(cstate[i][j] != ostate[i][j])
                    return false;
        return true;
    }

    public void printState(int[][] state)
    {
        for(int i=0; i < 3; i++)   // Printing the grids according to 3 by 3 syntax since the size of matrix is 3*3
        {
            for(int j=0; j < 3; j++)
            {
                System.out.print(state[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void clone(int[][] gamegrid, int[][] temproryGrid)
    {
        for(int i = 0; i < gamegrid.length; i++)   // Cloning a grid into a temporary gamegrid.
            temproryGrid[i] = gamegrid[i].clone();
    }

    public boolean hasState(State state, ArrayList<State> expanded)
    {
        int[][] grid = state.getGamegrid();
        for(State stateExpanded : expanded)   // Iterating through the expand list.
        {
            int[][] temp = stateExpanded.getGamegrid();
            boolean hasstate = true;
            for(int i=0; i < 3; i++)   // Checks if  the desired state is in the expand list.
                for(int j=0; j < 3; j++)
                    if(temp[i][j] != grid[i][j])
                        hasstate = false;
            if(hasstate)
                return true;
        }
        return false;
    }
    //all the possible moves available
    //there are at a time atmost 4 positions available for the 0 to move
    public void moves(int[][] cGrid, int x,int y, String direction)
    {
        if (direction == "up")   // Initiates up movement.
        {
            int temp = cGrid[x][y];
            cGrid[x][y] = cGrid[x - 1][y];
            cGrid[x - 1][y] = temp;
        }else if (direction == "down")   // Initiates down movement.
        {
            int temp = cGrid[x][y];
            cGrid[x][y] = cGrid[x + 1][y];
            cGrid[x + 1][y] = temp;
        }else if (direction == "left")   // Initiates left movement.
        {
            int temp = cGrid[x][y];
            cGrid[x][y] = cGrid[x][y - 1];
            cGrid[x][y - 1] = temp;
        }else if (direction == "right")   // Initiates right movement.
        {
            int temp = cGrid[x][y];
            cGrid[x][y] = cGrid[x][y + 1];
            cGrid[x][y + 1] = temp;
        }
    }
}
