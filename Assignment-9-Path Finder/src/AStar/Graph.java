package AStar;

import javafx.scene.layout.GridPane;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Nickolas Komarnitsky
 * u0717854
 * 3/24/2017
 * 2420
 * Assignment 06
 * I pledge that the work done here was my own and that I have learned how to write this program, such that I could throw it out and restart and finish it in a timely manner. I am not turning in any work that I cannot understand, describe, or recreate. (Name)
 * Nickolas Komarnitsky
 */
public class Graph extends GridPane{
    private Node[][] nodes;
    private Node current, start, goal;
    private Queue<Node> path;

    public Graph(Node[][] inputNodes) {
        this.nodes = inputNodes;
        for (int row = 0; row < nodes.length; row++) {
            for (int col = 0; col < nodes[0].length; col++) {
                add(nodes[row][col],row,col);
                if (!nodes[row][col].isWall()) {
                    setCurrent(nodes[row][col]);
                    buildEdges(nodes[row][col]);
                    if (nodes[row][col].getValue() == 'S') {
                        this.setStart(nodes[row][col]);
                    } else if (nodes[row][col].getValue() == 'G') {
                        this.setGoal(nodes[row][col]);
                    }
                }
            }
        }
    }

    private void buildEdges(Node n) {
        if (!getDown().isWall()) {
            Node temp = getDown();
            n.getEdges().add(temp);
        }
        if (!getUp().isWall()) {
            Node temp = getUp();
            n.getEdges().add(temp);
        }
        if (!getLeft().isWall()) {
            Node temp = getLeft();
            n.getEdges().add(temp);
        }
        if (!getRight().isWall()) {
            Node temp = getRight();
            n.getEdges().add(temp);
        }
    }

    public void print(boolean printWalls) {
        for (int row = 0; row < nodes.length; row++) {
            for (int col = 0; col < nodes[0].length; col++) {
                if (printWalls) {
                    nodes[row][col].print();
                } else {
                    if (!nodes[row][col].isWall()) {
                        nodes[row][col].print();
                    }
                }
            }
        }
    }

    public String toString() {
        String string = "" + nodes.length + " " + nodes[0].length + "\n";
        for (int row = 0; row < nodes.length; row++) {
            for (int col = 0; col < nodes[0].length; col++) {
                string += nodes[row][col].toString();
            }
            string += "\n";
        }
        return string;
    }

    public LinkedList<Node> buildPath() {
        LinkedList<Node> path = new LinkedList<>();
        Node node = getGoal();
        while (node.get_parent() != null) {
            path.addFirst(node);
            node = node.get_parent();
        }
        return path;
    }

    public Node getRight() {
        return nodes[current.getY()][current.getX() + 1];
    }

    public Node getLeft() {
        return nodes[current.getY()][current.getX() - 1];
    }

    public Node getUp() {
        return nodes[current.getY() - 1][current.getX()];
    }

    public Node getDown() {
        return nodes[current.getY() + 1][current.getX()];
    }

    public Node[][] getNodes() {
        return nodes;
    }

    public void setNodes(Node[][] nodes) {
        this.nodes = nodes;
    }

    public Node getCurrent() {
        return current;
    }

    public void setCurrent(Node current) {
        this.current = current;
    }

    public Node getStart() {
        return start;
    }

    public void setStart(Node start) {
        this.start = start;
    }

    public Node getGoal() {
        return goal;
    }

    public void setGoal(Node goal) {
        this.goal = goal;
    }

    public Queue<Node> getPath() {
        return path;
    }

    public void setPath(Queue<Node> path) {
        this.path = path;
    }
}
