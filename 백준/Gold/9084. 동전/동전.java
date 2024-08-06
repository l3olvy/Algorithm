import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		final int T = Integer.parseInt(st.nextToken());
		
		// 테스트 케이스 돌리기
		for (int i = 0; i < T; i++) {
			
			// 입력값 저장
			st = new StringTokenizer(br.readLine());
			final int N = Integer.parseInt(st.nextToken());
			
			int[] money = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				money[j] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			final int M = Integer.parseInt(st.nextToken());

			int[] dp = new int[M + 1];
			
			dp[0] = 1;
			
			// 현재 금액 - 동전 종류(금액)로 만들 수 있는 경우의 수에 현재 금액의 경우의 수 더하기 
			for (int m : money) {
				for (int j = 1; j < M + 1; j++) {
					if (j >= m) 
						dp[j] += dp[j - m];
				}
			}
			
			sb.append(dp[M] + "\n");
		}
		System.out.println(sb);
	}
}
