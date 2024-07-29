import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    public static int n, s;
    public static int[] arr;
    public static int count = 0;

    public static void dfs(int start, int sum) {
        // 중간 과정에서 목표 합에 도달했을 때도 체크
        if(sum == s) {
            count++;
        }
        
        for(int i = start; i < n; i++) {
            dfs(i + 1, sum + arr[i]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        arr = new int[n];

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        dfs(0, 0);

        // 부분 수열의 크기가 0인 경우는 세지 않기 때문에 count를 조정
        if (s == 0) {
            count--;
        }
        
        System.out.println(count);
    }
}