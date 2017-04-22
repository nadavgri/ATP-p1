package algorithms.search;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by zaks on 11/04/2017.
 * classic DFS - adj-s stack for reached states.
 */

public class DepthFirstSearch extends ASearchAlgorithm{
    private Stack<AState> stack;

    public DepthFirstSearch() {
        this.name = "Depth First Search";
        stack = new Stack<>();
        this.numberOfNodesEvaluated = 0;
    }

    /**
     *
     * @param domain - searchable item to search on
     * @return Solution - arraylist of states from start to goal
     */
    @Override
    public Solution solve(ISearchable domain) {

        ArrayList<AState> solutionPath = new ArrayList<>();

        resetMaze(domain);
        AState start = domain.getStartPosition();
        AState goal = domain.getGoalPosition();

        //start algorithm with start state
        stack.push(start);
        start.setVisited(true);
        domain.setVisitedState(start, true);

        //searching on graph
        while (!stack.isEmpty()){
            AState curr = stack.pop();
            curr.setVisited(true);
            domain.setVisitedState(curr, true);
            numberOfNodesEvaluated++;
            if (curr.isEqual(goal)){
                foundGoal(domain, curr, goal);
                break;
            }
            else{
                ArrayList<AState> adj = domain.getAllPossibleStates(curr);
                //running on adj of any reached state and adds to stack
                for (AState state: adj) {
                    if (!domain.getVisited(state) && !stack.contains(state)){
                        state.setParentState(curr);
                        domain.setParentState(state,curr);
                        stack.push(state);

                    }
                }
            }
        }
        //get solution path
        ArrayList<AState> reverseSol = new ArrayList<>();
        reverseSol = getSolutionReversed(domain, goal, start, reverseSol);
        reverseArray(reverseSol,solutionPath);

        //creates new solution and pass important data to it
        return new Solution( numberOfNodesEvaluated, solutionPath);
        //return sol;
    }

    public int getNumberOfNodesEvaluated(){
        return this.numberOfNodesEvaluated;
    }

}
