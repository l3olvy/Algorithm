import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int n, s;
	public static int[] arr;
	public static int count = 0;
	
	public static void dfs(int start, int sum) {
		if(start >= n)
			return;
		sum += arr[start];
		if(sum == s) {
			++count;
		}
		
		dfs(start + 1, sum);
		dfs(start + 1, sum - arr[start]);
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
		System.out.println(count);
	}
}