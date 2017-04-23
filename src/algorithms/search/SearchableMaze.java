package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import java.util.ArrayList;


/**
 * Created by zaks on 11/04/2017.
 * This class is in charge of converting a maze into a searchable maze, by the
 * interface ISearchable, and converting maze's positions into maze states,
 * as needed for the algorithms to solve.
 */

public class SearchableMaze implements ISearchable{
    private Maze maze;
    private MazeState start;
    private MazeState goal;
    private MazeState[][] newMaze;

    //constructor
    public SearchableMaze(Maze maze) {
        this.maze = maze;
        this.newMaze = new MazeState[maze.getColSize()][maze.getRowSize()];
        for (int i = 0; i < maze.getColSize(); i++) {
            for (int j = 0; j < maze.getRowSize() ; j++) {
                //creating a new maze state from the maze positions - with priority
                newMaze[i][j] = new MazeState(maze, new Position(i,j), maze.getDataByIndex(i,j));
            }
        }
        this.start = new MazeState(maze, maze.getStartPosition(), 0);
        this.goal = new MazeState(maze, maze.getGoalPosition(), 0);
    }

    public AState[][] getMazeStates() {
        return newMaze;
    }

    /**
     * converting the 2-d array to Arraylist, as requested at the interface ISearchable
     * @return arraylist of all maze's states
     */
    public ArrayList<AState> getAllStates(){
        ArrayList<AState> struct = new ArrayList<>();
        for (int i = 0; i < newMaze.length; i++) {
            for (int j = 0; j < newMaze[0].length; j++) {
                struct.add(newMaze[i][j]);
            }
        }
        return struct;
    }

    /**
     * defines legal steps in the maze from a current state
     * this maze allows crosses
     * @param curr
     * @return an array list of all possible steps moving forward from curr
     */
    @Override
    public ArrayList<AState> getAllPossibleStates(AState curr) {
        ArrayList<AState> possibleSteps = new ArrayList<>();
        MazeState m = (MazeState) curr;
        for (int i = -1; i <=1 ; i++) {
            for (int j = -1; j <=1 ; j++) {
                try{
                    int currX = ((Position)m.getCurrState()).getX();
                    int currY = ((Position)m.getCurrState()).getY();
                    if (!(i==0 && j == 0) && !(i!=0 && j!=0)){
                        if (this.maze.getBuild()[i+ currX][j+currY]==0 && !this.newMaze[i+ currX][j+currY].isVisited()){
                            possibleSteps.add(newMaze[i+currX][j+currY]);
                        }
                    }
                }catch (Exception e){
                    continue;
                }
            }
        }
        return possibleSteps;
    }

    @Override
    public void setVisitedState(AState state, boolean value) {
        ArrayList<AState> struct = getAllStates();
        int index = struct.indexOf(state);
        AState tmp = struct.get(index);
        tmp.setVisited(value);
    }

    @Override
    public void setParentState(AState son, AState parent) {
        ArrayList<AState> struct = getAllStates();
        int index = struct.indexOf(son);
        AState tmp = struct.get(index);
        tmp.setParentState(parent);
    }

    @Override
    public AState getParent(AState son) {
        ArrayList<AState> struct = getAllStates();
        int index = struct.indexOf(son);
        return  struct.get(index).getParentState();
    }

    @Override
    public boolean getVisited(AState state) {
        ArrayList<AState> struct = getAllStates();
        int index = struct.indexOf(state);
        return  struct.get(index).isVisited();
    }

    @Override
    public AState getStartPosition() {
        return this.start;
    }

    @Override
    public AState getGoalPosition() {
        return this.goal;
    }

    /**
     *
     * @param mX - current maze state - x axis values
     * @param mY - current maze state - y axis values
     * @return priority by position from start point
     * Cartesian destination calculate from Start state to current
     */
    private double destFromStart(int mX, int mY){
        return Math.sqrt(Math.pow((double)mX-this.maze.getStartPosition().getX(),2)+Math.pow((double)mY-this.maze.getStartPosition().getY(),2));
    }

    //get original maze, as a 2-d array
    public Maze getMaze() {
        return maze;
    }

}
