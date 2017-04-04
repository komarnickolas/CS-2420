package AStar;

import javafx.scene.control.*;

import java.util.LinkedList;

/**
 * Nickolas Komarnitsky
 * u0717854
 * 3/24/2017
 * 2420
 * Assignment 06
 * I pledge that the work done here was my own and that I have learned how to write this program, such that I could throw it out and restart and finish it in a timely manner. I am not turning in any work that I cannot understand, describe, or recreate. (Name)
 * Nickolas Komarnitsky
 */
public class Node extends Label{
    private boolean visited = false;
    private boolean isWall;
    private char value;
    private int x;
    private int y;
    private int cost, estimatedCost;
    private LinkedList<Node> edges;
    private Node parent;

    public Node(char value, int x, int y, boolean isWall) {
        this.setText(""+value);
        this.setValue(value);
        this.value = value;
        this.x = x;
        this.y = y;
        this.isWall = isWall;
        this.parent = null;
        this.cost = 0;
        this.estimatedCost = 0;
        edges = new LinkedList<>();
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public char getValue() {
        if (this == null) {
            return 'X';
        }
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public boolean isWall() {
        return isWall;
    }

    public void setAsWall(boolean isWall) {
        this.isWall = isWall;
    }

    public LinkedList<Node> getEdges() {
        return edges;
    }

    public void setEdges(LinkedList<Node> edges) {
        this.edges = edges;
    }

    public String toString() {
        String string = "" + getValue();
        return string;
    }

    public void print() {
        if (!isWall()) {
            System.out.print("(" + getX() + "," + getY() + ") [" + getValue() + "," + getCost() + "] ");
            if (get_parent() != null) {
                System.out.print("Parent: [" + get_parent().getValue() + "] (" + get_parent().getX() + ","
                        + get_parent().getY() + ") ");
            }
            System.out.print("Neighbors: {");
            for (Node n : getEdges()) {
                System.out.print("[" + n.getValue() + "] (" + n.getX() + "," + n.getY() + "),");
            }
            System.out.print("}\n");
        } else {
            System.out.println("(" + getX() + "," + getY() + ") [X]");
        }
    }

    public int calculateCost(Node node) {
        return Math.abs(getX() - node.getX()) + Math.abs(getY() - node.getY());
    }

    public Node get_parent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getEstimatedCost() {
        return estimatedCost;
    }

    public void setEstimatedCost(int hScore) {
        estimatedCost = hScore;
    }
}
