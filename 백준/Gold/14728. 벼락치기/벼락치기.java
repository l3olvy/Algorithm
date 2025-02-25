import java.util.*;
import java.lang.*;
import java.io.*;

class Main
{
    static int N, T, section[][], map[][];
	public static void main (String[] args) throws java.lang.Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		section = new int[N + 1][2];
		map = new int[N + 1][T + 1];
		
		for (int i = 1; i <= N; i++) {
		    st = new StringTokenizer(br.readLine());
		    section[i][0] = Integer.parseInt(st.nextToken());
		    section[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i <= N; i++) {
		    for (int j = 1; j <= T; j++) {
		        if (j < section[i][0]) {
		            map[i][j] = map[i - 1][j];
		        }
		        else {
		            map[i][j] = Math.max(map[i - 1][j], map[i - 1][j - section[i][0]] + section[i][1]);
		        }
		    }
		}
		
		System.out.println(map[N][T]);	
	}
}