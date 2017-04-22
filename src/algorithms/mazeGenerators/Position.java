package algorithms.mazeGenerators;

/**
 * Created by zaks on 11/04/2017.
 * Position class, contains x&y coordinate on the Maze array.
 */
public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    /**
     * toString method overrides the original method to return specific pattern of Position class.
     * @return
     */
    public String toString(){
        return "{" + this.getX() + "," + this.getY() +"}";
    }
}
