package algorithms.search;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by zaks on 11/04/2017.
 * Works like Breadth First Search algorithm, but uses a priority queue, that
 * ordering by a comparator of the data member "int priority".
 */
public class BestFirstSearch extends BreadthFirstSearch {

    private PriorityQueue<AState> pQ;

    public BestFirstSearch() {
        this.name = "Best First Search";
        pQ = new PriorityQueue<>(priority);
    }

    public Solution solve (ISearchable domain){
        return solve(domain,domain.getStartPosition(),domain.getGoalPosition(), pQ);
    }

    //priority comparator - lambda function
    private static Comparator<AState> priority = (s1, s2) -> (int) (s1.getPriority() - s2.getPriority());
}
