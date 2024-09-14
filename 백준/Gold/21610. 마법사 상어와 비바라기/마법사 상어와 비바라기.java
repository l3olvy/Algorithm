import java.util.*;
import java.io.*;

public class Main {

	static int N, M, map[][], sumWater;
	static ArrayList<Node> clouds;
	static boolean prevCloud[][];
	
	// 이동방향 ←, ↖, ↑, ↗, →, ↘, ↓, ↙
	static int dx[] = {0, 0, -1, -1, -1, 0, 1, 1, 1};
	static int dy[]	= {0, -1, -1, 0, 1, 1, 1, 0, -1};
	
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
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		clouds = new ArrayList<Node>();
		
		// 초기 비바라기 마법 시전
		clouds.add(new Node(N - 1, 0));
		clouds.add(new Node(N - 1, 1));
		clouds.add(new Node(N - 2, 0));
		clouds.add(new Node(N - 2, 1));
		
		prevCloud = new boolean[N][N];

		// M번 명령에 따른 비바라기 마법 시전
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());

			moveCloud(d, s);
			copyWater();
			makeCloud();
		}
		
		// 바구니에 들어있는 물의 양의 합 구한 후 출력
		sumWater = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				sumWater += map[r][c];
			}
		}
		
		System.out.println(sumWater);
	}
	
	// 구름 이동 & 비내림 물 +1 증가
	public static void moveCloud(int d, int s) {
		
		for (int i = 0; i < clouds.size(); i++) {
			Node cloud = clouds.get(i);
			
			// 구름 이동
			int nx = cloud.x + dx[d] * s;
			int ny = cloud.y + dy[d] * s;

			if (nx >= N) nx %= N;
			else if (nx < 0) nx = -((-nx) % N) == 0 ? 0 : -((-nx) % N) + N;
			if (ny >= N) ny %= N;
			else if (ny < 0) ny = -((-ny) % N) == 0 ? 0 : -((-ny) % N) + N;

			cloud.x = nx;
			cloud.y = ny;
			
			// 비내림 물 +1 증가
			prevCloud[cloud.x][cloud.y] = true;
			map[cloud.x][cloud.y] += 1;
		}
	}
	
	// 물복사버그 마법
	public static void copyWater() {
		for (int i = 0; i < clouds.size(); i++) {
			Node cloud = clouds.get(i);
			int count = 0;

			for (int d = 2; d <= 8; d += 2) {
				int nx = cloud.x + dx[d];
				int ny = cloud.y + dy[d];
				// 범위 넘어가면 pass
				if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
					continue;
				}
				
				if (map[nx][ny] > 0) count++;
				
			}

			map[cloud.x][cloud.y] += count;
		}
		
		// 이전 구름 삭제
		clouds.removeAll(clouds);
	}
	
	// 물의 양 2 이상인 칸에 구름 생성 후 물 -2 감소
	public static void makeCloud() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] >= 2 && !prevCloud[r][c]) {
					clouds.add(new Node(r, c));
					map[r][c] -= 2;
					prevCloud[r][c] = true;
				}
				if (prevCloud[r][c])
					prevCloud[r][c] = false;
			}
		}
	}
}