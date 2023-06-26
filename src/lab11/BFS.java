package lab11;

import java.util.*;

public class BFS {
    private WeightedGraph graph;

    public BFS(WeightedGraph graph) {
        this.graph = graph;
    }

    public List<Integer> shortestPath(int source, int destination) {
        Queue<Integer> queue = new LinkedList<>();
        Map<Integer, Integer> previous = new HashMap<>();
        Map<Integer, Integer> distances = new HashMap<>();

        for (int vertex : graph.getVertices()) {
            previous.put(vertex, null);
            distances.put(vertex, Integer.MAX_VALUE);
        }

        queue.offer(source);
        distances.put(source, 0);

        while (!queue.isEmpty()) {
            int current = queue.poll();

            if (current == destination) {
                break; // Reached the destination, exit the loop
            }

            List<WeightedGraph.Edge> neighbors = graph.getNeighbors(current);
            if (neighbors != null) {
                for (WeightedGraph.Edge edge : neighbors) {
                    int neighbor = edge.getDestination();
                    int weight = edge.getWeight();

                    int newDistance = distances.get(current) + weight;
                    if (newDistance < distances.get(neighbor)) {
                        distances.put(neighbor, newDistance);
                        previous.put(neighbor, current);
                        queue.offer(neighbor);
                    }
                }
            }
        }

        return buildPath(previous, source, destination);
    }

    private List<Integer> buildPath(Map<Integer, Integer> previous, int source, int destination) {
        List<Integer> path = new ArrayList<>();
        Integer current = destination;

        while (current != null) {
            path.add(0, current);
            current = previous.get(current);
        }

        if (path.get(0) == source) {
            return path;
        } else {
            return Collections.emptyList(); // No path exists
        }
    }
}

