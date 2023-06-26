package lab11;

import java.util.*;

public class RandomWeightedGraph extends WeightedGraph{

    private int vertices;
    private int edges;

    public RandomWeightedGraph (int vertices, int edges) {
        super();
        this.vertices = vertices;
        this.edges = edges;
        generateVertices(vertices);
        generateEdges(edges);
    }

    private void generateVertices(int num) {
        for(int i = 0; i <= num; i++) {
            addVertex(i);
        }
    }
    private void generateEdges(int num) {

        for(int i = 0; i <= num; i++) {
            Random random = new Random();
            int source = random.nextInt(vertices + 1);
            int destination = random.nextInt(vertices + 1);
            while(destination == source && !isConnected(source, destination)) {
                destination = random.nextInt(vertices + 1);
            }
            int weight = random.nextInt(51);

            addEdge(source, destination, weight);
        }
    }

    private boolean isConnected(int source, int destination) {

        List<Edge> neighbors = getNeighbors(source);

        if(neighbors.contains(destination)) return false;
        return true;
    }



}
