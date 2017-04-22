package algorithms.mazeGenerators;

/**
 * Created by zaks on 11/04/2017.
 * pull Test
 */
public class Main {
    public static void main(String[] args) {
       int row = 10;
        int col = 40;
       //SimpleMazeGenerator s = new SimpleMazeGenerator();
        MyMazeGenerator s = new MyMazeGenerator();
        Maze m = s.generate(col, row);
        m.print();
        System.out.println("Time: " + new MyMazeGenerator().measureAlgorithmTime(col, row));

    }
}
