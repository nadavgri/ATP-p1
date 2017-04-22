
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.search.*;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by zaks on 19/04/2017.
 */
public class UnitTestingBestFirstSearchTest {

    //check if start state position is same as needed
    @Test
    public void testStart() throws Exception {
    }

    //check maze with all 1 except start and goal
    @Test
    public void testNoSolution() throws Exception {
        int [][] mz = new int[5][5];
        for (int i = 0; i < mz.length; i++) {
            for (int j = 0; j < mz[0].length; j++) {
                mz[i][j] = 1;
            }
        }
        mz[0][0]=0;
        mz[4][4]=0;
        Maze maze = new Maze(new Position(0,0), new Position(4,4), mz);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        BestFirstSearch b = new BestFirstSearch();
        Solution s = b.solve(searchableMaze);
        assertEquals(0, s.getSolutionPath().size()); // 0 for no solution
        assertEquals(1, s.getNumberOfNodesEvaluation());// 1 for start
    }

    //check maze with all 1
    @Test
    public void testNoSolutionAllOne() throws Exception {
        int [][] mz = new int[5][5];
        for (int i = 0; i < mz.length; i++) {
            for (int j = 0; j < mz[0].length; j++) {
                mz[i][j] = 1;
            }
        }
        Maze maze = new Maze(new Position(0,0), new Position(4,4), mz);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        BestFirstSearch b = new BestFirstSearch();
        Solution s = b.solve(searchableMaze);
        assertEquals(0, s.getSolutionPath().size()); // 0 for no solution
        assertEquals(1, s.getNumberOfNodesEvaluation());// 1 for start
        //Position[] expected = {new Position(0,0), new Position(4,4)};
    }

    //check if name of the algorithm is "Best First Search Test"
    @Test
    public void testName() throws Exception {
        BestFirstSearch b = new BestFirstSearch();
        String trueName = b.getName();
        String expected = "Best First Search";
        assertEquals(expected, trueName);
    }

    @Test
    public void testSameGoalStart() throws Exception {
        int [][] mz = new int[5][5];
        for (int i = 0; i < mz.length; i++) {
            for (int j = 0; j < mz[0].length; j++) {
                if (Math.random() > 0.5)
                    mz[i][j] = 0;
                else
                    mz[i][j] = 1;
            }
        }
        Maze maze = new Maze(new Position(0,0), new Position(0,0), mz);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        BestFirstSearch b = new BestFirstSearch();
        Solution s = b.solve(searchableMaze);
        assertEquals(1, s.getSolutionPath().size()); // 1 for no solution
        assertEquals(1, s.getNumberOfNodesEvaluation());// 1 for start
    }
}