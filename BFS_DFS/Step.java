package work;

public class Step {
//constructeur
public Step() {
	
}

//les méthodes
public Node moveUp(Node currentState) {
	Node newState = null;
    int emptyPosition [] = currentState.getCoordinate(0);
    if (emptyPosition[0] - 1 >= 0) {
    	newState = new Node();
    	newState.setState(currentState.affectState());  
    	newState.setDepth(currentState.getDepth()+1);
    	newState.setPredecessor(currentState);
    	//inverser les deux cases
        int temp = newState.getNum(emptyPosition[0] - 1 , emptyPosition[1]);
        newState.setNum(emptyPosition[0], emptyPosition[1], temp);
        newState.setNum(emptyPosition[0]-1, emptyPosition[1], 0);
    }
    return newState;
}

public Node moveRight(Node currentState) {
	Node newState = null;
    int emptyPosition [] = currentState.getCoordinate(0);
    if (emptyPosition[1] + 1 <= 2) {
    	newState = new Node();
    	newState.setState(currentState.affectState()); 
    	newState.setDepth(currentState.getDepth()+1);
    	newState.setPredecessor(currentState);
    	//inverser les deux cases
        int temp = newState.getNum(emptyPosition[0], emptyPosition[1]+1);
        newState.setNum(emptyPosition[0], emptyPosition[1], temp);
        newState.setNum(emptyPosition[0], emptyPosition[1]+1, 0);
    }
    return newState;
}

public Node moveDown(Node currentState) {
	Node newState = null;
    int emptyPosition [] = currentState.getCoordinate(0);
    if (emptyPosition[0] +1 <= 2) {
    	newState = new Node();
    	newState.setState(currentState.affectState()); 
    	newState.setDepth(currentState.getDepth()+1);
    	newState.setPredecessor(currentState);
    	//inverser les deux cases
        int temp = newState.getNum(emptyPosition[0] + 1 , emptyPosition[1]);
        newState.setNum(emptyPosition[0], emptyPosition[1], temp);
        newState.setNum(emptyPosition[0]+1, emptyPosition[1], 0);
    }
    return newState;
}

public Node moveLeft(Node currentState) {
	Node newState = null;
    int emptyPosition [] = currentState.getCoordinate(0);
    if (emptyPosition[1] - 1 >= 0) {
    	newState = new Node();
    	newState.setState(currentState.affectState()); 
    	newState.setDepth(currentState.getDepth()+1);
    	newState.setPredecessor(currentState);
    	//inverser les deux cases
        int temp = newState.getNum(emptyPosition[0], emptyPosition[1]-1);
        newState.setNum(emptyPosition[0], emptyPosition[1], temp);
        newState.setNum(emptyPosition[0], emptyPosition[1]-1, 0);
    }
    return newState;
}

}
