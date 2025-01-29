import java.util.*;
import java.lang.*;
import java.io.*;

class Main
{
	public static void main (String[] args) throws java.lang.Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		char[] arrA = (" " + st.nextToken()).toCharArray();
		st = new StringTokenizer(br.readLine());
		char[] arrB = (" " + st.nextToken()).toCharArray();
		
		int map[][] = new int[arrA.length][arrB.length];
		int result = 0;

	    for (int a = 1; a < arrA.length; a++) {
	        for (int b = 1; b < arrB.length; b++) {
	            if (arrA[a] == arrB[b]) {
	                map[a][b] = map[a - 1][b - 1] + 1;
	                result = Math.max(result, map[a][b]);
	            }
	        }
	    }
		
		System.out.print(result);
	}
}