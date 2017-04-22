package algorithms.search;

import java.util.ArrayList;

/**
 * Created by zaks on 11/04/2017.
 * solution form - for algorithm output
 */

public class Solution {

    private int numberOfNodesEvaluation;
    private ArrayList<AState> solutionPath;

    //each algorithm needs to create a new solution with this parameters
    public Solution(int numberOfNodesEvaluation, ArrayList<AState> solutionPath) {
        this.numberOfNodesEvaluation = numberOfNodesEvaluation;
        this.solutionPath = solutionPath;

    }

    // implemented also in ASearchAlgorithm and the test file uses this one.
    public int getNumberOfNodesEvaluation() {
        return numberOfNodesEvaluation;
    }


    public ArrayList<AState> getSolutionPath() {
        return solutionPath;
    }

}
