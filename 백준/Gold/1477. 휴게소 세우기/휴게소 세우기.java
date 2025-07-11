import java.util.*;
import java.lang.*;
import java.io.*;

class Main
{
    static int N, M, L, arr[];
    
	public static void main (String[] args) throws java.lang.Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		arr = new int[N + 2];
		arr[0] = 0;
		arr[N + 1] = L;
		
		for (int i = 1; i <= N; i++) {
		    arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		int s = 1;
		int e = L - 1;
		
		while (s <= e) {
		    int mid = (s + e) / 2;
		    int cnt = 0;
		    
		    for (int i = 1; i <arr.length; i++) {
		        cnt += (arr[i] - arr[i - 1] - 1) / mid;
		    }
		    
		    if (cnt > M) {
		        s = mid + 1;
		    } else {
		        e = mid - 1;
		    }
		}
		
		System.out.print(s);

	}
}
