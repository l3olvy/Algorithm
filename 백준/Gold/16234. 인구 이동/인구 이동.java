import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


class Node {
	private int x;
	private int y;
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}	
}

public class Main {

	public static int[][] map;
	public static boolean [][] visited;
	// 상, 우, 하, 좌
	public static int[] dx = {-1, 0, 1, 0};
	public static int[] dy = {0, 1, 0, -1};
	public static int n, l, r;
	
	public static int answer = 0;
	
	public static List<Node> list; 
	
	public static int bfs(int x, int y) {
		Queue<Node> q = new LinkedList<>();
		list = new ArrayList<>();
		
		list.add(new Node(x, y));
		q.offer(new Node(x, y));
		visited[x][y] = true;
		int sum = map[x][y];
		
		while(!(q.isEmpty())) {
			Node node = q.poll();
			x = node.getX();
			y = node.getY();
			
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(isIn(nx, ny)) {
					if(Math.abs(map[nx][ny] - map[x][y]) <= r
							&& Math.abs(map[nx][ny] - map[x][y]) >= l) {
						q.offer(new Node(nx,ny));
						list.add(new Node(nx, ny));
						sum += map[nx][ny];
						visited[nx][ny] = true;
					}
				}
			}
		}
		
		return sum;
	}
	
	public static void changeMap(int sum) {
		int avg = sum / list.size();
		
		for(Node node : list) {
			int x = node.getX();
			int y = node.getY();
			map[x][y] = avg;
		}
	}
	
	public static boolean isIn(int x, int y) {	
		if(x >= 0 && y >= 0 && x < n && y < n && !visited[x][y])
			return true;
		else 
			return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 변수에 입력값 저장
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		
		map = new int[n][n];
		
		
		// 2차원 배열 map에 입력값 저장
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(true) {
			boolean isBreak = true;
			visited = new boolean[n][n];
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(!visited[i][j]) {
						int sum = bfs(i, j);
						if (list.size() > 1) {
							changeMap(sum);
							isBreak = false;
						}
					}
				}
			}
			if (isBreak) break;
			answer++;
		}
		
		System.out.println(answer);
		
	}

}
