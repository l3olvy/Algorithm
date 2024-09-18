import java.util.*;
import java.io.*;

public class Main {

	// ↑, ↖, ←, ↙, ↓, ↘, →, ↗
	public static int dx[] = {-1, -1, 0, 1, 1, 1, 0, -1};
	public static int dy[] = {0, -1, -1, -1, 0, 1, 1, 1};
	public static int N = 4, max;
	
	public static class Shark {
		int x, y, dir, sum;

		public Shark(int x, int y, int dir, int sum) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.sum = sum;
		}
	}
	
	public static class Fish {
		int x, y, id, dir;
		boolean isAlive;

		public Fish(int x, int y, int id, int dir, boolean isAlive) {
			this.x = x;
			this.y = y;
			this.id = id;
			this.dir = dir;
			this.isAlive = isAlive;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int[][] map = new int[N][N];
		Fish[] fishes = new Fish[17];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				int id = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken()) - 1;
				
				Fish f = new Fish(r, c, id, dir, true);
				map[r][c] = id;
				fishes[id] = f;
			}
		}
		
		// 초기 상어 좌표
		Fish f = fishes[map[0][0]];
		Shark shark = new Shark(0, 0, f.dir, f.id);
		f.isAlive = false;
		map[0][0] = -1;
		
		dfs(map, shark, fishes);
	
		System.out.println(max);
	}
	
	public static void moveFish(int[][] map, Shark shark, Fish[] fishes) {
		for (int i = 1; i <= 16; i++) {
			// 이미 먹힌 물고기면 pass
			if (!fishes[i].isAlive) continue;

			int x = fishes[i].x;
			int y = fishes[i].y;
			
			int dir = fishes[i].dir;

			for (int j = 0; j < 8; j++) {
				int nd = (dir + j) % 8;
				fishes[i].dir = nd;
				int nx = x + dx[nd];
				int ny = y + dy[nd];
				
				// 범위를 벗어나지 않고 상어가 아니라면
                if (nx >= 0 && ny >= 0 && nx < N && ny < N && map[nx][ny] != -1) {
                    // 빈 칸이었다면
                    if (map[nx][ny] == 0) {
                    	map[fishes[i].x][fishes[i].y] = 0; //기존 위치 빈칸으로 
                    	fishes[i].x = nx;
                    	fishes[i].y = ny;
						map[nx][ny] = i; 
                    } else {
                    	// 바꿀 물고기 위치 변경 
						int changeFish = fishes[map[nx][ny]].id;
						fishes[changeFish].x = fishes[i].x;
						fishes[changeFish].y = fishes[i].y;
						map[fishes[changeFish].x][fishes[changeFish].y] = changeFish;

						//현재 물고기 위치 변경 
						fishes[i].x = nx;
						fishes[i].y = ny;
						map[nx][ny] = i;
                    }
                    break;
                }
			}
		}
	}
	
	public static void dfs(int[][] map, Shark shark, Fish[] fishes) {
		max = Math.max(max, shark.sum);
		
		// 물고기 이동
		moveFish(map, shark, fishes);
		
		// 물고기 이동 후 상어가 이동할 수 있는 경우의 수
		for (int i = 1; i < N; i++) {
			int nx = shark.x + dx[shark.dir] * i;
			int ny = shark.y + dy[shark.dir] * i;
			
			if (nx >= 0 && ny >= 0 && nx < N && ny < N && map[nx][ny] > 0) {
				int[][] copyMap = new int[N][N];
				for (int j = 0; j < N; j++) {
					System.arraycopy(map[j], 0, copyMap[j], 0, N);
				}
				
				Fish[] copyFishes = new Fish[17];
				for (int j = 1; j <= 16; j++) {
					copyFishes[j] = new Fish(fishes[j].x, fishes[j].y, fishes[j].id, fishes[j].dir, fishes[j].isAlive);
				}
				
				// 이전에 상어가 있던 곳 0으로 처리
				copyMap[shark.x][shark.y] = 0;
				// 먹힌 물고기 처리 및 상어 위치 이동
				Fish eatenFish = copyFishes[map[nx][ny]];
				Shark copyShark = new Shark(eatenFish.x, eatenFish.y, eatenFish.dir, shark.sum + eatenFish.id);
				eatenFish.isAlive = false;
				copyMap[eatenFish.x][eatenFish.y] = -1;

				dfs(copyMap, copyShark, copyFishes);
			}
		}
	}
}