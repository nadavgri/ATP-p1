package test;

import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.*;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by zaks on 11/04/2017.
 */

public class RunSearchOnMaze {

    public static void main(String[] args) throws InterruptedException {
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(30, 30);
        maze.print();
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        System.out.println(maze.getStartPosition().toString());
        System.out.println(maze.getGoalPosition().toString());

        solveProblem(searchableMaze, new BreadthFirstSearch());
        solveProblem(searchableMaze, new DepthFirstSearch());
        solveProblem(searchableMaze, new BestFirstSearch());

        TimeUnit.SECONDS.sleep(45);

    }

    private static void solveProblem(ISearchable domain, ISearchAlgorithm searcher) {
        //Solve a searching problem with a searcher
        Solution solution = searcher.solve(domain);
        System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
        //Printing Solution Path
        System.out.println("Solution path:");
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        for (int i = 0; i < solutionPath.size(); i++) {
            System.out.println(String.format("%s. %s", i, solutionPath.get(i)));
        }
    }
}