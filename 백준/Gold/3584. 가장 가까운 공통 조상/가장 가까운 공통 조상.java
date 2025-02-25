import java.util.*;
import java.lang.*;
import java.io.*;

class Main
{
    static int T, N, A, B;
    static boolean visited[];
    static ArrayList<ArrayList<Integer>> tree;
    static StringBuilder sb = new StringBuilder();
	public static void main (String[] args) throws java.lang.Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		
		T = Integer.parseInt(st.nextToken());
		
		for (int t = 1; t <= T; t++) {
		    st = new StringTokenizer(br.readLine());
    		N = Integer.parseInt(st.nextToken());
    		visited = new boolean[N + 1];
    		
    		tree = new ArrayList<ArrayList<Integer>>();
    		
    		for (int i = 0; i <= N; i++) {
    		    tree.add(new ArrayList<Integer>());
    		}
    		
    		for (int i = 0; i < N - 1; i++) {
    		    st = new StringTokenizer(br.readLine());
    		    int nodeA = Integer.parseInt(st.nextToken());
    		    int nodeB = Integer.parseInt(st.nextToken());
    		    
    		    tree.get(nodeB).add(nodeA);
    		}
    		
    		st = new StringTokenizer(br.readLine());
    		A = Integer.parseInt(st.nextToken());
    		B = Integer.parseInt(st.nextToken());
    		visited[A] = true;
    		visited[B] = true;
    		dfs(A);
    		dfs(B);
		}
		
		System.out.print(sb);
	}
	
	public static void dfs(int X) {
	    if (tree.get(X).size() == 0) {
	        return;
	    }
	    
	    int N = tree.get(X).get(0);
	    if (visited[N] == true) {
	        sb.append(N + "\n");
	        return;
	    } else {
	        visited[N] = true;
	        dfs(N);   
	    }
	}
}