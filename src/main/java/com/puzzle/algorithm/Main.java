package com.puzzle.algorithm;
import com.puzzle.heuristic.ManhattanDistanceHeuristic;

import java.util.ArrayList;

public class Main {

    public static ArrayList<Integer> solvedBoard = new ArrayList<>();
    public static ArrayList<Integer> initialBoard = new ArrayList<>();
    public static AStarSolver solution;
    public static void main(String[] args){

        String initial= "380476215";
        String goal = "123456780";
        for(int i=0; i<9;i++){
            initialBoard.add(Character.getNumericValue(initial.charAt(i)));
            solvedBoard.add(Character.getNumericValue(goal.charAt(i)));
        }

        solution = new AStarSolver(initialBoard, solvedBoard, 3);
        solution.AStar();

    }
}
