package algorithms.search;


import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

/**
 * Created by zaks on 11/04/2017.
 * a specific type of state - contains position {x,y} and the maze itself
 */

public class MazeState extends AState {
    protected Maze maze;
    private Position currState;
    private int data;//can be 0 or 1


    //constructor without priority of a state. gives default priority
    public MazeState(Maze maze,Position currState, int data) {
        super(currState);
        this.maze = maze;
        this.data = data;
        this.currState = currState;
    }

    //constructor with priority
    public MazeState(Maze maze, Position currState, double priority, int data) {
        super(currState, priority);
        this.maze = maze;
        this.data=data;
        this.currState = currState;

    }

    @Override
    public Object getCurrState() {
        return currState;
    }

    public int getData() {
        return data;
    }


    public boolean isEqual(AState other) {
        return (this.currState.getY() == ((MazeState)other).currState.getY())
                && (this.currState.getX() == ((MazeState)other).currState.getX());
    }

    @Override
    public boolean equals(Object other) {
        return this.isEqual((AState)other);
    }


}
