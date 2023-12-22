import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Queue;

/**
 * Represents an airport system with cities, flights, and various algorithms.
 * @quthor Ryan Cesar Irizarry
 */
public class AirportSystem {

    /**
     * Represents an edge connecting two cities in the airport system.
     */
    class Edge {

        //The starting location of this edge as a string.
        String source;

        //The end destination of this edge as a string.
        String destination;

        //The distance between start and destination.
        int distance;

        /**
         * Constructs an edge between two cities with a given distance.
         *
         * @param source The starting location of the edge.
         * @param destination The end destination of the edge.
         * @param distance The distance between the two cities.
         */
        public Edge(String source, String destination, int distance) {
            this.source = source;
            this.destination = destination;
            this.distance = distance;
        }


        /**
         * Returns a string representation of the edge.
         *
         * @return A string representing the edge.
         */
        @Override
        public String toString() {
            return "[" + this.source + ", " + this.destination + "]";
        }
    }

    /**
     * Represents a city in the airport system with connections to other cities.
     */
    static class Vertex {

        //The city name.
        String id;

        //The cities that are connected to this city by the airport system.
        List<Edge> edges;

        /**
         * Constructs a city with a given ID.
         *
         * @param id The ID of the city.
         */
        public Vertex(String id) {
            this.id = id;
            this.edges = new LinkedList<>();
        }

        /**
         * Returns the string representation of the vertex.
         *
         * @return The string representation of the vertex.
         */
        @Override
        public String toString() {
            return this.id;
        }

    }

    //The adjacency list of the cities.
    // Each node is a city, and each connecting line indicates a flight between two cities.
    // The List<Edge> represents a vertex.
    List<Vertex> connections;

    /**
     * Constructs an AirportSystem with an empty list of connections.
     */
    public AirportSystem() {
        this.connections = new ArrayList<>();
    }

    /**
     * Finds a vertex with a given ID in the connections list.
     *
     * @param id The ID of the vertex to find.
     * @return The vertex with the specified ID, or null if not found.
     */
    private Vertex findVertex(String id) {
        for (int i = 0; i < connections.size(); i++) {
            Vertex v = connections.get(i);
            if (v.id.equals(id)) {
                return v;
            }
        }
        return null;
    }

    /**
     * Adds a new edge to the connections list. Return false if the edge already exists or the weight is negative.
     *
     * @param source The source city.
     * @param destination The destination city.
     * @param weight The weight (distance) of the edge.
     * @return True if the edge is added successfully, false otherwise.
     */
    public boolean addEdge(String source, String destination, int weight) {
        if (weight < 0) {
            return false;
        }
        Edge inputEdge = new Edge(source, destination, weight);
        Vertex pinpointVertex = findVertex(source);

        if (pinpointVertex != null) {

            Iterator<Edge> vIterator = pinpointVertex.edges.iterator();

            while (vIterator.hasNext()) {
                Edge curEdge = vIterator.next();
                if (curEdge.destination.equals(inputEdge.destination)) {
                    return false;
                }
            }
            pinpointVertex.edges.add(inputEdge);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the shortest distance between cityA and cityB using Dijkstra's algorithm.
     *
     * @param cityA The starting city.
     * @param cityB The destination city.
     * @return The shortest distance between city A and city B.
     */
    public int shortestDistance(String cityA, String cityB) {
        //create an empty chart that will be used to list the vertices and their respective distances
        Map<String, Integer> distanceTable = new HashMap<>();
        for (int i = 0; i < connections.size(); i++) {
            Vertex currVertex = connections.get(i);
            String cID = currVertex.id;
            distanceTable.put(cID, Integer.MAX_VALUE);
        }
        distanceTable.put(cityA, 0);

        PriorityQueue<Vertex> pq = new PriorityQueue<>((v1, v2) -> Integer.compare(distanceTable.get(v1.id), distanceTable.get(v2.id)));
        Vertex sourceTex = findVertex(cityA);
        pq.add(sourceTex);

        while (pq.size() > 0) {
            Vertex curVertex = pq.poll();

            for (int j = 0; j < curVertex.edges.size(); j++) {
                Edge edge = curVertex.edges.get(j);
                int curDistance = distanceTable.get(curVertex.id);
                int newDistance = curDistance + edge.distance;
                String destinationId = edge.destination;

                if (newDistance < distanceTable.get(destinationId)) {
                    distanceTable.put(destinationId, newDistance);
                    Vertex destinationVertex = findVertex(destinationId);
                    pq.add(destinationVertex);
                }
            }
        }
        return distanceTable.get(cityB);
    }

    /**
     * Uses Prim's algorithm to create a minimum spanning tree.
     *
     * @return A list of edges representing the minimum spanning tree.
     */
    public List<Edge> minimumSpanningTree() {
        List<Vertex> visited = new ArrayList<>();
        List<Edge> treeEdges = new ArrayList<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.distance));

        Vertex startTex = connections.get(0);
        visited.add(startTex);
        pq.addAll(startTex.edges);

        while (pq.size() > 0) {
            Edge minEdge = pq.poll();
            Vertex destinationTex = findVertex(minEdge.destination);

            if (!visited.contains(destinationTex)) {
                visited.add(destinationTex);
                treeEdges.add(minEdge);
                pq.addAll(destinationTex.edges);
            }
        }
        return treeEdges;
    }

