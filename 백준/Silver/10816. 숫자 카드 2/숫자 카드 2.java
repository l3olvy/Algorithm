import java.util.*;
import java.lang.*;
import java.io.*;

class Main
{
    static int N, M, arr[];
    
	public static void main (String[] args) throws java.lang.Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		arr = new int[N];
		for (int i = 0; i < N; i++) {
		    arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
		    int num = Integer.parseInt(st.nextToken());
		    int s = binarySearch(num);
		    int e = binarySearch2(num);
		    
		    if (s < e) {
		        sb.append((e - s + 1) + " ");
		    } else if (s == e) {
		        sb.append(1 + " ");
		    } else {
		        sb.append(0 + " ");
		    }
		}
		System.out.print(sb.toString());
	}
	
	public static int binarySearch(int x) {
	    int s = 0;
	    int e = N - 1;
	    
	    while (s <= e) {
	        int mid = (s + e) / 2;
	        
	        if (arr[mid] < x) s = mid + 1;
	        else e = mid - 1;
	    }

	    return s;
	}
	
	public static int binarySearch2(int x) {
	    int s = 0;
	    int e = N - 1;
	    
	    while (s <= e) {
	        int mid = (s + e) / 2;
	        
	        if (arr[mid] <= x) s = mid + 1;
	        else e = mid - 1;
	    }
	    
	    return e;
	}
}