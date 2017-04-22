package algorithms.mazeGenerators;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by zaks on 11/04/2017.
 * An abstract class to the maze Generators, implements mazeGenerator Interface
 * Generate mazes and measure algorithms running time
 */
public abstract class AMazeGenerator implements IMazeGenerator {
    private long time;
    private Maze maze;

    /**
     * Abstract method of maze generation, implemented in inner classes
     * @param col
     * @param row
     * @return
     */
    public abstract Maze generate(int col, int row);

    /**
     * Measure time of generating a certain maze
     * @param col
     * @param row
     * @return
     */
    public long measureAlgorithmTime(int col, int row) {
        long first = System.currentTimeMillis();
        this.maze = generate(col, row);
        long second = System.currentTimeMillis();
        this.time = second - first;
        return time;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Maze getM() {
        return maze;
    }

    public void setM(Maze m) {
        this.maze = m;
    }

    /**
     * Side-function which creates Randomly goal and start positioning points
     * @param col
     * @param row
     * @return
     */
    public Position randomPos(int col, int row) {
        Random sx = new Random();
        int startY = sx.nextInt(row);
        int startX;
        if (startY == 0 || startY == row - 1) {
            Random sy = new Random();
            startX = sy.nextInt(col);
        } else {
            if (Math.random() > 0.5)
                startX = 0;
            else
                startX = col - 1;
        }
        Position start = new Position(startX, startY);
        return start;
    }

}
