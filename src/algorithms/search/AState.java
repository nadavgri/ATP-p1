package algorithms.search;

/**
 * Created by zaks on 11/04/2017.
 *  Generic state - can be a position in a maze or node in a tree or anything.
 * Contains all needed data for the search algorithm
 * MazeState will override the generic construction and change it to Position,
 * as should be in our maze
 */
public abstract class AState {
    protected Object currState;
    protected AState parentState;
    protected boolean visited;
    protected double priority;
    protected int time;

    //constructor without priority of a state. gives default priority of 0
    public AState(Object currState) {
        this.currState = currState;
        this.parentState=null;
        this.visited=false;
        this.priority=0;
    }

    //constructor with priority
    public AState(Object currState, double priority) {
        this.currState = currState;
        this.parentState=null;
        this.visited=false;
        this.priority = priority;
    }

    /*
     * getters and setters
     */
    public abstract Object getCurrState();

    public AState getParentState() {
        return parentState;
    }
    public void setParentState(AState parentState) {
        this.parentState = parentState;
    }

    public boolean isVisited() {
        return this.visited;
    }
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public double getPriority() {
        return priority;
    }

    public void setPriority(double priority) {
        this.priority = priority;
    }

    public String toString(){
        return this.getCurrState().toString();
    }
    public abstract boolean isEqual(AState other);
    @Override
    public abstract boolean equals(Object other);

    public int getTime() {
        return time;
    }
    public void setTime(int time) {
        this.time = time;
    }
}
