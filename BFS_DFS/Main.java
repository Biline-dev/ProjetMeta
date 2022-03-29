package work;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
    //int startState[][] = {{6,2,5}, {1,8,0}, {7,4,3}}, goalState[][] = {{6,0,2}, {1,8,5}, {7,4,3}};
    int startState[][] = {{2,8,3}, {1,6,4}, {7,0,5}}, goalState[][] = {{1,2,3}, {8,0,4}, {7,6,5}};
	//int startState[][] = {{1,2,3}, {0,4,6}, {7,5,8}}, goalState[][] = {{1,2,3}, {4,5,6}, {7,8,0}};
    BFS bfs = new BFS(startState, goalState);
    bfs.search();
    DFS dfs = new DFS(startState, goalState);
    dfs.search();
}
}




