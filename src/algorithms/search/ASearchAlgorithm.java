package algorithms.search;

import java.util.ArrayList;
import java.util.Queue;

/**
 * Created by zaks on 11/04/2017.
 * An abstract class for all search algorithms.
 * Passing the implementation of solve function to each algorithm
 */

public abstract class ASearchAlgorithm implements ISearchAlgorithm {
    protected String name;
    protected int numberOfNodesEvaluated;

    //abstract constructor
    ASearchAlgorithm() {}

    public abstract Solution solve (ISearchable domain);

    //algorithm name, will change after heritage
    public String getName() {
        return name;
    }

    public abstract int getNumberOfNodesEvaluated();

    /**
     * Before running the algorithm, reset searchable item's data, without previous data
     * @param domain
     */
    protected void resetMaze(ISearchable domain) {
        for (int i = 0; i < domain.getAllStates().size(); i++) {
            domain.getAllStates().get(i).setVisited(false);
            domain.getAllStates().get(i).setParentState(null);
        }
    }

    /**
     * When the algorithm finds the goal, it sets goal's data - visited and parent state
     * @param domain
     * @param curr
     * @param goal
     */
    protected void foundGoal(ISearchable domain,AState curr,AState goal){
        goal.setParentState(curr.getParentState());
        domain.setParentState(goal,curr.getParentState() );
        curr.setVisited(true);
        goal.setVisited(true);
        domain.setVisitedState(curr, true);
        domain.setVisitedState(goal, true);
    }

    /**
     * After iterating the searchable item and found the goal, we have the data to get the solution,
     * so we need to start with the goal and iterating threw parents to get to the start position.
     * later we will reverse the return value
     * @param domain
     * @param goal
     * @param start
     * @param reverseSol
     */
    protected ArrayList<AState> getSolutionReversed(ISearchable domain, AState goal,AState start, ArrayList<AState> reverseSol) {
        AState back = goal;
        boolean noSolution = false;

        while (!back.isEqual(start)) {//need to improve this check!!

            reverseSol.add(back);
            if (domain.getParent(back)==null){
                noSolution=true;
                break;
            }else{
                back = back.getParentState();
                //back = domain.getParent(back);
            }
        }
        if(!noSolution)
            reverseSol.add(start);
        else
            reverseSol.clear();
        return reverseSol;
    }

    /**
     * after getting the solution reversed when running backwards from the goal, we need to reverse the outcome
     * to get the real solution
     * @param reverseSol
     * @param solutionPath
     */
    protected void reverseArray(ArrayList<AState> reverseSol, ArrayList<AState> solutionPath) {
        for (int i = reverseSol.size()-1; i >=0; i--) {
            solutionPath.add(reverseSol.get(i));
        }
    }
}
