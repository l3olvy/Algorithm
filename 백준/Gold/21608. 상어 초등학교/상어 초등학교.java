import java.util.*;
import java.util.Map.Entry;
import java.io.*;

public class Main {

	static int N, map[][], count[][], answer = 0;
	static ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
	static boolean visited[];
	static Node loc[];
	// 상, 하, 좌, 우
	static int dx[] = {-1, 1, 0, 0};
	static int dy[] = {0, 0, -1, 1};
	static HashMap<Integer, Integer> maxCnt = new HashMap<>();
	
	public static class Node implements Comparable<Node> {
		int x, y, cnt;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public Node(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
		
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (!(o instanceof Node)) return false;
			Node node = (Node) o;
			return x == node.x && y == node.y;
		}
		
		@Override
		public int hashCode() {
			return 31 * x + y;
		}
		
		@Override
		public int compareTo(Node n) {
			if (Integer.compare(n.cnt, this.cnt) == 0) {
				if (Integer.compare(this.x, n.x) == 0) {
					return Integer.compare(this.y, n.y);
				}
				else return Integer.compare(this.x, n.x);
			}
			else return Integer.compare(n.cnt, this.cnt);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		visited = new boolean[N * N + 1];
		loc = new Node[N * N + 1];
		map = new int[N][N];
		count = new int[N][N];
		for (int i = 0; i <= N * N; i++) {
			list.add(new ArrayList<Integer>());
		}
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (r == 0 || r == N - 1) {
					if (c == 0 || c == N - 1) {
						count[r][c] = 2;
					}
					else count[r][c] = 3;
				}
				else if (c == 0 || c == N - 1) {
					count[r][c] = 3;
				}
				else count[r][c] = 4;
			}
		}
		
		for (int i = 0; i < N * N; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			
			HashMap<Node, Integer> hMap = new HashMap<Node, Integer>();
			
			for (int j = 0; j < 4; j++) {
				int n = Integer.parseInt(st.nextToken());
				list.get(idx).add(n);
				if (visited[n]) {
					Node node = loc[n];
					for (int d = 0; d < 4; d++) {
						int nx = node.x + dx[d];
						int ny = node.y + dy[d];
						
						if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] != 0) continue;
						Node temp = new Node(nx, ny);
						
						if (hMap.containsKey(temp)) {
							hMap.put(temp, hMap.get(temp) + 1);
						} else {
							hMap.put(temp, 1);
						}
					}
				}
			}
			
			if (hMap.size() == 0) {
				Node locNode = getLoc();
				loc[idx] = new Node(locNode.x, locNode.y);
				map[locNode.x][locNode.y] = idx;
			}
			else {
				int maxNum = 0;
				PriorityQueue<Node> pq = new PriorityQueue<>();
				for (Entry<Node, Integer> entry : hMap.entrySet()) {
					maxNum = Integer.max(maxNum, entry.getValue());
				}
				
				for (Entry<Node, Integer> entry : hMap.entrySet()) {
					if (maxNum == entry.getValue()) {
						Node tempNode = entry.getKey();
						pq.offer(new Node(tempNode.x, tempNode.y, count[tempNode.x][tempNode.y]));
					}
				}
				
				Node resultNode = pq.poll();
				map[resultNode.x][resultNode.y] = idx;
				for (int d = 0; d < 4; d++) {
					int nx = resultNode.x + dx[d];
					int ny = resultNode.y + dy[d];
					
					if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] == -1) continue;
					if (count[nx][ny] > 0) count[nx][ny] -= 1;
				}
				loc[idx] = new Node(resultNode.x, resultNode.y);
				count[resultNode.x][resultNode.y] = -1;
				
			}
			visited[idx] = true;
		}
		
		for (int i = 1; i <= N * N; i++) {
			int count = 0;
			Node idxLoc = loc[i];
			for (int d = 0; d < 4; d++) {
				int nx = idxLoc.x + dx[d];
				int ny = idxLoc.y + dy[d];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
				int tN = map[nx][ny];
				if (list.get(i).contains(tN)) count++;
			}
			
			answer += Math.round(Math.pow(10, count - 1));
		}
		
		System.out.print(answer);
	}
	
	public static Node getLoc() {
		int max = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				max = Math.max(max,  count[r][c]);
			}
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (count[r][c] == max) {
					count[r][c] = -1;
					for (int d = 0; d < 4; d++) {
						int nx = r + dx[d];
						int ny = c + dy[d];
						
						if (nx < 0 || nx >= N || ny < 0 || ny >= N || count[nx][ny] == -1) continue;
						if (count[nx][ny] > 0) count[nx][ny] -= 1;
					}
					return new Node(r, c);
				}
			}
		}
		return new Node(-1, -1);
	}

}
