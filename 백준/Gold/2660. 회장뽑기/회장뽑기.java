import java.util.*;
import java.lang.*;
import java.io.*;

class Main
{
    static int N;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean visited[];
    
    public static class Node implements Comparable<Node> {
        int idx, score;
        
        public Node(int idx, int score) {
            this.idx = idx;
            this.score = score;
        }
        
        @Override
        public int compareTo(Node n) {
            if (Integer.compare(this.score, n.score) == 0) {
                return Integer.compare(this.idx, n.idx);
            } else return Integer.compare(this.score, n.score);
        }
    }
    
	public static void main (String[] args) throws java.lang.Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i <= N; i++) {
		    graph.add(new ArrayList<Integer>());
		}
		
		while (true) {
		    st = new StringTokenizer(br.readLine());
		    int a = Integer.parseInt(st.nextToken());
		    int b = Integer.parseInt(st.nextToken());
		    
		    if (a == -1 && b == -1) break;
		    
		    graph.get(a).add(b);
		    graph.get(b).add(a);
		}
		
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		
		for (int i = 1; i <= N; i++) {
		    visited = new boolean[N + 1];
		    
		    ArrayDeque<Node> dq = new ArrayDeque<Node>();
		    
		    dq.offer(new Node(i, 0));
		    visited[i] = true;
		    int cnt = 1;
		    int depth = 0;
		    while (!dq.isEmpty()) {
		        Node node = dq.poll();
                depth = Math.max(depth, node.score);
		        for (int j = 0; j < graph.get(node.idx).size(); j++) {
		            int next = graph.get(node.idx).get(j);
		            if (visited[next]) continue;
		            cnt++;
		            visited[next] = true;
		            dq.offer(new Node(next, node.score + 1));
		        }
		        
		    }

		    if (cnt == N) {
		        pq.offer(new Node(i, depth));
		    }
		}
		
		Node node = pq.poll();
		int sc = node.score;
		int count = 1;
		StringBuilder sb = new StringBuilder();
		sb.append(node.idx + " ");
		
		while (!pq.isEmpty()) {
		    Node n = pq.poll();
		    if (n.score == sc) {
		        count++;
		        sb.append(n.idx + " ");
		    } else break;
		}
		
		System.out.println(sc + " " + count);
		System.out.print(sb.toString());
	}
}
