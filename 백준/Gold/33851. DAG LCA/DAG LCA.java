import java.util.*;
import java.lang.*;
import java.io.*;



class Main
{
    static int n, m, q, result;
    static final int INF = (int)1e9;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
    
	public static void main (String[] args) throws java.lang.Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuffer sb = new StringBuffer();
		
		n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        
        // ArrayList 초기화
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<Integer>());
        }
        
        // 단방향 그래프 생성
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            
            graph.get(v).add(u);
        }
        
        // 쿼리 실행
        for (int i = 0; i < q; i++) {
            result = INF;
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            
            int list1[] = dijkstra(u);
            int list2[] = dijkstra(v);
            
            for (int j = 1; j <= n; j++) {
                int minU = list1[j];
                int minV = list2[j];
                
                if (minU == INF || minV == INF) continue;
                
                result = Integer.min(result, Integer.max(minU, minV));
            }
            
            if (result == INF) result = -1;
            
            sb.append(result + "\n");
        }
        
        System.out.print(sb.toString());
	}
	
	public static class Node implements Comparable<Node> {
	    int idx, dist;
	    
	    public Node(int idx, int dist) {
	        this.idx = idx;
	        this.dist = dist;
	    }
	    
	    @Override
	    public int compareTo(Node node) {
	        return Integer.compare(this.dist, node.dist);
	    }
	}
	
	public static int[] dijkstra(int start) {
	    int list[] = new int[n + 1];
	    Arrays.fill(list, INF);
	    Queue<Node> pq = new PriorityQueue<Node>();
	    pq.offer(new Node(start, 0));
	    list[start] = 0;
	    
	    while (!pq.isEmpty()) {
	       Node node = pq.poll();
	       
	       int now = node.idx;
	       int dist = node.dist;
	       
	       if (list[now] < dist) continue;
	       
	       for (int i = 0; i < graph.get(now).size(); i++) {
	           int cost = list[now] + 1;
	           if (cost < list[graph.get(now).get(i)]) {
	               list[graph.get(now).get(i)] = cost;
	               pq.offer(new Node(graph.get(now).get(i), cost));
	           }
	       }
	    }
	    
	    return list;
	}
}


// 일단 최단 경로 알고리즘..... => 다익스트라.