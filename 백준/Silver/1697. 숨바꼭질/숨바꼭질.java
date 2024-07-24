import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static int n, k;
    public static int[] dp = new int[100001];
    
    public static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        
        q.offer(start);
        dp[start] = 0;
        
        while(!q.isEmpty()) {
            int y = q.poll();
            
            if (y == k) {
                return;
            }
            
            if ((y - 1) >= 0 && dp[y - 1] == Integer.MAX_VALUE) {
                q.offer(y - 1);
                dp[y - 1] = dp[y] + 1;
            }
            if ((y + 1) < 100001 && dp[y + 1] == Integer.MAX_VALUE) {
                q.offer(y + 1);
                dp[y + 1] = dp[y] + 1;
            }
            if ((y * 2) < 100001 && dp[y * 2] == Integer.MAX_VALUE) {
                q.offer(y * 2);
                dp[y * 2] = dp[y] + 1;
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        
        Arrays.fill(dp, Integer.MAX_VALUE);
        
        bfs(n);
        
        System.out.println(dp[k]);
    }

}