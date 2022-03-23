package com.puzzle.heuristic;
import com.puzzle.algorithm.Heuristic;
import java.util.ArrayList;

    public class MisplacedTilesHeuristic implements Heuristic {
        @Override
        public int getHeuristic(ArrayList<Integer> currentBoard,ArrayList<Integer> targetBoard ) {
            int misplacedCounter = 0;
            for(int i = 0; i < currentBoard.size(); i++) {
                int currentBoardValue = currentBoard.get(i);
                int solvedBoardValue = targetBoard.get(i);
                if(currentBoardValue != solvedBoardValue && currentBoardValue != 0)
                    misplacedCounter++;
            }
            return misplacedCounter;
        }
    }
