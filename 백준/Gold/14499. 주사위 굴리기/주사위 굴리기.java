import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] dice = {0, 0, 0, 0, 0, 0};
	
	public static void turn(int dir) {
		int a, b, c, d, e, f;
		
		a = dice[0];
		b = dice[1];
		c = dice[2];
		d = dice[3];
		e = dice[4];
		f = dice[5];
		
		// 동쪽
		if(dir == 1) {
			dice[0] = d;
			dice[2] = a;
			dice[3] = f;
			dice[5] = c;
		}
		// 서쪽
		else if(dir == 2) {
			dice[0] = c;
			dice[2] = f;
			dice[3] = a;
			dice[5] = d;
		}
		// 북쪽
		else if(dir == 3) {
			dice[0] = e;
			dice[1] = a;
			dice[4] = f;
			dice[5] = b;
		}
		// 남쪽
		else {
			dice[0] = b;
			dice[1] = f;
			dice[4] = a;
			dice[5] = e;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		// 동, 서, 북, 남
		int[] dx = {0, 0, 0, -1, 1};
		int[] dy = {0, 1, -1, 0, 0};
		
		final int N = Integer.parseInt(st.nextToken());
		final int M = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		final int K = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		
		// map 입력 받기
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for (int k = 0; k < K; k++) {
			// 명령 입력 받기
			int order = Integer.parseInt(st.nextToken());
			
			// 다음 위치
			int nx = x + dx[order];
			int ny = y + dy[order];
			
			// 다음 좌표가 바깥이라면 명령 무시
			if(nx < 0 || nx >= N || ny < 0 || ny >= M)
				continue;
			
			x  = nx;
			y = ny;
			
			// 언제나 주사위 윗면은 dice[0], 밑면은 dice[5]가 되도록 방향에 맞게 주사위 값 변경
			turn(order);
			
			// map 숫자가 0 이면 주사위 숫자 넣어주고, 0이 아니면 주사위에 값 복사 후 0으로 초기화
			if (map[x][y] == 0)
				map[x][y] = dice[5];
			else {
				dice[5] = map[x][y];
				map[x][y] = 0;
			}
			
			// 매 명령마다 주사위 윗면에 적힌 숫자 저장
			sb.append(dice[0] + "\n");
		}
		
		System.out.println(sb);
	}
}