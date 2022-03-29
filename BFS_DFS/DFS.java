package work;

import java.util.Stack;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DFS {
    private int moveCounter = 0;
    private Stack<Node> stackOpen = new Stack<Node>(); 
    private Node startState, goalState, currentState = new Node();
    private ArrayList<Node> fileClosed = new ArrayList<Node>();
    private int lengthMax = 4;
    private ArrayList<Node> allSolutionsList = new ArrayList<Node>();

//constructor
public DFS(int start[][], int goal[][]) {
    startState = new Node(start, null,0);
    goalState = new Node(goal, null,0);
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
    if(goalState.equal(newState)) allSolutionsList.add(newState);
     else stackOpen.push(newState);
    }	
    
    //droite
    newState = p.moveRight(currentState);
    if (newState != null) {
    	if(goalState.equal(newState)) allSolutionsList.add(newState);
    	else stackOpen.push(newState);	
    }
  
   //bas  
    newState = p.moveDown(currentState);
    if (newState != null) {
    	if(goalState.equal(newState)) allSolutionsList.add(newState);
    	else stackOpen.push(newState);	
    }
  
    //gauche
    newState = p.moveLeft(currentState);
    if (newState != null) {
    	if(goalState.equal(newState)) allSolutionsList.add(newState);
    	else stackOpen.push(newState);
    }
}

//la méthode search 
public void search() {
  boolean exist = false;
  Node tmpNode = new Node();
  currentState = stackOpen.pop();
  fileClosed.add(currentState);
  succesor(currentState);
  
while (true) {
try {
	
    if(!stackOpen.empty()) {
    	do {
        	currentState = stackOpen.pop();
    	}while(currentState.getDepth()>lengthMax);

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
    moveCounter++;
}

}
//une exception sera élevée quand la pile ne contient plus de noeuds 
catch (Exception e) {
    System.out.println("pile vide!");
        break;
    }
}

//affichage des solutions si on a trouvé au moins une 
if (allSolutionsList.size() !=0) affichage_success(); 
System.out.println("nombre d'étapes : " + moveCounter);
}

//la méthode d'affichage des solutions étape par étape
public void affichage_success() {
	System.out.println("les solutions trouvées par DFS : ");
	//ce hashset nous permet de vérifier si notre algorithme a généré deux solutions identiques
	Set<ArrayList<Node>> hashSet = new HashSet<ArrayList<Node>>();
	int step ,i, numSol=0;
	ArrayList<Node> solution = new ArrayList<Node>();
	for(Node state : allSolutionsList) {
		solution.clear();
		do {
			solution.add(state);
			state = state.getPredecessor();
		}while(state !=null);
		
		//si la fonction add du hashset nous retourne faux , cela veut dire que la solution existe déjà
		if(!hashSet.add(solution)) {
			System.out.println("la solution existe déjà , bad algorithm :((");
			break;
		}
		else {
		//l'affichage de la solution
			System.out.println("solution n° : "+numSol);
			numSol++;
			step = 0;
			i = solution.size()-1;
			while(i>=0) {
				System.out.println("step"+step+" : \n"+solution.get(i));
				i--;
				step++;		
		}
	}
	System.out.println("-------------------------------");		
	}
}

}