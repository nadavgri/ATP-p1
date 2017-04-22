package algorithms.mazeGenerators;

/**
 * Created by zaks on 11/04/2017.
 * Interface to define which methods mazeGenerator class needs
 */
public interface IMazeGenerator {
    Maze generate(int col, int row);
    long measureAlgorithmTime(int col, int row);
}
