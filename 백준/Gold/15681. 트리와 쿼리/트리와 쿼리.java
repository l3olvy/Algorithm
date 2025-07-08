import java.util.*;
import java.lang.*;
import java.io.*;

class Main
{
    static int N, R, Q, num[];
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean visited[];

	public static void main (String[] args) throws java.lang.Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i <= N; i++) {
		    graph.add(new ArrayList<Integer>());
		}
		num = new int[N + 1];
		Arrays.fill(num, 1);
		visited = new boolean[N + 1];
		
		for (int i = 0; i < N - 1; i++) {
		    st = new StringTokenizer(br.readLine());
		    int U = Integer.parseInt(st.nextToken());
		    int V = Integer.parseInt(st.nextToken());
		    
		    graph.get(U).add(V);
		    graph.get(V).add(U);
		}
		
		visited[R] = true;
		dfs(R);

		for (int i = 0; i < Q; i++) {
		    st = new StringTokenizer(br.readLine());
		    int U = Integer.parseInt(st.nextToken());
		    sb.append(num[U] + "\n");
		}	
		System.out.print(sb.toString());
	}
	
	public static int dfs(int x) {  
	    for (int i = 0; i < graph.get(x).size(); i++) {
	        int next = graph.get(x).get(i);        
	        if (!visited[next]) {
	            visited[next] = true;
	            num[x] += dfs(next);        
	        }
	    }    
	    return num[x];
	}
}