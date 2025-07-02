import java.util.*;
import java.lang.*;
import java.io.*;

class Main
{
    static int N, type, list[];
    static long k, fact[], answer;
    static boolean visited[];

	public static void main (String[] args) throws java.lang.Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        
        fact = new long[N + 1];
        factorial();
        
        st = new StringTokenizer(br.readLine());
        type = Integer.parseInt(st.nextToken());

        if (type == 1) {
            k = Long.parseLong(st.nextToken());
            visited = new boolean[N + 1];
            System.out.print(getKthPerm());
        } else {
            list = new int[N];
            for (int i = 0; i < N; i++) {
                list[i] = Integer.parseInt(st.nextToken());
            }
            visited = new boolean[N + 1];
            System.out.print(getPermEqualsList());
        }
	}
	
	public static void factorial() {
	    fact[0] = 1;
	    
	    for (int i = 1; i <= N; i++) {
	        fact[i] = fact[i - 1] * i;
	    }
	}
	
	public static String getKthPerm() {
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < N; i++) {
	        for (int j = 1; j <= N; j++) {
	            if (visited[j]) continue;
	            if (k - fact[N - 1 - i] > 0) {
	                k -= fact[N - 1 - i];
	            } else {
	                visited[j] = true;
	                sb.append(j + " ");
	                break;
	            }
	        }
	    }
	    return sb.toString();
	}
	
	public static long getPermEqualsList() {
	    for (int i = 0; i < N; i++) {
	        for (int j = 1; j < list[i]; j++) {
	            if (visited[j]) continue;
	            answer += fact[N - 1 - i];
	        }
	        visited[list[i]] = true;
	    }
	    
	    return answer + 1L;
	}
}
