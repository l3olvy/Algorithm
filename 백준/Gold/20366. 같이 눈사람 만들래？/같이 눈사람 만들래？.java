import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	
	static int N, min, idx;
	static int arr[], snow[];
	static ArrayList<Node> nodes;
	
	public static class Node implements Comparable<Node> {
		int a, b, c;

		public Node(int a, int b, int c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.c, o.c);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		min = Integer.MAX_VALUE;
		
		snow = new int[2];
		
		nodes = new ArrayList<Node>();
		comb(0, 0);
		
		Collections.sort(nodes);
		
		for (int s = 0; s < nodes.size() - 1; s++) {
			Node nodeS = nodes.get(s);
			for (int e = s + 1; e < nodes.size(); e++) {
				Node nodeE = nodes.get(e);
				int sA = nodeS.a, eA = nodeE.a;
				int sB = nodeS.b, eB = nodeE.b;
				
				if (sA != eA && sA != eB && sB != eA && sB != eB) {
					int sC = nodeS.c, eC = nodeE.c;
					min = Math.min(min, eC - sC);
					break;
				}
			}
		}
		
		System.out.println(min);
	}
	
	public static void comb(int cnt, int start) {
		if (cnt == 2) {
			int a = snow[0];
			int b = snow[1];
			int c = arr[a] + arr[b];
			nodes.add(new Node(a, b, c));
			return;
		}
		
		for (int i = start; i < N; i++) {
			snow[cnt] = i;
			comb(cnt + 1, i + 1);
		}
	}
}
