package ce213;

public class State {

    private int [][] gamegrid;        //variable to get the grid
    private int cost;                 //variable to get the cost g_n
    private int heuristics;           //variable to get heuristics h_n

    public State(int [][] gamegrid, int cost, int heuristics)  //constructor of the class
    {
        this.gamegrid = gamegrid;
        this.cost = cost;
        this.heuristics = heuristics;
    }


    public int getCost()               //method to get cost
    { return cost; }

    public int getHeuristics()         //method to get heuristics
    { return heuristics;}

    public int getF_n()                //method to get f_n
    { return ( cost + heuristics); }

    public  int[][] getGamegrid()      //method to get gamegrid
    { return gamegrid; }

    public String toString()
    {return " [ " + gamegrid + " ]";}
}
