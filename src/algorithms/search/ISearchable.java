package algorithms.search;

import java.util.ArrayList;

/**
 * Created by zaks on 11/04/2017.
 * defines what needed to be searchable:
 * start, goal and the all struct.
 */

public interface ISearchable {
    AState getStartPosition(); //return start state of the searchable item
    AState getGoalPosition();  //return end (goal) state of the searchable item
    ArrayList<AState> getAllStates(); //return the struct of the searchable item as an arraylist
    ArrayList<AState> getAllPossibleStates(AState curr); //return possible steps of a specific state
    void setVisitedState(AState state, boolean value); //sets visited data of a specific state
    void setParentState(AState son, AState parent); //sets parent data of a specific state
    AState getParent(AState son); //return parent data of a specific state
    boolean getVisited(AState state); //return visited data of a specific state
}
