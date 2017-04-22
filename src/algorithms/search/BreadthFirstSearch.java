package algorithms.search;


import java.util.*;

/**
 * Created by zaks on 11/04/2017.
 * classic BFS - queue of adj-s of reached state.
 */
public class BreadthFirstSearch extends ASearchAlgorithm {
    private PriorityQueue<AState> queue;

    public BreadthFirstSearch() {
        this.name = "Breadth First Search";
        queue = new PriorityQueue<>(zeroPriority);
        this.numberOfNodesEvaluated = 0; //initializing to 0
    }

    //wrap function, to send the correct queue, with it's comparator
    public Solution solve(ISearchable domain) {
        return solve(domain, domain.getStartPosition(), domain.getGoalPosition(), queue);
    }

    /**
     *
     * @param domain - searchable item to search on
     * @param start - start of searching
     * @param goal - end of searching
     * @param queue - for all states waiting to evaluate
     * @return Solution - arraylist of states from start to goal
     */
    public Solution solve(ISearchable domain, AState start, AState goal, PriorityQueue<AState> queue) {
        int time = 0;
        numberOfNodesEvaluated = 0;
        ArrayList<AState> solutionPath = new ArrayList<>();
        resetMaze(domain);

        //start algorithm with start state
        queue.add(start);
        start.setVisited(true);
        domain.setVisitedState(start, true);
        start.setTime(time);
        time++;

        //searching on graph
        while (!queue.isEmpty()) {
            AState curr = queue.poll();
            curr.setVisited(true);
            domain.setVisitedState(curr, true);
            numberOfNodesEvaluated++;

            if (curr.isEqual(goal)) {
                foundGoal(domain, curr, goal);
                break;
            } else {
                ArrayList<AState> adj = domain.getAllPossibleStates(curr);
                //running on adj of any reached state and adds to queue
                for (AState state : adj) {
                    if (!domain.getVisited(state) && !queue.contains(state)) {
                        state.setParentState(curr);
                        domain.setParentState(state, curr);
                        state.setTime(time);
                        time++;
                        queue.add(state);
                    }
                }
            }
        }

        //get solution path
        ArrayList<AState> reverseSol = new ArrayList<>();
        reverseSol = getSolutionReversed(domain, goal, start, reverseSol);
        reverseArray(reverseSol, solutionPath);

        //creates new solution and pass important data to it
        return new Solution(numberOfNodesEvaluated, solutionPath);
        //return sol;
    }

    public int getNumberOfNodesEvaluated() {
        return this.numberOfNodesEvaluated;
    }

    /* zero priority comparator - for BFS only
     * because the adj queue at BFS algorithm works only by fifo, comparator by time arrival
     */
    PriorityQueue<AState> zeroPriority = new PriorityQueue<AState>(new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            AState u = (AState) o1;
            AState v = (AState) o2;
            return Integer.compare(new Integer(u.getTime()), new Integer(v.getTime()));
        }

    });
}


