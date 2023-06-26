package lab11;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class Pathfinding {

    private Dijkstra dijkstra;
    private BFS bfs;
    private WeightedGraph graph;
    private Random random;
    private int vertexA;
    private int vertexB;

    public Pathfinding (WeightedGraph graph) {
        dijkstra = new Dijkstra(graph);
        bfs = new BFS(graph);
        random = new Random();
        this.graph = graph;
        getRandomVertices();
    }

    public void getRandomVertices() {
        Set vertices = graph.getVertices();
        vertexA = random.nextInt(vertices.size() + 1);
        vertexB = random.nextInt(vertices.size() + 1);
        System.out.println("random point A: " + vertexA + "\nrandom Point B: " + vertexB);
        System.out.println();
    }
    public void cheapestPath() {
        List<Integer> shortestPath = dijkstra.cheapestPath(vertexA, vertexB);

        if (!shortestPath.isEmpty()) {
            System.out.println("Cheapest path from " +  vertexA +  " to " + vertexB + " :");
            for (int vertex : shortestPath) {
                System.out.print(vertex + " ");
            }
        } else {
            System.out.println("No path exists from " +  vertexA +  " to " + vertexB + " :");
        }
    }

    public void shortestPath() {
        List<Integer> shortestPath = bfs.shortestPath(vertexA, vertexB);

        if (!shortestPath.isEmpty()) {
            System.out.println("Shortest path from " +  vertexA +  " to " + vertexB + " :");
            for (int vertex : shortestPath) {
                System.out.print(vertex + " ");
            }
        } else {
            System.out.println("No path exists from " +  vertexA +  " to " + vertexB + " :");
        }
        System.out.println();
    }


}
