package com.puzzle.heuristic;
import com.puzzle.algorithm.Heuristic;
import java.util.ArrayList;

/**
 * This heuristic count the manhattan Distance between the current state to the goal
 * @author beline
 *
 */

public class ManhattanDistanceHeuristic implements Heuristic {
    @Override
    public int getHeuristic(ArrayList<Integer> currentBoard, ArrayList<Integer> targetBoard) {
        int manhattanSum = 0;
        for(int i = 0; i < currentBoard.size(); i++) {
            int currentBoardValue = currentBoard.get(i);
            int solvedBoardValue = targetBoard.get(i);
            if(currentBoardValue != solvedBoardValue && currentBoardValue != 0)
                manhattanSum += calculateManhattanDistance(currentBoard, i, currentBoardValue);
        }
        return manhattanSum;
    }

    public static int calculateManhattanDistance(ArrayList<Integer> currentBoard, int index, int value) {
        int currentRow = (index / 3) + 1;
        int currentColumn = (index % 3) + 1;
        int targetIndex = currentBoard.indexOf(value);
        int targetRow = (targetIndex / 3) + 1;
        int targetColumn = (targetIndex % 3) + 1;

        return Math.abs(targetColumn - currentColumn) + Math.abs(targetRow - currentRow);
    }

}
