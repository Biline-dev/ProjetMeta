package com.puzzle.algorithm;
import com.puzzle.heuristic.ManhattanDistanceHeuristic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

public class Main {

    public static ArrayList<Integer> solvedBoard = new ArrayList<>();
    public static ArrayList<Integer> initialBoard = new ArrayList<>();
    public static AStarSolver solution;

    public static void main(String[] args) throws IOException {

       /* String initial= "123847065";
        String goal = "123456780";
        for(int i=0; i<9;i++){
            initialBoard.add(Character.getNumericValue(initial.charAt(i)));
            solvedBoard.add(Character.getNumericValue(goal.charAt(i)));
        }
        Random r1 = new Random();
        for (int i = initialBoard.size() - 1; i >= 1; i--) {
            // swapping current index value
            // with random index value
            Collections.swap(initialBoard, i, r1.nextInt(i + 1));
        }
        Iterator itr = initialBoard.iterator();

        while (itr.hasNext()) {
            System.out.print(itr.next());
        }*/
        String initial= "152703846";
        String goal = "123456780";
        for(int i=0; i<9;i++){
            initialBoard.add(Character.getNumericValue(initial.charAt(i)));
            solvedBoard.add(Character.getNumericValue(goal.charAt(i)));
        }
        solution = new AStarSolver(initialBoard, solvedBoard, 1);
        solution.AStar();

    }
}
