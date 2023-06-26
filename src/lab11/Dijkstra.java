package lab11;

import java.util.*;

public class Dijkstra {
    private WeightedGraph graph;

    public Dijkstra(WeightedGraph graph) {
        this.graph = graph;
    }

    public List<Integer> cheapestPath(int source, int destination) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Map<Integer, Integer> previous = new HashMap<>();
        Map<Integer, Integer> distances = new HashMap<>();

        for (int vertex : graph.getVertices()) {
            previous.put(vertex, null);
            distances.put(vertex, Integer.MAX_VALUE);
        }

        distances.put(source, 0);
        pq.offer(new Node(source, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (current.vertex == destination) {
                break; // Reached the destination, exit the loop
            }

            List<WeightedGraph.Edge> neighbors = graph.getNeighbors(current.vertex);
            if (neighbors != null) {
                for (WeightedGraph.Edge edge : neighbors) {
                    int neighbor = edge.getDestination();
                    int weight = edge.getWeight();

                    int newDistance = distances.get(current.vertex) + weight;
                    if (newDistance < distances.get(neighbor)) {
                        distances.put(neighbor, newDistance);
                        previous.put(neighbor, current.vertex);
                        pq.offer(new Node(neighbor, newDistance));
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

    private class Node implements Comparable<Node> {
        private int vertex;
        private int distance;

        public Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(distance, other.distance);
        }
    }
}
