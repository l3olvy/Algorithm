import java.util.*;
import java.lang.*;
import java.io.*;

class Main
{
    static int N, M, d[];
    static final int INF = Integer.MAX_VALUE;
    static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
    
    public static class Node implements Comparable<Node> {
        int idx, cost;
        
        public Node (int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Node n) {
            return Integer.compare(this.cost, n.cost);
        }
    }
    
    
	public static void main (String[] args) throws java.lang.Exception
	{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    
	    N = Integer.parseInt(st.nextToken());
	    M = Integer.parseInt(st.nextToken());
	    
	    d = new int[N + 1];
	    Arrays.fill(d, INF);
	    
	    // 그래프 초기화
	    for (int n = 0; n <= N; n++) {
	        graph.add(new ArrayList<Node>());
	    }
	    
	    for (int m = 0; m < M; m++) {
	        st = new StringTokenizer(br.readLine());
	        int a = Integer.parseInt(st.nextToken());
	        int b = Integer.parseInt(st.nextToken());
	        int c = Integer.parseInt(st.nextToken());
	        
	        graph.get(a).add(new Node(b, c));
	        graph.get(b).add(new Node(a, c));
	    }
	    
	    dijkstra(1);
	    
	    System.out.print(d[N]);
	}
	
	public static void dijkstra(int start) {
	    PriorityQueue<Node> pq = new PriorityQueue<Node>();
	    
	    pq.offer(new Node(start, 0));
	    d[start] = 0;
	    
	    while (!pq.isEmpty()) {
	        Node node = pq.poll();
	        
	        if (d[node.idx] < node.cost) continue;
	        
	        for (int i = 0; i < graph.get(node.idx).size(); i++) {
	            int nowIdx = graph.get(node.idx).get(i).idx;
	            int nowCost = graph.get(node.idx).get(i).cost;
	            if (d[nowIdx] > nowCost + d[node.idx]) {
	                d[nowIdx] = nowCost + d[node.idx];
	                pq.offer(new Node(nowIdx, nowCost));
	            }
	        }
	    }
	}
}