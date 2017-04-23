package algorithms.mazeGenerators;


/**
 * Created by zaks on 11/04/2017.
 * Maze class, contains 2D-array of walls and passages (1 & 0), positions of start and goal.
 */
public class Maze {
    private Position startPosition;
    private Position goalPosition;
    int[][] build;

    /**
     * Maze constructor
     * @param startPosition
     * @param goalPosition
     * @param build
     */
    public Maze(Position startPosition, Position goalPosition, int[][] build) {
        this.startPosition = startPosition;
        this.goalPosition = goalPosition;
        this.build = build;
    }

    public Position getStartPosition() {
        return startPosition;
    }

    public void setStartPotision(Position startPotision) {
        this.startPosition = startPotision;
    }

    public Position getGoalPosition() {
        return goalPosition;
    }

    public void setGoalPosition(Position goalPosition) {
        this.goalPosition = goalPosition;
    }

    /**
     * maze-Print void method, runs over the 2D-array and prints its containment
     */
    public void print() {
        for (int i = 0; i < build[0].length; i++) {
            for (int j = 0; j < build.length; j++) {
                if (j == startPosition.getX() && i == startPosition.getY())
                    System.out.print("S");
                else if (j == goalPosition.getX() && i == goalPosition.getY())
                    System.out.print("E");
                else if (build[j][i] == 1)
                    System.out.print("█");
                else if (build[j][i] == 0)
                    System.out.print("░");
            }
            System.out.println("");
        }
    }
    public int getRowSize(){
        return this.build[0].length;
    }
    public int getColSize(){
        return this.build.length;
    }

    public int getDataByIndex(int i, int j){
        return build[i][j];
    }

    public int[][] getBuild() {
        return build;
    }
}
