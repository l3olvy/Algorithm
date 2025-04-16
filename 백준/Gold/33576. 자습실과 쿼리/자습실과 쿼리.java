import java.util.*;
import java.lang.*;
import java.io.*;

class Main
{
    static int N, M, Q, start, end;
    static long arr[];
    
	public static void main (String[] args) throws java.lang.Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		arr = new long[N + 1];
		start = 0;
		end = N;
		
		for (int i = 0; i < M; i++) {
		    st = new StringTokenizer(br.readLine());
		    int W = Integer.parseInt(st.nextToken());
		    int D = Integer.parseInt(st.nextToken());
		    arr[W] = D;
		}
		
		// 누적합
		for (int i = 2; i <= N; i++) {
		    arr[i] += arr[i - 1];
		}
		
		// 1번 ~ Q번 학생 위치
		for (int i = 0; i < Q; i++) {
		    st = new StringTokenizer(br.readLine());
		    int P = Integer.parseInt(st.nextToken());
		    
		    // 모든 벽이 다 깨진 경우
		    if (start >= end || P <= start || P >= end) {
		        sb.append(0);
		        if (i != Q - 1) {
    		        sb.append("\n");
    		    }
		        continue;
		    }
		    
		    long left = arr[P] - arr[start];
		    long right = arr[end] - arr[P];
		    
		    if (left < right) {
		        start = P;
		        sb.append(left);
		    }
		    else if (left > right) {
		        end = P;
		        sb.append(right);
		    }
		    else {
		        int leftD = P - 1;
		        int rightD = N - P;
		        
		        if (leftD <= rightD) {
		            start = P;
		            sb.append(left);
		        }
		        else {
		            end = P;
		            sb.append(right);
		        }
		    }
		    
		    if (i != Q - 1) {
		        sb.append("\n");
		    }
		}
		
		System.out.print(sb);
	}
}
