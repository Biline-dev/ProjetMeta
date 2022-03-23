package com.puzzle.algorithm;
import com.puzzle.heuristic.ManhattanDistanceHeuristic;

import java.util.ArrayList;

public class Main {

    public static ArrayList<Integer> solvedBoard = new ArrayList<>();
    public static ArrayList<Integer> initialBoard = new ArrayList<>();
    public static AStarSolver solution;
    public static void main(String[] args){

        solvedBoard.add(1);
        solvedBoard.add(2);
        solvedBoard.add(3);
        solvedBoard.add(8);
        solvedBoard.add(0);
        solvedBoard.add(4);
        solvedBoard.add(7);
        solvedBoard.add(6);
        solvedBoard.add(5);


        initialBoard.add(2);
        initialBoard.add(8);
        initialBoard.add(3);
        initialBoard.add(1);
        initialBoard.add(6);
        initialBoard.add(4);
        initialBoard.add(7);
        initialBoard.add(0);
        initialBoard.add(5);

        solution = new AStarSolver(initialBoard, solvedBoard, 1);
        State current =solution.AStar();
        System.out.println("Le parcours: "+solution.getNumberStates());
        current.display();

    }
}
