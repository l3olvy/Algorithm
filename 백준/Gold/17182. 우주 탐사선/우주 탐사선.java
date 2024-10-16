import java.util.*;
import java.io.*;

public class Main {

	static int N, K, map[][], count, min = Integer.MAX_VALUE;
	static boolean visited[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		for (int k = 0; k < N; k++) {
			for (int a = 0; a < N; a++) {
				for (int b = 0; b < N; b++) {
					map[a][b] = Math.min(map[a][b], map[a][k] + map[k][b]);
				}
			}
		}
		visited = new boolean[N];
		visited[K] = true;
		goToSpace(K, 1, 0);

		System.out.println(min);
	}

	// 이 함수는 i번째를 방문할건데 그 후에 아직 방문하지 않은 행성을 탐사하는데 걸리는 최소 시간을 반환
	public static void goToSpace(int planet, int count, int sum) {
		if (count == N) {
			min = Math.min(min, sum);
			return;
		}
		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				goToSpace(i, count + 1, sum + map[planet][i]);
				visited[i] = false;
			}
		}
	}
}