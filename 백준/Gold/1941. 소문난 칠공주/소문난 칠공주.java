import java.util.*;
import java.lang.*;
import java.io.*;

class Main
{
    static int N = 5, map[][], answer = 0;
    static boolean visited[][];
    static Set<String> hs = new HashSet<>();
    // 상, 하, 좌, 우
    static int dx[] = {-1, 1, 0, 0};
    static int dy[] = {0, 0, -1, 1};
    
	public static void main (String[] args) throws java.lang.Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
		    st = new StringTokenizer(br.readLine());
		    String str = st.nextToken();
		    for (int j = 0; j < N; j++) {
		        if (str.charAt(j) == 'Y') map[i][j] = 1;
		        else map[i][j] = 0;
		    }
		}
		
		for (int i = 0; i < N; i++) {
		    for (int j = 0; j < N; j++) {
		      //  System.out.println("===" + i + ", " + j + "===");
		        visited = new boolean[N][N];
		        visited[i][j] = true;
		        dfs(i, j, 1, map[i][j]);
		    }
		}
		
		System.out.print(hs.size());
	}
	
	public static void dfs(int x, int y, int cnt, int sum) {
	    if (sum >= 4) return;
        
	    if (cnt == 7) {
	        StringBuilder sb = new StringBuilder();
	        for (int i = 0; i < N; i++) {
	            for (int j = 0; j < N; j++) {
	                if(visited[i][j])
	                    sb.append(i).append(j).append(",");
	            }
	        }
	        hs.add(sb.toString());
	        answer++;
	        return;
	    }
	    
	    for (int i = 0; i < N; i++) {
	        for (int j = 0; j < N; j++) {
	            if (visited[i][j]) continue;
	            if (isNeihbor(i, j)) {
	                visited[i][j] = true;
	                dfs(i, j, cnt + 1, sum + map[i][j]);
	                visited[i][j] = false;
	            }
	        }
	    }
	}
	
	public static boolean isNeihbor(int x, int y) {
	    for (int d = 0; d < 4; d++) {
	        int nx = x + dx[d];
	        int ny = y + dy[d];
	        if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
	        if (visited[nx][ny]) {
	            return true;
	        }
	    }
	    return false;
	}
}
