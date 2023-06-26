package lab11;

import java.util.List;

public class Main {
    public static void main (String[] args) {
        RandomWeightedGraph rGraph = new RandomWeightedGraph(10, 25);

        rGraph.printGraph();
        Pathfinding pathfinding = new Pathfinding(rGraph);
        pathfinding.shortestPath();
        pathfinding.cheapestPath();
        }
    }

