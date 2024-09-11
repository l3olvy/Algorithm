import java.util.*;
import java.io.*;

public class Main {
	
	static int N, M, F1, F2, maxLen, max;
	static ArrayList<ArrayList<Node>> graph;
	static boolean visited[];
	
	static class Node {
		int to, weight;
		
		public Node(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<ArrayList<Node>>();
		
		// 그래프 초기화
		for (int n = 0; n <= N; n++) {
			graph.add(new ArrayList<Node>());
		}
		
		maxLen = 0;
		
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			// 양방향 그래프 생성
			graph.get(A).add(new Node(B, C));
			graph.get(B).add(new Node(A, C));
			maxLen = Math.max(maxLen, C);
		}
		
		st = new StringTokenizer(br.readLine());
		F1 = Integer.parseInt(st.nextToken());
		F2 = Integer.parseInt(st.nextToken());
		max = 0;
        
		System.out.println(binarySearch(1, maxLen));
	}
	
	public static int binarySearch(int start, int end) {
		if (start > end) return max;
		
		int mid = (start + end) / 2;
		
		if (!bfs(F1, mid)) return binarySearch(start, mid - 1);
		else return binarySearch(mid + 1, end);
	}
	
	public static boolean bfs(int from, int target) {
		Queue<Node> pq = new ArrayDeque<Node>();
		
		pq.offer(new Node(from, 0));
		visited = new boolean[N + 1];
		
		visited[from] = true;
		
		while (!pq.isEmpty()) {
			Node node = pq.poll();
			int to = node.to;
			
			if (to == F2) {
				max = target;
				return true;
			}

			for (int i = 0; i < graph.get(to).size(); i++) {
				Node nextNode = graph.get(to).get(i);
				
				if (visited[nextNode.to])
					continue;
				
				if (nextNode.weight < target)
					continue;
				
				visited[nextNode.to] = true;
				pq.offer(nextNode);				
			}
		}
		return false;
	}
}