package lab11;

import java.util.List;

public class Main {
    public static void main (String[] args) {
        WeightedGraph graph = new WeightedGraph();
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 2);
        graph.addEdge(1, 2, 1);
        graph.addEdge(2, 3, 5);

        List<WeightedGraph.Edge> neighbors = graph.getNeighbors(0);
        for (WeightedGraph.Edge edge : neighbors) {
            int destination = edge.getDestination();
            int weight = edge.getWeight();
            System.out.println("Edge from 0 to " + destination + " with weight " + weight);
        }

    }
}
