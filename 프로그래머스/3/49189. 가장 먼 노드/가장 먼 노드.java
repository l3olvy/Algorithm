import java.util.*;

class Solution {

    public static ArrayList<ArrayList<Node>> graph;
    public static int[] d;
    public static final int INF = (int) 1e9;
    
    public static class Node implements Comparable<Node> {
        int idx, dist;
        
        public Node(int idx, int dist) {
            this.idx = idx;
            this.dist = dist;
        }
        
        @Override
        public int compareTo(Node n) {
            return Integer.compare(this.dist, n.dist);
        }
    }
    
    public int solution(int n, int[][] edge) {
        
        graph = new ArrayList<ArrayList<Node>>();
        d = new int[n + 1];
        Arrays.fill(d, INF);
        
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<Node>());
        }
        
        for (int[] e : edge) {
            int a = e[0];
            int b = e[1];
            
            graph.get(a).add(new Node(b, 1));
            graph.get(b).add(new Node(a, 1));
        }
        
        dijkstra(1);
        
        int max = 0;
        int answer = 0;
    
        for (int i = 1; i <= n; i++) {
            max = Math.max(max, d[i]);
        }
        
        for (int i = 1; i <= n; i++) {
            if (max == d[i]) answer++;
        }
        
        
        return answer;
    }
    
    public static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        
        pq.offer(new Node(start, 0));
        d[start] = 0;
        
        while (!pq.isEmpty()) {
            Node n = pq.poll();
            int now = n.idx;
            int dist = n.dist;
            
            if (d[now] < dist) continue;
            
            for (int i = 0; i < graph.get(now).size(); i++) {
                int cost = dist + graph.get(now).get(i).dist;
                
                if (cost < d[graph.get(now).get(i).idx]) {
                    d[graph.get(now).get(i).idx] = cost;
                    pq.offer(new Node(graph.get(now).get(i).idx, cost));
                }
            }
        }
    }
}