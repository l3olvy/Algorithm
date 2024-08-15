import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[][] map;
	static int N, M;
	// 상, 하, 좌, 우
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static int max = 0;
	
	public static class Node {
		int r;
		int c;
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		makeWall(0);
		
		System.out.println(max);
	}
	
	// 조합을 이용해서 벽 3개 세우기
	public static void makeWall(int cnt) {
		if (cnt == 3) {
			countSafeArea();
			return;
		}
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] == 0) {
					map[r][c] = 1;
					makeWall(cnt + 1);
					map[r][c] = 0;
				}
			}
		}
	}
	
	// map을 깊은 복사한 copyMap의 값이 2면 바이러스 퍼뜨리고 최종 안전 영역 구하기
	public static void countSafeArea() {
		int[][] copyMap = new int[N][M];
		for (int r = 0; r < N; r++)
			System.arraycopy(map[r], 0, copyMap[r], 0, M);
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (copyMap[r][c] == 2)
					spreadVirus(copyMap, r, c);
			}
		}
		
		int count = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (copyMap[r][c] == 0)
					count++;
			}
		}
		
		max = Math.max(max, count);
	}
	
	// bfs를 이용하여 바이러스 퍼뜨리기, 바이러스가 퍼진 것은 3으로 저장해서  반복 막기
	public static void spreadVirus(int[][] copyMap, int r, int c) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(r, c));
		
		while(!q.isEmpty()) {
			Node n = q.poll();
			
			for (int d = 0; d < 4; d++) {
				int nr = n.r + dr[d];
				int nc = n.c + dc[d];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= M)
					continue;
				
				if (copyMap[nr][nc] == 0) {
					copyMap[nr][nc] = 3;
					q.add(new Node(nr, nc));
				}
			}
		}
		
	}
}
