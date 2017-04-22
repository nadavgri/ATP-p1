package algorithms.search;

/**
 * Created by zaks on 11/04/2017.
 *     defines what needed to be a search algorithm:
 *     solving method, name of the algorithm and count nodes evaluation
 */

public interface ISearchAlgorithm {
    Solution solve(ISearchable domain);
    String getName();
    int getNumberOfNodesEvaluated();

}
