import java.util.*;
import java.lang.*;
import java.io.*;

class Main
{
	public static void main (String[] args) throws java.lang.Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		if (K == 1 || K == N - 1) {
		    System.out.println(0);
		    return;
		}
		else if (N % K == 0 || N % (N - K) == 0) {
		    System.out.println(0);
		    return;
		}
		else {
		    int R = Math.min(K, N - K);
		    
		    int C = gcd(N, R);
		    System.out.println(((N / C) * ((long)(R / C) - 1)));
		    return;
		}
	}
	
	public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