    /**
     * Performs a breadth-first search starting from the specified city.
     *
     * @param start The ID of the starting city.
     * @return A list of visited cities in the order they were visited.
     */
    public List<String> breadthFirstSearch(String start) {
        Vertex startVertex = findVertex(start);

        if (startVertex == null) {
            return null;
        }

        ArrayList<String> visited = new ArrayList<>();
        Queue<Vertex> queue = new LinkedList<>();

        queue.add(startVertex);
        visited.add(start);

        while (!queue.isEmpty()) {
            Vertex currVertex = queue.poll();

            for (int i = 0; i < currVertex.edges.size(); i++) {
                Edge child = currVertex.edges.get(i);
                String directlyConnectedID = child.destination;
                Vertex directlyConnected = findVertex(directlyConnectedID);

                if (directlyConnected != null && !visited.contains(directlyConnectedID)) {
                    queue.add(directlyConnected);
                    visited.add(directlyConnectedID);
                }
            }
        }
        return visited;
    }

    /**
     * Helper method that prints the graph in a readable format, indicating which edge belongs to each vertex.
     *
     * @param cityID The ID of the city.
     * @param edges The list of edges associated with the city.
     */
    private void printGraph(String cityID, List<Edge> edges) {
        StringBuilder builder = new StringBuilder();
        builder.append("V: ").append(cityID).append(" | E: [");

        for (int j = 0; j < edges.size(); j++) {
            Edge edge = edges.get(j);
            builder.append("[").append(edge.source).append(", ").append(edge.destination).append("]");

            if (j < edges.size() - 1) {
                builder.append(", ");
            }
        }
        builder.append("]\n");
        System.out.println(builder.toString());
    }

    /**
     * Prints the entire airport system graph, displaying each vertex and its associated edges.
     */
    public void print() {
        for (int i = 0; i < connections.size(); i++) {
            Vertex currVertex = connections.get(i);
            printGraph(currVertex.id, currVertex.edges);
        }
    }

    /**
     * The main method to execute when running the AirportSystem class.
     *
     * @param args Command-line arguments
     */
    public static void main (String args[]) {
        AirportSystem airportSystem = new AirportSystem();

        Vertex v5 = new Vertex("5");
        Vertex v4 = new Vertex("4");
        Vertex v1 = new Vertex("1");
        Vertex v2 = new Vertex("2");
        Vertex v3 = new Vertex("3");
        Vertex v6 = new Vertex("6");

        airportSystem.connections.add(v5);
        airportSystem.connections.add(v4);
        airportSystem.connections.add(v1);
        airportSystem.connections.add(v2);
        airportSystem.connections.add(v3);
        airportSystem.connections.add(v6);

        airportSystem.addEdge("5", "4", 9);
        airportSystem.addEdge("5", "1", 4);
        airportSystem.addEdge("4", "5", 9);
        airportSystem.addEdge("4", "1", 1);
        airportSystem.addEdge("4", "2", 3);
        airportSystem.addEdge("4", "3", 5);
        airportSystem.addEdge("1", "5", 4);
        airportSystem.addEdge("1", "4", 1);
        airportSystem.addEdge("1", "2", 2);
        airportSystem.addEdge("2", "4", 3);
        airportSystem.addEdge("2", "1", 2);
        airportSystem.addEdge("2", "3", 3);
        airportSystem.addEdge("2", "6", 7);
        airportSystem.addEdge("3", "4", 5);
        airportSystem.addEdge("3", "2", 3);
        airportSystem.addEdge("3", "6", 8);
        airportSystem.addEdge("6", "3", 8);
        airportSystem.addEdge("6", "2", 7);

        System.out.println("Output for print graph: ");
        airportSystem.print();

        System.out.println("Output for minimum spanning tree: ");
        System.out.println(airportSystem.minimumSpanningTree());
        System.out.println();
        System.out.println("Output for shortest distance from 6 to 5: ");
        System.out.println(airportSystem.shortestDistance(v6.id, v5.id));
        System.out.println();
        System.out.println("Output breadthFirstSearch: ");
        System.out.println(airportSystem.breadthFirstSearch(v1.id));
    }
}


