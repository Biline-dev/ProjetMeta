package com.puzzle.algorithm;
import java.util.Stack;

public class Result {

    private Stack<String> movesForTheShortestPath;
    private int numberOfExploredNodes;
    private int  numberOfDevelopedNodes;
    private int shortestPath;

    public Result(Stack<String> movesForTheShortestPath,
                  int numberOfExploredNodes,
                  int numberOfDevelopedNodes,
                  int shortestPath) {
        
        this.movesForTheShortestPath = movesForTheShortestPath;
        this.numberOfExploredNodes = numberOfExploredNodes;
        this.numberOfDevelopedNodes = numberOfDevelopedNodes;
        this.shortestPath= shortestPath;
    }

    public Stack<String> getMovesForTheShortestPath() {return movesForTheShortestPath;}
    public int getNumberOfExploredNodes() {return numberOfExploredNodes;}
    public int getNumberOfDevelopedNodes() {return numberOfDevelopedNodes;}
    public int getShortestPath() {return shortestPath;}

    public void display(){
        System.out.println(" moves are: "+this.getMovesForTheShortestPath());
        System.out.println("the number of explored nodes: "+this.getNumberOfExploredNodes());
        System.out.println("the number of developed nodes: "+this.getNumberOfDevelopedNodes());
        System.out.println("the shortest path to reach the goal: "+this.getShortestPath());
    }
}
