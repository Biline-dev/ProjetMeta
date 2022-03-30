package work;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
	/*ArrayList <Integer> startState = new ArrayList<Integer>(Arrays.asList(6, 2, 5, 1, 8, 0, 7, 4, 3));
	ArrayList <Integer> goalState = new ArrayList <Integer>(Arrays.asList(6, 0, 2, 1, 8, 5, 7, 4, 3));*/
		
	/*ArrayList <Integer> startState = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 0, 4, 6, 7, 5, 8));
	ArrayList <Integer> goalState = new ArrayList <Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 0));*/
		
	ArrayList <Integer> startState = new ArrayList<Integer>(Arrays.asList(2, 8, 3, 1, 6, 4, 7, 0, 5));
	ArrayList <Integer> goalState = new ArrayList <Integer>(Arrays.asList(1, 2, 3, 8, 0, 4, 7, 6, 5));
		


     BFS bfs = new BFS(startState, goalState);
    bfs.search();
    DFS dfs = new DFS(startState, goalState);
    dfs.search(); 	
}
}
