Simulating a Link State Routing Protocol (LSRP) like OSPF in Java involves a few key components, primarily the understanding and implementation of Dijkstra's algorithm for the shortest path finding. The LSRP operates by maintaining a complete map of the network topology at each node, which is updated via periodic exchanges of link state advertisements (LSAs) between nodes.

In this simplified simulation, we'll consider a static network topology represented by a graph, where each node will calculate the shortest path to every other node using Dijkstra's algorithm. This approach focuses more on the algorithmic part rather than the full dynamic behavior of real link state routing protocols.

### Java Program to Simulate Link State Routing Protocol

Here’s how you might code it:

/*
import java.util.*;

public class LinkStateRouting {

    static class Graph {
        private int vertices; // Number of vertices
        private List<List<Node>> adjList; // Adjacency list

        Graph(int vertices) {
            this.vertices = vertices;
            adjList = new ArrayList<>(vertices);
            for (int i = 0; i < vertices; i++) {
                adjList.add(new ArrayList<>());
            }
        }

        public void addEdge(int u, int v, int weight) {
            adjList.get(u).add(new Node(v, weight));
            adjList.get(v).add(new Node(u, weight)); // For undirected graph
        }

        public void dijkstra(int src) {
            PriorityQueue<Node> pq = new PriorityQueue<>(vertices, new Node());
            int[] dist = new int[vertices];
            Arrays.fill(dist, Integer.MAX_VALUE);
            pq.add(new Node(src, 0));
            dist[src] = 0;

            while (!pq.isEmpty()) {
                Node node = pq.poll();
                int u = node.node;

                for (Node edge : adjList.get(u)) {
                    int v = edge.node;
                    int weight = edge.cost;
                    if (dist[u] + weight < dist[v]) {
                        dist[v] = dist[u] + weight;
                        pq.add(new Node(v, dist[v]));
                    }
                }
            }

            printSolution(src, dist);
        }

        private void printSolution(int src, int[] dist) {
            System.out.println("Routing Table for Router " + src);
            System.out.println("Destination\tCost");
            for (int i = 0; i < vertices; i++) {
                System.out.println(i + "\t\t" + dist[i]);
            }
        }
    }

    static class Node implements Comparator<Node> {
        public int node;
        public int cost;

        public Node() {}

        public Node(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compare(Node node1, Node node2) {
            if (node1.cost < node2.cost)
                return -1;
            if (node1.cost > node2.cost)
                return 1;
            return 0;
        }
    }

    public static void main(String[] args) {
        int vertices = 5;
        Graph graph = new Graph(vertices);

        // Add edges
        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 3, 6);
        graph.addEdge(1, 3, 8);
        graph.addEdge(1, 4, 5);
        graph.addEdge(1, 2, 3);
        graph.addEdge(2, 4, 7);

        // Calculate shortest path from all nodes
        for (int i = 0; i < vertices; i++) {
            graph.dijkstra(i);
        }
    }
}
*/

### How This Works:
1. **Graph Representation**: The network is represented using an adjacency list where each node has a list of all connected nodes along with the cost to reach them.
2. **Dijkstra's Algorithm**: This algorithm is used by each node to calculate the shortest path to every other node in the network.
3. **Priority Queue**: It's used to always expand the least costly node not yet processed, ensuring that the shortest path is found in an efficient manner.

### Running the Program:
1. **Save the code** in a file named `LinkStateRouting.java`.
2. **Compile the code** using:

   javac LinkStateRouting.java

3. **Run the compiled program** with:

   java LinkStateRouting

Each node (router) computes the shortest path to every other node, simulating the process of building a routing table based on link-state information. This program is a static simulation and doesn't include the dynamic exchange of LSAs or handling changes in the network topology dynamically.