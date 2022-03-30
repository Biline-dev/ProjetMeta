package work;


public class Node {
	private int state[][] = new int[3][3];
	private Node predecessor = null;
	private int depth = 0;

//constructeurs
//1
public Node() {
}

//2
public Node(int state[][], Node predecessor, int depth) {
   this.state = state;
   this.predecessor = predecessor;
   this.depth = depth;
}

//setters & getters
public int getNum(int posX, int posY) {
   return state[posX][posY];
}

public void setNum(int x, int y, int num) {
   state[x][y] = num;
}

public int getX(int num) {
int positionX = 0;
for (int x = 0; x < 3; x++) {
    for (int y = 0; y < 3; y++) {
        if (state[x][y] == num) {
            positionX = x;
            break;
        }
    }
}
    return positionX;
}

public int getY(int num) {
int positionY = 0;
for (int x = 0; x < 3; x++) {
    for (int y = 0; y < 3; y++) {
        if (state[x][y] == num) {
            positionY = y;
            break;
        }
    }
}

    return positionY;
}

//getX + getY
public int[] getCoordinate(int num) {
	int coordinate[] = new int[2];
    for (int x = 0; x < 3; x++) {
        for (int y = 0; y < 3; y++) {
            if (state[x][y] == num) {
                coordinate[0] = x;
                coordinate[1] = y;
                break;
            }
        }
    }
    return coordinate;
}

public int[][] getState() {
	return state;
}

public void setState(int[][] state) {
	this.state = state;
}

public Node getPredecessor() {
	return predecessor;
}

public void setPredecessor(Node predecessor) {
	this.predecessor = predecessor;
}

public int getDepth() {
	return depth;
}

public void setDepth(int depth) {
	this.depth = depth;
}

//méthode pour affecter le contenu du noeud à un ature
public int[][] affectState() {
	int newState[][] = new int[3][3];
	for(int i=0;i<3;i++) {
		for(int j=0;j<3;j++) {
			newState[i][j]=this.state[i][j];
		}
	}
	return newState;
}

//méthodes pour vérifier si deux noeud sont égaux en se basant sur leurs contenus
public boolean equal(Node n) {
	int i = 0;
	int j = 0;
	while (i<3 && j<3) {
		if(this.state[i][j] != n.state[i][j]) {
			return false;
		}
		i++;
		j++;
	}
	return true;
}

//méthode toString
public String toString() {
 String output = "";

 for (int x = 0; x < 3; x++) {
     for (int y = 0; y < 3; y++) {
         output += state[x][y];
     }
     output += "\n";
 }

 return output;
}

}
