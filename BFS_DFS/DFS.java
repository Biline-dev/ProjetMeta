package work;

import java.util.Stack;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DFS {
    private Stack<Node> stackOpen = new Stack<Node>(); 
    private Node startState, goalState, currentState = new Node();
    private ArrayList<Node> fileClosed = new ArrayList<Node>();
    private int depthMax = 4;
    private int numberOfExploredNodes = 1;
    private int  numberOfDevelopedNodes = 1;
    private boolean found = false;

//constructor
public DFS(ArrayList <Integer> start, ArrayList <Integer> goal, int depthMax) {
    startState = new Node(start, null,0, "start");
    goalState = new Node(goal, null,0, "end");
    this.depthMax = depthMax;
    stackOpen.push(startState);
}

//les méthodes
//la méthode succesor définit l’état résultant de l’exécution d’une des quatres actions suivantes:
private void succesor(Node currentState) {
    Node newState = new Node();
    Step p = new Step();
    
    //haut
    newState = p.moveUp(currentState);
    if (newState != null) {
    if(goalState.equal(newState)) found = true;
     else{
    	 stackOpen.push(newState);
    	 numberOfExploredNodes++;
     }
    }	
    
    //droite
   newState = p.moveRight(currentState);
    if (newState != null) {
    	if(goalState.equal(newState)) found = true;
    	else {
    		stackOpen.push(newState);
    		 numberOfExploredNodes++;
    	}
    }
  
   //bas  
    newState = p.moveDown(currentState);
    if (newState != null) {
    	if(goalState.equal(newState)) found = true;
    	else {
    		stackOpen.push(newState);	
    		 numberOfExploredNodes++;
    	}
    }
  
    //gauche
    newState = p.moveLeft(currentState);
    if (newState != null) {
    	if(goalState.equal(newState)) found = true;
    	else {
    		stackOpen.push(newState);
    		numberOfExploredNodes++;
    	}
    }
}

//la méthode search 
public Result search() {
  boolean exist = false;
  Node tmpNode = new Node();
  currentState = stackOpen.pop();
  fileClosed.add(currentState);
  succesor(currentState);
  
while (!found) {
try {
	
    if(!stackOpen.empty()) {
    	do {
        	currentState = stackOpen.pop();
    	}while(currentState.getDepth()>depthMax);
    }
//parcourir la file et vérifier si l'élément qu'on veut développer n'existe pas ds la liste
for (int i = 0; i < fileClosed.size(); i++) {
    tmpNode = fileClosed.get(i);
    if (currentState.equals(tmpNode)) {
        exist = true;
        break;
    }                        
}
//si l'élément n'existe pas dans fermée, on peut développer ses successuers
if (!exist) {
	fileClosed.add(currentState);
	succesor(currentState);
	numberOfDevelopedNodes++;
}

}
//une exception sera élevée quand la pile ne contient plus de noeuds 
catch (Exception e) {
    System.out.println("pile vide!");
        break;
    }
}

//retourner le résultat si on a trouvé une solution
if (found) {
Stack<String> movesForTheShortestPath =  new Stack<String>(); 
Node state = currentState;
	do {
		movesForTheShortestPath.push(state.getMove());
		state = state.getPredecessor();	 
	}while(state !=null); 
return new Result(movesForTheShortestPath, numberOfExploredNodes, numberOfDevelopedNodes, movesForTheShortestPath.size());
}
else return null;
}
}
