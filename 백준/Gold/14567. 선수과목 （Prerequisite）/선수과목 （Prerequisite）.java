import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M, indegree[], result[];
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	
	public static void topologySort() {
		StringBuilder sb = new StringBuilder();
		
		result = new int[N + 1];
		Queue<Integer> q = new LinkedList<Integer>();
		
		// 진입차수가 0인 거 넣기
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) {
				q.offer(i);
				result[i] = 1;
			}
		}
		
		while (!q.isEmpty()) {
			int now = q.poll();
			
			for (int i = 0; i < graph.get(now).size(); i++) {
				int temp = graph.get(now).get(i);
				indegree[temp] -= 1;
				
				if (indegree[temp] == 0) {
					q.offer(temp);
					result[temp] = result[now] + 1;
				}
			}
		}
		
		for (int i = 1; i <= N; i++) {
			sb.append(result[i] + " ");
		}
		
		System.out.println(sb);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		indegree = new int[N + 1];
		
		// 그래프 초기화
		for (int n = 0; n <= N; n++) {
			graph.add(new ArrayList<>());
		}
		
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			graph.get(A).add(B);
			indegree[B] += 1;
		}

		topologySort();
	}
}