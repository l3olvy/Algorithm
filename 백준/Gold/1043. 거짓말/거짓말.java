import java.util.*;
import java.lang.*;
import java.io.*;

class Main
{
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
    static ArrayList<ArrayList<Integer>> party = new ArrayList<ArrayList<Integer>>();
    static int N, M, P, answer;
    static boolean visited[];
    static ArrayDeque<Integer> dq = new ArrayDeque<Integer>();
    
	public static void main (String[] args) throws java.lang.Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        answer = M;
        
        P = Integer.parseInt(st.nextToken());
        visited = new boolean[N + 1];
        
        if (P == 0) {
            System.out.print(M);
            return;
        }
        
        for (int p = 0; p < P; p++) {
            int person = Integer.parseInt(st.nextToken());
            dq.offer(person);
        }
        
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<Integer>());
        }
        
        for (int i = 0; i <= M; i++) {
            party.add(new ArrayList<Integer>());
        }
        
        for (int m = 1; m <= M; m++) {
            st = new StringTokenizer(br.readLine());
            int partyNum = Integer.parseInt(st.nextToken());
            int[] array = new int[partyNum];
            for (int p = 0; p < partyNum; p++) {
                int temp = Integer.parseInt(st.nextToken());
                array[p] = temp;
                party.get(m).add(temp);
            }
            
            if (partyNum > 1) {
                for (int p = 1; p < partyNum; p++) {
                    int a = array[p - 1];
                    int b = array[p];
                    
                    if (!graph.get(a).contains(b))
                        graph.get(a).add(b);
                    if (!graph.get(b).contains(a))
                        graph.get(b).add(a);
                }
            }
        }
        
        
        while (!dq.isEmpty()) {
            int now = dq.poll();
            visited[now] = true;
            
            for (int j = 0; j < graph.get(now).size(); j++) {
                int next = graph.get(now).get(j);
                if (!visited[next]) {
                    dq.offer(next);
                }
            }
        }
        
        
        for (int i = 1; i <= M; i++) {
            for (int j = 0; j < party.get(i).size(); j++) {
                int num = party.get(i).get(j);
                if (visited[num]) {
                    answer--;
                    break;
                }
            }
        }
        System.out.print(answer); 
	}
}