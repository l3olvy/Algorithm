import java.util.*;
import java.io.*;

public class Main {
	
	static int N, M, K;
	static List<Shark> sharks;
	static List<int[][]> directions = new ArrayList<int[][]>();
	static Place map[][];

	// 1 위, 2 아래, 3 왼쪽, 4 오른쪽
	static int[] dx = {0, -1, 1, 0, 0};
	static int[] dy = {0, 0, 0, -1, 1};
	
	public static class Node {
		int x, y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static class Place {
		int num, time;
		boolean isShark;
		
		public Place(int num, int time, boolean isShark) {
			this.num = num;
			this.time = time;
			this.isShark = isShark;
		}
	}
	
	public static class Shark implements Comparable<Shark> {
		int num, x, y, dir;
		
		public Shark(int num, int x, int y, int dir) {
			this.num = num;
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
		
		@Override
		public int compareTo (Shark o) {
			return Integer.compare(this.num, o.num);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 격자 크기 N, 상어 마리수 M, 냄새 소멸 시간 K
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		sharks = new LinkedList<Shark>();
		map = new Place[N][N];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				int n = Integer.parseInt(st.nextToken());
				// 상어가 해당 칸에 존재할 때 map에 냄새 남은 시간, sharks에 상어 정보 저장
				if (n != 0) {
					map[r][c] = new Place(n, K, true);
					sharks.add(new Shark(n, r, c, 0));
				}
			}
		}
		
		// 상어 번호 순 오름차순 정렬
		Collections.sort(sharks);
		
		// 상어 현재 방향 저장
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int d = Integer.parseInt(st.nextToken());
			sharks.get(i).dir = d;
		}
		
		// directions에 각 상어의 방향 우선순위 저장
		directions.add(new int[5][4]);
		for (int i = 1; i <= M; i++) {
			directions.add(new int[5][4]);
			for (int r = 1; r <= 4; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < 4; c++) {
					directions.get(i)[r][c] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		gameStart();
	}
	
	public static void gameStart() {
		int t = 0;
		
		while (true) {
			if (sharks.size() == 1) {
				System.out.println(t);
				return;
			}
			
			if (t == 1000) {
				System.out.println(-1);
				return;
			}
			
			for (int j = 0; j < sharks.size(); j++) {
				Shark shark = sharks.get(j);
				
				int cnt = 0;
				if (map[shark.x][shark.y] != null)
					map[shark.x][shark.y].isShark = false;
				
				for (int i = 0; i < 4; i ++) {
					int nextDir = directions.get(shark.num)[shark.dir][i];
					int nx = shark.x + dx[nextDir];
					int ny = shark.y + dy[nextDir];
					
					// 냄새가 있는 칸이거나 격자를 벗어나면 continue
					if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
						cnt++;
						continue;
					}
					
					if (map[nx][ny] != null && map[nx][ny].time > 0) {
					    cnt++;
					    continue;
					}
					
					// 냄새가 없고 우선순위에 맞는 칸으로 이동
					shark.x = nx;
					shark.y = ny;
					shark.dir = nextDir;
					break;
				}

				// 이동할 칸이 없는 경우 왔던 칸으로 방향 바꿔서 돌아감 < 여기가 문제, 같은 번호 여러개면 우선순위 고려해야함
				if (cnt == 4) {
					for (int i = 0; i < 4; i ++) {
						int nextDir = directions.get(shark.num)[shark.dir][i];
						int nx = shark.x + dx[nextDir];
						int ny = shark.y + dy[nextDir];
						
						if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
						
						if (map[nx][ny] != null && map[nx][ny].num == shark.num) {
							shark.dir = nextDir;
							shark.x = nx;
							shark.y = ny;
							break;
						}
					}
				}

			}
			
			List<Shark> toRemove = new ArrayList<>();
			for (int j = 0; j < sharks.size(); j++) {
				Shark shark = sharks.get(j);
				

				// 상어가 겹치는 경우
				if (map[shark.x][shark.y] != null && map[shark.x][shark.y].isShark) {
					toRemove.add(shark);
					continue;
				}
				// 냄새 남기기
				map[shark.x][shark.y] = new Place(shark.num, K, true);
			}
			sharks.removeAll(toRemove);
			reduceTime();
			t++;
		}
	}
	
	public static void reduceTime() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] != null) {
					if (map[r][c].time > 1) {
						if (!map[r][c].isShark)
							map[r][c].time--;
					}		
					else
						map[r][c] = null;
				}
			}
		}
	}
}
