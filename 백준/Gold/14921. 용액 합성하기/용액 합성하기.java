import java.util.*;
import java.lang.*;
import java.io.*;

class Main
{
    static int N, arr[];
    
	public static void main (String[] args) throws java.lang.Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		arr = new int[N];
		
		for (int i = 0; i < N; i++) {
		    arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int s = 0;
		int e = N - 1;
		int min = Integer.MAX_VALUE;
		
		while (s < e) {
		    int sum = arr[s] + arr[e];
		    
		    if (sum < 0) {
		        s++;
		        if (Math.abs(min) > Math.abs(sum)) {
		            min = sum;
		        }
		    } else if (sum > 0) {
		        e--;
		        if (Math.abs(min) > Math.abs(sum)) {
		            min = sum;
		        }
		    } else {
		        min = 0;
		        break;
		    }
		}
	
        System.out.print(min);
	}
}
