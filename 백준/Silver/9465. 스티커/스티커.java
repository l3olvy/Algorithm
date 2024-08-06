import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		final int T = Integer.parseInt(st.nextToken());
		
		// 각 테스트 케이스 돌리기
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			final int N = Integer.parseInt(st.nextToken());
			
			int[][] arr = new int[2][N];
			
			// 입력값 2차원 배열에 저장
			for (int j = 0; j < 2; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < N; k++) {
					arr[j][k] = Integer.parseInt(st.nextToken());
				}
			}
			
			if (N == 1) {
				sb.append(Math.max(arr[0][0], arr[1][0]) + "\n");
				continue;
			}
			
			// 2열은 각각 1열 대각선 값을 더해줌
			arr[0][1] += arr[1][0];
			arr[1][1] += arr[0][0];
			
			if (N == 2) {
				sb.append(Math.max(arr[0][1], arr[1][1]) + "\n");
				continue;
			}
			
			// 3열부터는 대각선 방향의 -1열, -2열 중 큰 값을 더해줌
			for (int j = 2; j < N; j++) {
				for (int k = 0; k < 2; k++) {
					int r = (k + 1) % 2;
					arr[k][j] += Math.max(arr[r][j - 1], arr[r][j - 2]);
				}
			}
			
			sb.append(Math.max(arr[0][N - 1], arr[1][N - 1]) + "\n");
		}
		
		System.out.println(sb);
	}

}
