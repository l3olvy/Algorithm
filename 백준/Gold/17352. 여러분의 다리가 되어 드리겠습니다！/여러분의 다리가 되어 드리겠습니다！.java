import java.util.*;
import java.lang.*;
import java.io.*;

class Main
{
    static int N, parent[];
    static HashSet<Integer> hSet;

	public static void main (String[] args) throws java.lang.Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		parent = new int[N + 1];
		
		for (int i = 1; i <= N; i++) {
		    parent[i] = i;
		}
		
		hSet = new HashSet<Integer>();

		
		for (int i = 0; i < N - 2; i++) {
		    st = new StringTokenizer(br.readLine());
		    int a = Integer.parseInt(st.nextToken());
		    int b = Integer.parseInt(st.nextToken());
		    
		    if (findParent(a) != findParent(b)) {
		        makeUnion(a, b);
		    }
		}
		
		for (int i = 1; i <= N; i++) {
		    int n = findParent(i);
		    hSet.add(n);
		    parent[i] = n;
		}
		
		for (Integer i : hSet) {
		    sb.append(i + " ");
		}
		
		System.out.print(sb);

	}
	
	public static int findParent(int x) {
	    if (parent[x] == x) return x;
	    else return parent[x] = findParent(parent[x]);
	}
	
	public static void makeUnion(int a, int b) {
	    a = findParent(a);
	    b = findParent(b);
	    
	    if (a < b) parent[b] = a;
	    else parent[a] = b;
	}
}