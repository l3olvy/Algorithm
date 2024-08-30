import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
	
	static int N, maxCnt, minLen, map[][];
	static ArrayList<Node> processors;
	// 상, 우, 하, 좌
	static int dx[] = {-1, 0, 1, 0};
	static int dy[] = {0, 1, 0, -1};
	
	public static class Node {
		int x, y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		final int T = Integer.parseInt(st.nextToken());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			
			processors = new ArrayList<>();
			
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					if (map[r][c] == 1 && r != 0 && r != N - 1 && c != 0 && c != N - 1)
						processors.add(new Node(r, c));
				}
			}
			
			maxCnt = 0;
			minLen = Integer.MAX_VALUE;
			
			dfs(0, 0, 0);

			sb.append("#" + t + " " + minLen + "\n");
		}
		System.out.println(sb);
	}
	
	public static void dfs(int start, int count, int len) {
        if (count + processors.size() - start < maxCnt) return;
		
		if (start == processors.size()) {
			if (maxCnt < count) {
				maxCnt = count;
				minLen = len;
			}
			else if (maxCnt == count) {
				minLen = Math.min(minLen, len);
			}	
			return;
		}
		
		Node n = processors.get(start);
		int x = n.x;
		int y = n.y;
//		dfs(start + 1, count, len);
		for (int i = 0; i < 4; i++) {
			
			int c = connect(x, y, i);
			if (c > 0) {
			count += 1;
			len += c;
			}
			
			dfs(start + 1, count, len);
			changeBlock(x, y, i, c, 0);
			if (c > 0) {
				count -= 1;
				len -= c;
			}
			
		}
		
	}
	

	public static int connect(int x, int y, int d) {
		
		int cnt = 0;
		int tx = x;
		int ty = y;
		
		while (true) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			// 벽과 닿으면
			if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
				changeBlock(tx, ty, d, cnt, 2);
				return cnt;
			}
			
			if (map[nx][ny] == 1 || map[nx][ny] == 2)
				return 0;
			
			if (map[nx][ny] == 0) {
				cnt++;
			}
			
			x = nx;
			y = ny;
		}
	}
	
	public static void changeBlock(int x, int y, int d, int cnt, int idx) {
		for (int i = 0; i < cnt; i++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			map[nx][ny] = idx;
			x = nx;
			y = ny;
		}
	}

}
