package lab11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class WeightedGraph {
    private Map<Integer, List<Edge>> adjacencyList;

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

    public class Edge {
        private int destination;
        private int weight;

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
