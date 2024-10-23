import java.util.*;
import java.io.*;
public class Main {
	
	static int N, M, K, map[][], dir, x, y, result;
	// 상, 우, 하, 좌
	static int dx[] = {-1, 0, 1, 0};
	static int dy[] = {0, 1, 0, -1};
	static int dice[] = {0, 1, 2, 3, 4, 5, 6};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		dir = x = y = 1;
		result = 0;
		
		map = new int[N + 1][M + 1];
		
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		while (K-- > 0) {
			moveDice();
			result += map[x][y] * getScore();
			rotateDir();
		}
		System.out.println(result);
	}
	
	public static void moveDice() {
		int nx = x + dx[dir];
		int ny = y + dy[dir];
		
		if (nx > 0 && ny > 0 && nx <= N && ny <= M) {
			x = nx;
			y = ny;
		}
		else {
			// 북쪽으로 지도를 벗어나면
			if (nx <= 0) dir = 2;
			// 동쪽으로 지도를 벗어나면
			else if (ny > M) dir = 3;
			// 남쪽으로 지도를 벗어나면
			else if (nx > N) dir = 0;
			// 서쪽으로 지도를 벗어나면
			else dir = 1;

			x += dx[dir];
			y += dy[dir];
		}
		changeValue();
	}
	
	public static void changeValue() {
		// 북
		if (dir == 0) {
			int n1 = dice[1], n2 = dice[2], n5 = dice[5], n6 = dice[6];
			dice[1] = n5;
			dice[2] = n1;
			dice[5] = n6;
			dice[6] = n2;
		}
		// 동
		else if (dir == 1) {
			int n1 = dice[1], n3 = dice[3], n6 = dice[6], n4 = dice[4];
			dice[1] = n4;
			dice[3] = n1;
			dice[6] = n3;
			dice[4] = n6;
		}
		// 남
		else if (dir == 2) {
			int n1 = dice[1], n2 = dice[2], n5 = dice[5], n6 = dice[6];
			dice[1] = n2;
			dice[2] = n6;
			dice[5] = n1;
			dice[6] = n5;
		}
		// 서
		else {
			int n1 = dice[1], n3 = dice[3], n6 = dice[6], n4 = dice[4];
			dice[1] = n3;
			dice[3] = n6;
			dice[6] = n4;
			dice[4] = n1;
		}
	}
	
	public static class Node {
		int x, y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static int getScore() {
		Queue<Node> q = new ArrayDeque<Node>();
		int count = 1;
		q.add(new Node(x, y));
		boolean visited[][] = new boolean[N + 1][M + 1];
		visited[x][y] = true;
		
		while (!q.isEmpty()) {
			Node n = q.poll();
			
			for (int d = 0; d < 4; d++) {
				int nx = n.x + dx[d];
				int ny = n.y + dy[d];
				
				if (nx > 0 && ny > 0 && nx <= N && ny <= M) {
					if (map[nx][ny] == map[x][y] && !visited[nx][ny]) {
						q.add(new Node(nx, ny));
						visited[nx][ny] = true;
						count++;
					}
				}
			}
		}
		return count;
	}
	
	public static void rotateDir() {
		// A > B -> 시계방향
		if (dice[6] > map[x][y]) dir = (dir == 3) ? 0 : dir + 1;
		// A < B -> 반시계방향
		else if (dice[6] < map[x][y]) dir = (dir == 0) ? 3 : dir - 1;
	}
}