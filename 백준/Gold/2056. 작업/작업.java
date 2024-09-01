import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static int N, time[], indegree[], result[];
	public static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();

	public static void topologySort() {
		Queue<Integer> q = new LinkedList<>();
		result = new int[N + 1];
		int answer = 0;
		
		Arrays.fill(result, 0);
		
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) {
				q.offer(i);
				result[i] = Math.max(result[i], time[i]);
			}
		}
		
		while (!q.isEmpty()) {
			int now = q.poll();
			
			for (int i = 0; i < graph.get(now).size(); i++) {
				int temp = graph.get(now).get(i);
				indegree[temp] -= 1;
				result[temp] = Math.max(result[temp], result[now] + time[temp]);
				
				if (indegree[temp] == 0) {
					q.offer(temp);
				}
			}
		}
		
		for (int i = 1; i <= N; i++) {
			answer = Math.max(answer, result[i]);
		}
		
		System.out.println(answer);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		time = new int[N + 1];
		indegree = new int[N + 1];
		
		// 그래프 초기화
		for (int n = 0; n <= N; n++) {
			graph.add(new ArrayList<>());
		}
		
		for (int a = 1; a <= N; a++) {
			st = new StringTokenizer(br.readLine());
			time[a] = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			
			for (int i = 0; i < num; i++) {
				int b = Integer.parseInt(st.nextToken());
				graph.get(a).add(b);
				indegree[b] += 1;
			}
		}
		
		topologySort();
	}

}