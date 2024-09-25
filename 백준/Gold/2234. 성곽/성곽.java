import java.util.*;
import java.io.*;

public class Main {
	
	static int N, M, map[][], num[][], category[][];
	static int idx = 0, maxRoom = 0, countRoom = 0, maxRemoveRoom = 0;
	static boolean visited[][];
	// 1 : 좌, 2 : 상, 4 : 우, 8 : 하
	static int dx[] = {0, 0, -1, 0, 0, 0, 0, 0, 1};
	static int dy[] = {0, -1, 0, 0, 1, 0, 0, 0, 0};
	
	public static class Node {
		int x, y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "[" + x + ", " + y + "]";
		}
		
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[M][N];
		num = new int[M][N];
		category = new int[M][N];
		visited = new boolean[M][N];
		
		for (int r = 0; r < M; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int r = 0; r < M; r++) {
			for (int c = 0; c < N; c++) {
				if (!visited[r][c]) {
					countRoom++;
					maxRoom = Math.max(maxRoom, bfs(r, c, 0));
				}
			}
		}
		
		for (int r = 0; r < M; r++) {
			for (int c = 0; c < N; c++) {
				for (int i = 1; i <= 8; i *= 2) {			
					// 벽이면
					if ((map[r][c] & i) != 0) {
						int nx = r + dx[i];
						int ny = c + dy[i];
						
						// 범위 넘어가면 pass
						if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;					
						// 같은 칸이었으면 pass
						if (category[r][c] == category[nx][ny]) continue;	
						
						maxRemoveRoom = Math.max(maxRemoveRoom, num[r][c] + num[nx][ny]);
					}
				}
			}
		}
		
		System.out.println(countRoom);
		System.out.println(maxRoom);
		System.out.println(maxRemoveRoom);
		
	}
	
	public static int bfs(int x, int y, int cnt) {
		Queue<Node> q = new LinkedList<Node>();
		Queue<Node> save = new LinkedList<Node>();
		
		q.offer(new Node(x, y));
		save.offer(new Node(x, y));
		visited[x][y] = true;
		
		while (!q.isEmpty()) {
			Node node = q.poll();
			cnt++;
			for (int i = 1; i <= 8; i *= 2) {
				// 벽이 아니면
				if ((map[node.x][node.y] & i) == 0) {
					int nx = node.x + dx[i];
					int ny = node.y + dy[i];

					if (!visited[nx][ny]) {
						q.offer(new Node(nx, ny));
						save.offer(new Node(nx, ny));
						visited[nx][ny] = true;
					}
				}
			}
		}
		
		while (!save.isEmpty()) {
			Node node = save.poll();
			num[node.x][node.y] = cnt;
			category[node.x][node.y] = idx;
		}
		
		idx++;
		return cnt;
	}

}
