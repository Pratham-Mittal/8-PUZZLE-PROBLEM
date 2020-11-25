package ce213;

import java.io.*;
import java.util.*;
import static java.lang.System.exit;

public class Main
{
    public int[][] InitialState = {{8, 7, 6},   // the initial grid configuration as per assignment.
                                   {5, 4, 3},
                                   {2, 1, 0}};
    private long start;   // variables for calculating the time
    private long end;

    Solve solve = new Solve();

    public static void main(String[] args) throws IOException
    {
        new Main();
    }


    Main()
    {
        Scanner input = new Scanner(System.in);                               //scanner to take user input
        Actions act = new Actions();                                          //object for action class
        int choice;          //variable to get user choice

        System.out.println("The initial state of the puzzle is: ");
        act.printState(InitialState);
        System.out.println("Goal state is: ");
        act.printState(solve.getGoalState());

        System.out.println("Choose the method by which you want to solve the 8 puzzle problem\n");
        System.out.println("1) Uniform Cost Search \t 2) A* Manhattan Search\n");
        choice = Integer.parseInt(input.nextLine());                      //gets the user input in choice

        try
        {
            if (choice == 1)                                                //finds the solution according to the user input
            {                                                               //if user chooses uniform cost search then it uses that algorithm
                System.out.println("Trying to find an optimal Solution");   //to solve the problem
                start = System.currentTimeMillis();  //gets start time
                solve.solution(InitialState, 1);
            }else if (choice == 2)
            {
                System.out.println("Trying to find an optimal Solution");   //if user chooses A* then it uses A* algorithm
                start = System.currentTimeMillis();  //gets start time
                solve.solution(InitialState, 2);
            }else
            {
                System.out.println("Invalid input please enter 1 or 2 ");     //if user enters anything other than 1 or 2 then outputs invalid value
                exit(-1);
            }

            end = System.currentTimeMillis();             //gets end time

            try {

                System.out.println("Optimal Solution Found\n");
                System.out.println("Number of nodes expanded: " + solve.getNodes());
                System.out.println("Number of nodes unexpanded: " + solve.getNodes2());
                System.out.println("Time taken: "+((end - start) / 1000)+" seconds");
                printSolution(choice);

            } catch (Exception e){
                System.out.println("Solution Not Found");
            }
        }catch (Exception e)
        {
            System.out.println("Couldn't solved the problem");
        }
    }


    private void printSolution(int choice) throws IOException   // Method for printing the results in a .txt files.
    {
        if (choice == 1)                  // if user chooses uniform cost search then output file is outputUCS.txt
        {
            FileWriter file = new FileWriter("outputUniCost.txt");     //writes a file
            PrintWriter out = new PrintWriter(file);                       //used for printing in file
            ArrayList<State> moves = solve.getMoves();
            //String[] temp = new String[solve.getMoves().size()];
            out.printf("Solution Found\n");
            out.printf("Number of moves: " + solve.costs + "\n");
            out.printf("Number of nodes expanded: " + solve.getNodes() + "\n");
            out.printf("Number of nodes unexpanded: " + solve.getNodes2() + "\n");
            out.printf("Time taken: "+((end - start) / 1000)+" seconds\n");
           for (int i = 0; i < moves.size(); i++)                        //loop to print all possible moves in a tree whether right or wrong
            {
                int[][] tempGrid = moves.get(i).getGamegrid();
                for(int j = 0; j < 3; j++)
                {
                    for(int z = 0; z < 3; z++)
                    {
                        out.printf(tempGrid[j][z] + " ");
                    }
                    out.printf("\n");
                }
                out.printf("\n");
            }

            out.close();
        }
        if (choice == 2)                                        // if user chooses A* then output file is outputAStar.txt
        {
            FileWriter file = new FileWriter("outputASTAR.txt");
            PrintWriter outs = new PrintWriter(file);
            ArrayList<State> moves = solve.getMoves();
            //String[] temp = new String[solve.getMoves().size()];
            outs.printf("Solution Found\n");
            outs.printf("Number of moves: " + solve.costs + "\n");
            outs.printf("Number of nodes expanded: " + solve.getNodes() + "\n");
            outs.printf("Number of nodes unexpanded: " + solve.getNodes2() + "\n");
            outs.printf("Time taken: "+((end - start) / 1000)+" seconds\n");
            for (int i = 0; i < moves.size(); i++)                                  //loop to print all possible states in a tree whether right or wrong
            {
                int[][] tempGrid = moves.get(i).getGamegrid();
                for(int j = 0; j < 3; j++)
                {
                    for(int z = 0; z < 3; z++)
                    {
                        outs.printf(tempGrid[j][z] + " ");
                    }
                    outs.printf("\n");
                }
                outs.printf("\n");
            }

            outs.close();  //closes the file
        }
    }









}

