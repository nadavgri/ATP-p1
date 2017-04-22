package algorithms.mazeGenerators;

import java.util.Random;

/**
 * Created by zaks on 11/04/2017.
 * Simple maze generator class, create random maze with entrance and exit.
 * extends AMazeGenerator
 */
public class SimpleMazeGenerator extends AMazeGenerator {
    /**
     * creates maze sized col*row, randomly insert passage and walls
     * Start and Goal positions are randomly selected - not the same position.
     * each Row and Column that Start or Goal are in will be a passage so there will always be a solution.
     * @param col
     * @param row
     * @return
     */
    public Maze generate(int col, int row) {
        int[][] build = new int[col][row];
        Position start = randomPos(col, row);
        Position goal = randomPos(col, row);
        while (start.getX() == goal.getX() && start.getY() == goal.getY()) {
            start = randomPos(col, row);
            goal = randomPos(col, row);
        }
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                if (Math.random() > 0.5)
                    build[i][j] = 0;
                else
                    build[i][j] = 1;

                if (j == start.getY() || j == goal.getY() || i == start.getX() || i == goal.getX())
                    build[i][j] = 0;
            }
        }
        Maze m = new Maze(start, goal, build);

        return m;
    }
}
