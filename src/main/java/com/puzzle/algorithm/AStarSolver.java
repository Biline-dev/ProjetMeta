package com.puzzle.algorithm;
import com.puzzle.heuristic.*;

import java.util.*;

/**
 * @author : beline
 * @description : Implementation of A* algorithm to solve 8-puzzle
 *
 * <p>
 * Uses a priority queue to ensure the least costing state is always at the front of
 * the frontier to be expanded upon.
 * </p>
 *
 */
public class AStarSolver extends Util {

    private ArrayList<Integer> initialBoard;
    private Heuristic heuristic;
    private PriorityQueue<State> open;
    private Set<State>  closed;
    private int numberStates = 0;


    /**
     * <p>
     * New A* Search creates new lists and sets the goal.
     * Also determines what 'heuristic mode' the A* Search should use.
     * </p>
     *
     * @param initialBoard
     * @param goal
     */
    public AStarSolver(ArrayList<Integer> initialBoard, ArrayList<Integer> goal, int heuristicMode) {
        this.initialBoard=initialBoard;
        this.target = goal;
        open = new PriorityQueue<State>();
        open = new PriorityQueue<>();
        closed = new HashSet<>(181440);
        defineMode(heuristicMode);
    }

    /**
     * <p>Sets the heuristic to either Manhattan or Tile based.
     * Updates a String to be used in informing the user of
     * which heuristic is under use. <br />
     * Default is Manhattan Distance.
     * </p>
     * @param mode
     */
    private void defineMode(int mode) {
        switch (mode) {
            case (1):
                heuristic = new ManhattanDistanceHeuristic();
                break;
            case (2):
                heuristic = new MisplacedTilesHeuristic();
                break;
            case (3):
                heuristic = new EuclideanDistanceHeuristic();
                break;
            default:
                heuristic = new ManhattanDistanceHeuristic();
        }
    }

    public  State AStar(){
        boolean solutionFound = false;
        int minimumRemainingCostToTarget= heuristic.getHeuristic(initialBoard, target);
        State source = new State( initialBoard,null,0, minimumRemainingCostToTarget);

        open.add(source);
        while(!open.isEmpty()){
            source.display();
            System.out.print("\n");
            State currentState = open.poll();

            //add ths state with minimum sumCost to the closed list.
            closed.add(currentState);
            if (currentState.getBoard().equals(target)) {
                return currentState;
            }
            numberStates++;

            //remove it from the open list and add it to the closed list.
            this.removeFromOpen(currentState);
            /*
             * Search through possible steps (Up, left, right, down) of empty
             * tiles and find the current state's children to be explored next
             */
            this.findChildren(currentState);
        }
            /*
            * If by the end of this while loop, no solution has been found, inform
            * the user
            */

        if (!solutionFound) {
            System.out.println("No solution for this puzzle");
        }
        return null;
    }

    @Override
    public void movement(int zeroIndex, int direction, State current) {
        if(rulesForMovement(zeroIndex, direction)){
            //Create a new child based on the current state
            State child = createChild(current, zeroIndex, direction, heuristic, target);
            /*
             * If the child state is not in the open or closed list, we haven't
             * explored it at all yet, so add to the open list.
             */
            if(!inClosedList(child) && !inOpenList(child)){
                this.addToOpen(child);
            }else{
                State checker = inList(child, open);
                /*
                 * If it is in the open list, check to see if it's cost is
                 * better now than it was in the previous open list.
                 */
                if (checker != null) {
                    if (child.getCostSum() < checker.getCostSum()) {
                        removeFromOpen(checker);
                        addToOpen(child);
                    } else {
                        /*
                         * Else check if it's in the closed list, and if it's
                         * state was better, then we 'update' the closed list
                         * with the new child.
                         */
                        checker = inList(child, closed);
                        if (checker != null) {
                            if (child.getCostSum() < checker.getCostSum()) {
                                removeFromClosed(checker);
                                addToClosed(child);
                            }
                        }
                    }
                }
            }
        }
    }

    public void addToOpen(State state) {open.add(state);}
    public boolean inOpenList(State state) {return open.contains(state);}
    public void removeFromOpen(State state) {open.remove(state);}
    public void addToClosed(State state) {closed.add(state);}
    public boolean inClosedList(State state) {return closed.contains(state);}
    public void removeFromClosed(State state) {closed.remove(state);}
    public int getNumberStates(){return this.numberStates=numberStates;}
}
