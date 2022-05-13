package work;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Gn {
	private Node startState, goalState;
    //constructor
	public Gn(ArrayList <Integer> start, ArrayList <Integer> goal) {
		this.startState = new Node(start, null,0, "start");
		this.goalState = new Node(goal, null,0, "end");
	}
	
	public Gn() {
		
	}
	
	//methods
	
	//la m�thode init permet de construire la population initiale
	public ArrayList<ArrayList<Integer>> init(int N, int generationSize) {
		ArrayList<ArrayList<Integer>> generation = new ArrayList <ArrayList<Integer>>();
		ArrayList <Integer> givenList = new ArrayList <Integer>(Arrays.asList(1, -1, 3, -3));
		
		for(int i=0; i<N; i++) {
			ArrayList <Integer> randomChromosome = new ArrayList <Integer>();
			Random rand = new Random();
			for (int j=0; j<generationSize; j++) {
				int randomElement = givenList.get(rand.nextInt(givenList.size()));
				randomChromosome.add(randomElement);
			}
		   // generation.add(valid(randomChromosome));
			generation.add(randomChromosome);
		}
		return generation;
	}
	
	
	//la m�thode fitness permet d��valuer chaque chromosome et rend une liste qui contient les fitness de tous les individus
	public ArrayList<Integer> fitness(ArrayList<ArrayList<Integer>> generation) {
		ArrayList <Integer> fitnessList = new ArrayList <Integer>();
		ArrayList <Integer> goalStateList = this.goalState.getStateList();
		int fitnessChromosome;
		for (ArrayList<Integer> chromosome : generation) {
			ArrayList<Integer> chromosomeNode = this.getNode(chromosome).getStateList();
			fitnessChromosome = 0;
			for(int i = 0; i< chromosomeNode.size(); i++) {
				if(chromosomeNode.get(i) == goalStateList.get(i)) {
					fitnessChromosome++;
				}
			}
			fitnessList.add(fitnessChromosome);
		}
		
		return fitnessList;
	}
	
	
	//cette m�thode va faire les mouvements possibles du chromosome
	public Node getNode(ArrayList <Integer> chromosome) {
		   Node currentState = this.startState;
		   Step p = new Step();
		   Node newState = new Node();
		   for(int move : chromosome) {
			   
			   if (move == -3) {
				   newState = p.moveUp(currentState);
				   if (newState != null) currentState = newState;
			   }
			   
			   if (move == 3) {
				   newState = p.moveDown(currentState);
				   if (newState != null) currentState = newState;
			   }
			   
			   if (move == -1) {
				   newState = p.moveLeft(currentState);
				   if (newState != null) currentState = newState;
			   }
			   
			   if (move == 1) {
				   newState = p.moveRight(currentState);
				   if (newState != null) currentState = newState;
			   } 
			   
		   }
		   return currentState;
	   }
	
	
	//la m�thode select permet de s�lectionner 2 chromosomes pour le croisement
	public ArrayList<ArrayList<Integer>> select(ArrayList <Integer> fitnessList, ArrayList<ArrayList<Integer>> generation){
		ArrayList<ArrayList<Integer>> sortedGeneration = new ArrayList<ArrayList<Integer>>();
		ArrayList <Integer> fitnessSorted = new ArrayList <Integer>();
		for(int i : fitnessList) {
			fitnessSorted.add(i);
		}
		Collections.sort(fitnessSorted, Collections.reverseOrder());
		int posMax = 0;
		for(int i : fitnessSorted) {
			posMax = fitnessList.indexOf(i);
			sortedGeneration.add(generation.get(posMax));
			fitnessList.set(posMax, 0);
		}
		return sortedGeneration;
	}
	
	//cr�er une paire d�enfants de la nouvelle g�n�ration en faisant du croisement selon pc
	public ArrayList<ArrayList<Integer>> croisement(ArrayList <Integer> parent1, ArrayList <Integer> parent2, double pc) {
		
		Random rand = new Random();
		int pointCroisement = 0;
		if(pc > rand.nextFloat(0, 1)) {
			pointCroisement = rand.nextInt(parent1.size());
			
			ArrayList <Integer> child1 = new ArrayList <Integer>();
			child1.addAll(parent1.subList(0, pointCroisement));
			child1.addAll(parent2.subList(pointCroisement, parent2.size()));
			
			ArrayList <Integer> child2 = new ArrayList <Integer>();
			child2.addAll(parent2.subList(0, pointCroisement));
			child2.addAll(parent1.subList(pointCroisement, parent1.size()));
			
			return new ArrayList<>(Arrays.asList(child1, child2));
		}
		return new ArrayList<>(Arrays.asList(parent1, parent2));
	}
	
	
	//appliquer la mutation sur chaque individu de la nouvelle g�n�ration selon pm
	public ArrayList <Integer> mutation(ArrayList <Integer> child, double pm){
		Random rand = new Random();
		for (int i = 0; i<child.size(); i++) {
			if(pm > rand.nextFloat(0, 1)) child.set(i, -1*child.get(i));
		}
		return child;
	}
	
	//cette m�thode va v�rifier la validit� du chromosome donn� en param�tre et supprimer les mouvemets non valides
	public ArrayList <Integer> getValid(ArrayList <Integer> child) {
		
		   ArrayList <Integer> validChild = new ArrayList <Integer>(); 
		   Node currentState = this.startState;
		   Step p = new Step();
		   Node newState = new Node();
	       int j = 0;
		   while(j < child.size()){
			   if (child.get(j) == -3) {
				   newState = p.moveUp(currentState);
				   if(newState != null) {
					   validChild.add(-3);
					   currentState = newState;
				   }
			   }
			   
			   if (child.get(j) == 3) {
				   newState = p.moveDown(currentState);
				   if(newState != null) {
					   validChild.add(3);
					   currentState = newState;
				   }
			   }
			   
			   if (child.get(j) == -1) {
				   newState = p.moveLeft(currentState);
				   if(newState != null) {
					   validChild.add(-1);
					   currentState = newState;
				   }
			   }
			   
			   if (child.get(j) == 1) {
				   newState = p.moveRight(currentState);
				   if(newState != null) {
					   validChild.add(1);
					   currentState = newState;
				   }
			   } 
			   j++;
		   }
		return validChild;
	}
	
	//l'algorithme principal
	public ArrayList<ArrayList<Integer>> application() {
		//initialisation des variables principales
		int N = 5; //number of generations
		int generationSize = 12;
		double pc = 0.3;
		double pm = 0.3;
		int maxIter = 100;
		int iter = 0;
		
		//d�finir les variables qu'on va utiliser dans pp
		ArrayList<Integer> fitness = new ArrayList <Integer>();
		ArrayList<ArrayList<Integer>> selected = new ArrayList<ArrayList<Integer>> ();
		ArrayList<ArrayList<Integer>> cc = new ArrayList<ArrayList<Integer>> ();
		ArrayList<Integer> child = new ArrayList<Integer> ();
		
		//pp
		//construire la population initiale
		ArrayList<ArrayList<Integer>> currentGeneration = this.init(N, generationSize);
		int bestFitness = 0;
		
		while(bestFitness < 9 && iter<maxIter) {
		    System.out.println("iter = "+iter);
			int i = 0;
			int j = 0;
			
			//calculer la fitness de la nouvelle g�n�ration afin de tester le test d'arret
			fitness = this.fitness(currentGeneration);
			bestFitness = Collections.max(fitness);
			selected = this.select(fitness, currentGeneration);
			System.out.println("     best current fitness = " + bestFitness);
			System.out.println("     selected generation = "+selected);
			if(bestFitness == 9) {
				break;
			}

			//sinon on constitue une nouvelle g�n�ration
			ArrayList<ArrayList<Integer>> newGeneration = new ArrayList<ArrayList<Integer>>();
			while(newGeneration.size()<N && i<selected.size() && j<selected.size()) {
				cc = this.croisement(selected.get(i), selected.get(j), pc);
				child = this.mutation(cc.get(0), pm);
				newGeneration.add(child);
				j ++;
				if(j == generationSize - 1) {
					i++;
					j=0;
				}
			}
			currentGeneration = newGeneration;
			
			iter ++;
		}
		
		return selected;
	}
	
	//cette m�thode permet de retourner la solution � l'interface afin de l'afficher
	public ArrayList <String> affichage(ArrayList<ArrayList<Integer>> currentGeneration){
		ArrayList <Integer> gbest = this.getValid(currentGeneration.get(0));
		System.out.println(gbest);
		ArrayList <String> movementsList = new ArrayList <String>();
		for(int i = 0; i<gbest.size(); i++) {
			switch(gbest.get(i)) {
			case -3 : movementsList.add("UP"); break;
			case  3 : movementsList.add("DOWN"); break;
			case -1 : movementsList.add("LEFT"); break;
			case  1 : movementsList.add("RIGHT"); break;
			}
		}
		System.out.println(movementsList);
		return movementsList;
	}
	
	//main just for test
	public static void main(String[] args) {
		/*ArrayList <Integer> startState = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 8, 4, 7, 0, 6, 5));
		ArrayList <Integer> goalState = new ArrayList <Integer>(Arrays.asList(1, 2, 3, 8, 0, 4, 7, 6, 5));*/
		/*ArrayList <Integer> startState = new ArrayList<Integer>(Arrays.asList(6, 2, 5, 1, 8, 0, 7, 4, 3));
		ArrayList <Integer> goalState = new ArrayList <Integer>(Arrays.asList(6, 0, 2, 1, 8, 5, 7, 4, 3));*/
		//ArrayList <Integer> goalState = new ArrayList <Integer>(Arrays.asList(6, 2, 5, 1, 8, 3, 7, 4, 0));
		ArrayList <Integer> startState = new ArrayList<Integer>(Arrays.asList(2, 8, 3, 1, 6, 4, 7, 0, 5));
		ArrayList <Integer> goalState = new ArrayList <Integer>(Arrays.asList(1, 2, 3, 8, 0, 4, 7, 6, 5));
		Gn algo = new Gn(startState, goalState);
		ArrayList<ArrayList<Integer>> solutions = algo.application(); 
		algo.affichage(solutions);
		 
		
	}	
	
}