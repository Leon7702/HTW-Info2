package lab11;

import java.util.*;

class WeightedGraph {
    protected Map<Integer, List<Edge>> adjacencyList;

    public WeightedGraph() {
        adjacencyList = new HashMap<>();
    }

    public void addVertex(int vertex) {
        adjacencyList.put(vertex, new ArrayList<>());
    }

    public void addEdge(int source, int destination, int weight) {
        if (!adjacencyList.containsKey(source))
            addVertex(source);
        if (!adjacencyList.containsKey(destination))
            addVertex(destination);

        List<Edge> edges = adjacencyList.get(source);
        edges.add(new Edge(destination, weight));
    }

    public List<Edge> getNeighbors(int vertex) {
        return adjacencyList.get(vertex);
    }

    public Set<Integer> getVertices() {
        return adjacencyList.keySet();
    }

    public void removeVertex(int vertex) {
        adjacencyList.remove(vertex);
        // Remove edges containing the vertex
        for (List<Edge> edges : adjacencyList.values()) {
            edges.removeIf(edge -> edge.getDestination() == vertex);
        }
    }

    public void removeEdge(int source, int destination) {
        List<Edge> edges = adjacencyList.get(source);
        if (edges != null) {
            edges.removeIf(edge -> edge.getDestination() == destination);
        }
    }

    public int size() {
        return adjacencyList.size();
    }

    public boolean isEmpty() {
        return adjacencyList.isEmpty();
    }

    public void printGraph() {
        for (Map.Entry<Integer, List<Edge>> entry : adjacencyList.entrySet()) {
            int vertex = entry.getKey();
            List<Edge> edges = entry.getValue();

            System.out.print("Vertex " + vertex + ": ");

            for (Edge edge : edges) {
                int destination = edge.getDestination();
                int weight = edge.getWeight();

                System.out.print("(" + destination + ", " + weight + ") ");
            }

            System.out.println();
        }
        System.out.println();
    }

    public class Edge {
        protected int destination;
        protected int weight;

        public Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }

        public int getDestination() {
            return destination;
        }

        public int getWeight() {
            return weight;
        }
    }
}
