import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class ans2 {
    public static void main(String[] args) {
        String[] lines = null;
        try {
            lines = Files.readAllLines(Paths.get("input.txt")).toArray(new String[0]);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        Node[] nodes = new Node[lines.length];
        for(int i = 0; i < lines.length; i++) {
            String[] parts = lines[i].split(",");
            Node node = new Node();
            node.x = Integer.parseInt(parts[0]);
            node.y = Integer.parseInt(parts[1]);
            node.z = Integer.parseInt(parts[2]);
            nodes[i] = node;
        }

        PriorityQueue<Edge> edgeQueue = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        for(int i = 0; i < nodes.length; i++) {
            for(int j = i + 1; j < nodes.length; j++) {
                int weight = (int) Math.sqrt(Math.pow(nodes[i].x - nodes[j].x, 2) +
                                            Math.pow(nodes[i].y - nodes[j].y, 2) +
                                            Math.pow(nodes[i].z - nodes[j].z, 2));
                Edge edge = new Edge(nodes[i], nodes[j], weight);
                edgeQueue.add(edge);
            }
        }
        
        int[][] adjacencyMatrix = new int[nodes.length][nodes.length];
        while(!edgeQueue.isEmpty() ) {
            Edge edge = edgeQueue.poll();
            int index1 = -1, index2 = -1;
            for(int i = 0; i < nodes.length; i++) {
                if(nodes[i] == edge.node1) index1 = i;
                if(nodes[i] == edge.node2) index2 = i;
            }
            adjacencyMatrix[index1][index2] = 1;
            adjacencyMatrix[index2][index1] = 1;
            List<List<Integer>> clusters =findDistinctNodeClusters(adjacencyMatrix);
            if(clusters.size()==1 && clusters.get(0).size()==nodes.length){
                System.out.println((long) edge.node1.x * (long)edge.node2.x);
                return;
            }
        }

    }

    private static List<List<Integer>> findDistinctNodeClusters(int[][] adjacencyMatrix) {
        boolean[] visited = new boolean[adjacencyMatrix.length];
        List<List<Integer>> clusters = new ArrayList<>();

        for(int i = 0; i < adjacencyMatrix.length; i++) {
            if(!visited[i]) {
                List<Integer> cluster = new ArrayList<>();
                dfs(i, adjacencyMatrix, visited, cluster);
                clusters.add(cluster);
            }
        }
        return clusters;
    }

    private static void dfs(int node, int[][] adjacencyMatrix, boolean[] visited, List<Integer> cluster) {
        visited[node] = true;
        cluster.add(node);
        for(int j = 0; j < adjacencyMatrix.length; j++) {
            if(adjacencyMatrix[node][j] == 1 && !visited[j]) {
                dfs(j, adjacencyMatrix, visited, cluster);
            }
        }
    }
}

class Node {
    int x;
    int y;
    int z;
    Node nextNode;
    Node prevNode;
}
class Edge {
    Node node1;
    Node node2;
    int weight;

    Edge(Node node1, Node node2, int weight) {
        this.node1 = node1;
        this.node2 = node2;
        this.weight = weight;
    }
}