package com.puzzle.algorithm;

public class Node<N extends Comparable<N>> implements Comparable<Node<N>> {

    private final N node;
    private Node<N> predecessor;
    private double totalCostFromStart; //g(x)
    private final double minimumRemainingCostToTarget; //h(x)
    private double costSum; //f(x)
    private String direction;
    public Node(N node,
                Node<N> predecessor,
                double totalCostFromStart,
                double minimumRemainingCostToTarget,
                String direction) {
        this.node = node;
        this.predecessor = predecessor;
        this.totalCostFromStart = totalCostFromStart;
        this.minimumRemainingCostToTarget = minimumRemainingCostToTarget;
        this.direction=direction;
        calculateCostSum();
    }

    private void calculateCostSum() {
        this.costSum = this.totalCostFromStart + this.minimumRemainingCostToTarget;
    }
    public N getNode() {
        return node;
    }

    public Node<N> getPredecessor() {
        return predecessor;
    }

    public String getDirection() {
        return direction;
    }

    public double getTotalCostFromStart() {
        return totalCostFromStart;
    }

    public void setPredecessor(Node<N> predecessor) {
        this.predecessor = predecessor;
    }

    public void setTotalCostFromStart(double totalCostFromStart) {
        this.totalCostFromStart = totalCostFromStart;
    }

    public void setDirection(String direction) {
        this.direction=direction;
    }

    @Override
    public int compareTo(Node<N> nNode) {
        int compare = Double.compare(this.costSum, nNode.costSum);
        if (compare == 0) {
            compare = node.compareTo(nNode.node);
        }
        return compare;
    }

    @Override
    public String toString() {
        return "Node{" +
                "node=" + node +
                ", predecessor=" + predecessor +
                ", totalCostFromStart=" + totalCostFromStart +
                ", minimumRemainingCostToTarget=" + minimumRemainingCostToTarget +
                ", costSum=" + costSum +
                ", direction='" + direction + '\'' +
                '}';
    }
}
