package AStar;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

/**
 * Nickolas Komarnitsky
 * u0717854
 * 3/24/2017
 * 2420
 * Assignment 06
 * I pledge that the work done here was my own and that I have learned how to write this program, such that I could throw it out and restart and finish it in a timely manner. I am not turning in any work that I cannot understand, describe, or recreate. (Name)
 * Nickolas Komarnitsky
 */
public class PathFinder extends TabPane{
    private Graph graphEmpty, graphSolved;

    public void solveMaze(String inputFileName, String outputFileName) {
        try {
            graphEmpty = new Graph(readFile(inputFileName));
            graphSolved = new Graph(readFile(inputFileName));
            HBox content = new HBox();
            content.setPadding(new Insets(15, 12, 15, 12));
            content.setSpacing(10);
            VBox maze = new VBox();
            VBox solution = new VBox();
            Label mazeLabel = new Label("Maze");
            Label solutionLabel = new Label("Solution");
            maze.getChildren().add(mazeLabel);
            solution.getChildren().add(solutionLabel);
            content.getChildren().addAll(maze,solution);
            maze.getChildren().add(graphEmpty);
            LinkedList<Node> path = findPath(graphSolved);
            if (path != null) {
                for (Node n : path) {
                    if (n != graphSolved.getGoal()) {
                        graphSolved.getNodes()[n.getY()][n.getX()].setText(".");
                        graphSolved.getNodes()[n.getY()][n.getX()].setValue('.');
                    }
                }
            }
            writeFile(outputFileName, graphSolved);
            solution.getChildren().add(graphSolved);
            Tab tab = new Tab();
            tab.setContent(new ScrollPane(content));
            tab.setText(inputFileName.substring(inputFileName.lastIndexOf("mazes") + 6, inputFileName.length()));
            getTabs().add(tab);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private LinkedList<Node> findPath(Graph graph) {
        Queue<Node> queue = new ArrayDeque<>();
        graph.getStart().setCost(0);
        graph.getStart().setEstimatedCost(graph.getStart().calculateCost(graph.getGoal()));
        queue.add(graph.getStart());
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node == graph.getGoal()) {
                return graph.buildPath();
            }
            LinkedList<Node> edges = node.getEdges();
            for (int i = 0; i < edges.size(); i++) {
                Node edgeNode = edges.get(i);
                int cost = node.getCost() + node.calculateCost(edgeNode);
                if ((!queue.contains(edgeNode) && !edgeNode.isVisited()) || cost < edgeNode.getCost()) {
                    edgeNode.setParent(node);
                    edgeNode.setCost(cost);
                    edgeNode.setEstimatedCost(edgeNode.calculateCost(graph.getGoal()));
                    if (edgeNode.isVisited()) {
                        edgeNode.setVisited(false);
                    }
                    if (!queue.contains(edgeNode)) {
                        queue.add(edgeNode);
                    }
                }
            }
            node.setVisited(true);
        }
        return null;
    }

    private Node[][] readFile(String inputFile) throws IOException {
        Node[][] fileArr = null;
        try (Stream<String> stream = Files.lines(Paths.get(inputFile))) {
            Object[] lines = stream.toArray();
            String[] sizeOfArray = ((String) lines[0]).split(" ");
            fileArr = new Node[Integer.parseInt(sizeOfArray[0])][Integer.parseInt(sizeOfArray[1])];
            for (int row = 1; row < lines.length; row++) {
                String line = (String) lines[row];
                char[] characters = line.toCharArray();
                for (int col = 0; col < characters.length; col++) {
                    if (characters[col] == 'X') {
                        Node node = new Node(characters[col], col, row - 1, true);
                        node.setCost(-1);
                        fileArr[row - 1][col] = node;
                    } else {
                        Node node = new Node(characters[col], col, row - 1, false);
                        fileArr[row - 1][col] = node;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileArr;
    }

    private static boolean writeFile(String outputFile, Graph graph) throws IOException {
        try {
            FileWriter fw = new FileWriter(outputFile);
            PrintWriter pr = new PrintWriter(fw);
            pr.print(graph.toString());
            pr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
