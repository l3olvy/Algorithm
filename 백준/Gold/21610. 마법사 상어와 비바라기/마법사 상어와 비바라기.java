import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;


public class Main {
		static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		static StringBuilder sb = new StringBuilder();
		static StringTokenizer st;

		static Deque<int[]> cloud = new ArrayDeque<>();
		static int N, M, A[][];
		static int[] dr = { 0, 0, -1, -1, -1, 0, 1, 1, 1 }, dc = { 0, -1, -1, 0, 1, 1, 1, 0, -1 }, nearR = { -1, -1, 1, 1 },
				nearC = { -1, 1, -1, 1 };

		public static void main(String[] args) throws IOException {
			st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			A = new int[N][N];

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(in.readLine());
				for (int c = 0; c < N; c++) {
					A[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			// 초반 4개 구름 생성
			for (int r = N - 1; r > N - 3; r--) {
				for (int c = 0; c < 2; c++) {

					cloud.add(new int[] { r, c });
				}
			}

			// M번 이동할 때마다 다음 과정 반복
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(in.readLine());
				int d = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				boolean[][] wasCloud = new boolean[N][N];

				// 1. move cloud & add water
				for (int[] c : cloud) {
					
					int nx = c[0] + dr[d] * s;
					int ny = c[1] + dc[d] * s;

					if (nx >= N) nx %= N;
					else if (nx < 0) nx = -((-nx) % N) == 0 ? 0 : -((-nx) % N) + N;
					if (ny >= N) ny %= N;
					else if (ny < 0) ny = -((-ny) % N) == 0 ? 0 : -((-ny) % N) + N;

					c[0] = nx;
					c[1] = ny;
					A[c[0]][c[1]]++;
					wasCloud[c[0]][c[1]] = true;
				}

				// 2. add water (마법)
				while (!cloud.isEmpty()) {
					int[] c = cloud.poll();

					int row = c[0];
					int col = c[1];

					for (int i = 0; i < 4; i++) {
						int nr = row + nearR[i];
						int nc = col + nearC[i];
						if (nr < 0 || nr >= N || nc < 0 || nc >= N || A[nr][nc] <= 0)
							continue;
						A[row][col]++;

					}
				}


				// 3. find new cloud
				for (int r = 0; r < N; r++) {
					for (int c = 0; c < N; c++) {
						if (A[r][c] > 1 && !wasCloud[r][c]) {
							A[r][c] -= 2;
							cloud.add(new int[] { r, c });
						}
					}
				}
			}

			int sum = 0;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					sum += A[r][c];
				}
			}
			System.out.println(sum);

		}
	}