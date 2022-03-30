package work;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class BFS {
    private int moveCounter = 0;
    private Node startState, goalState, currentState = new Node();
    private ArrayList<Node> fileClosed = new ArrayList<Node>();
    private ArrayList<Node> fileOpen = new ArrayList<Node>();
    private ArrayList<Node> allSolutionsList = new ArrayList<Node>();
    private int maxiter = 1000;

//constructor
public BFS(ArrayList <Integer> start, ArrayList <Integer> goal) {
    startState = new Node(start, null,0);
    goalState = new Node(goal, null,0);
    fileOpen.add(startState);
}

//les m�thodes
//la m�thode succesor d�finit l��tat r�sultant de l�ex�cution d�une des quatres actions suivantes:
private void succesor(Node currentState) {
    Node newState = new Node();
    Step p = new Step();
    
    //haut
    newState = p.moveUp(currentState);
    if (newState != null) {
    if(goalState.equal(newState)) allSolutionsList.add(newState);
     else fileOpen.add(newState);
    }	
    
    //droite
    newState = p.moveRight(currentState);
    if (newState != null) {
    	if(goalState.equal(newState)) allSolutionsList.add(newState);
    	else fileOpen.add(newState);	
    }
  
   //bas  
    newState = p.moveDown(currentState);
    if (newState != null) {
    	if(goalState.equal(newState)) allSolutionsList.add(newState);
    	else fileOpen.add(newState);	
    }
  
    //gauche
    newState = p.moveLeft(currentState);
    if (newState != null) {
    	if(goalState.equal(newState)) allSolutionsList.add(newState);
    	else fileOpen.add(newState); 
    }
}

//la m�thode search 
public void search() {
  boolean exist = false;
  Node tmpNode = new Node();
  currentState = fileOpen.remove(0);
  fileClosed.add(currentState);
  succesor(currentState);
 int iter = 0;
while (iter < maxiter) {
	iter++;	
	
 if(!fileOpen.isEmpty()) {
  currentState = fileOpen.remove(0);	
//parcourir la file et v�rifier si l'�l�ment qu'on veut d�velopper n'existe pas ds la liste
 for (int i = 0; i < fileClosed.size(); i++) {
    tmpNode = fileClosed.get(i);
    if (currentState.equals(tmpNode)) {
        exist = true;
        break;
    }                        
 }
//si l'�l�ment n'existe pas dans ferm�e, on peut d�velopper ses successuers
 if (!exist) {
	fileClosed.add(currentState);
	succesor(currentState);
    moveCounter++;
 }
 }
//une exception sera �lev�e quand la pile ne contient plus de noeuds 
    else{
    System.out.println("file vide!");
        break;
    }
}

//affichage des solutions si on a trouv� au moins une 
if (allSolutionsList.size() !=0) affichage_success(); 
else System.out.println("aucune solution trouv�e");
System.out.println("nombre d'�tapes : " + moveCounter);
}

//la m�thode d'affichage des solutions �tape par �tape
public void affichage_success() {
	System.out.println("les solutions trouv�es par BFS : ");
	//ce hashset nous permet de v�rifier si notre algorithme a g�n�r� deux solutions identiques
	Set<ArrayList<Node>> hashSet = new HashSet<ArrayList<Node>>();
	int step ,i, numSol=0;
	ArrayList<Node> solution = new ArrayList<Node>();
	for(Node state : allSolutionsList) {
		solution.clear();
		do {
			solution.add(state);
			state = state.getPredecessor();
		}while(state !=null);
		
		//si la fonction add du hashset nous retourne faux , cela veut dire que la solution existe d�j�
		if(!hashSet.add(solution)) {
			System.out.println("la solution existe d�j� , bad algorithm :((");
			break;
		}
		else {
		//l'affichage de la solution
			System.out.println("solution n� : "+numSol);
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
