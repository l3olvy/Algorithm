import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, memories[], costs[];
    static int[] dp;
    static int INF = Integer.MAX_VALUE, max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        memories = new int[N];
        costs = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            memories[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
        	int temp = Integer.parseInt(st.nextToken());
            costs[i] = temp;
            max += temp;
        }

        dp = new int[max + 1]; // dp 배열을 비용에 따라 설정

        // 각 앱을 고려하여 dp 배열 갱신
        for (int i = 0; i < N; i++) {
            // 역순으로 dp 배열 갱신 (덮어씌우지 않기 위해)
            for (int j = max; j >= costs[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - costs[i]] + memories[i]);
            }
        }

        // 필요한 메모리 M 바이트 이상을 확보할 수 있는 최소 비용을 찾음
        int result = INF;
        for (int i = 0; i <= max; i++) {
            if (dp[i] >= M) {
                result = Math.min(result, i);
            }
        }
        System.out.println(result);
    }
}
