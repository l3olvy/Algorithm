import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int n;
	public static int[] arr;
	public static int[] oper = new int[4];
	public static int min = 1000000000;
	public static int max = -1000000000;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 입력값 받아 저장
		n = Integer.parseInt(st.nextToken());
		arr = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 4; i++)
			oper[i] = Integer.parseInt(st.nextToken());
		
		// 가장 첫 번째 정수는 이전에 연산자가 필요 X -> 1, arr[0] 담아서 dfs 호출 
		dfs(1, arr[0]);
		
		// max, min 출력
		System.out.println(max);
		System.out.println(min);
		
	}
	
	public static void dfs(int idx, int num) {
		// dfs 종료 조건
		if (idx == n) {
			min = Math.min(min, num);
			max = Math.max(max, num);
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			// + : 0, - : 1, * : 2, / : 3
			// 해당 연산자가 존재하면 사용했다는 의미로 하나 줄인 후 연산 한 결과와 idx + 1를 갖고 dfs 호출
			if(oper[i] > 0) {
				oper[i]--;
				
				if(i == 0)
					dfs(idx + 1, num + arr[idx]);
				else if(i == 1)
					dfs(idx + 1, num - arr[idx]);
				else if(i == 2)
					dfs(idx + 1, num * arr[idx]);
				else if(i == 3)
					dfs(idx + 1, (int)(num / arr[idx]));
			
				oper[i]++;
			}
		}
	}
}
