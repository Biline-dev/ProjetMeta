package com.puzzle.algorithm;
import java.util.ArrayList;

/**
 * <p>
 * Heuristics must have a cost method.
 * </p>
 * @author beline
 *
 */
public interface Heuristic {
    int getHeuristic(ArrayList<Integer> board, ArrayList<Integer> targetBoard );
}
