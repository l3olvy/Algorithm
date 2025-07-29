import java.util.*;
import java.lang.*;
import java.io.*;

class Main
{
    static int N, K, R, count = 0, visitMark = 1;
    // 상, 하, 좌, 우
    static int dx[] = {-1, 1, 0, 0};
    static int dy[] = {0, 0, -1, 1};
    static int[][] cowNum, roadBit, visitedTime;
    
	public static void main (String[] args) throws java.lang.Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
	    cowNum = new int[N + 1][N + 1];
	    roadBit = new int[N + 1][N + 1];
	    visitedTime = new int[N + 1][N + 1];
		
		for (int i = 0; i < R; i++) {
		    st = new StringTokenizer(br.readLine());
		    int r1 = Integer.parseInt(st.nextToken());
		    int c1 = Integer.parseInt(st.nextToken());
		    int r2 = Integer.parseInt(st.nextToken());
		    int c2 = Integer.parseInt(st.nextToken());
		    setBit(r1, c1, r2, c2);
		}
		
		int[][] cows = new int[K + 1][2];
		
		for (int i = 1; i <= K; i++) {
		    st = new StringTokenizer(br.readLine());
		    int r = Integer.parseInt(st.nextToken());
		    int c = Integer.parseInt(st.nextToken());
		    
		    cows[i][0] = r;
		    cows[i][1] = c;
		    cowNum[r][c] = i;
		}
		
		for (int i = 1; i < K; i++) {
		    int startX = cows[i][0], startY = cows[i][1];
		    int unreachable = K - i;
		    visitMark++;
		    
		    ArrayDeque<int[]> dq = new ArrayDeque<>();
		    dq.add(new int[]{startX, startY});
		    visitedTime[startX][startY] = visitMark;
		    
		    while (!dq.isEmpty()) {
		        int[] now = dq.poll();
		        int x = now[0], y = now[1];
		        
		        if (cowNum[x][y] > i) {
		            unreachable--;
		        }
		        
		        for (int d = 0; d < 4; d++) {
    		        int nx = x + dx[d], ny = y + dy[d];
    		        if (nx <= 0 || ny <= 0 || nx > N || ny > N) continue;
    		        if (visitedTime[nx][ny] == visitMark) continue;
    		        if ((roadBit[x][y] & (1 << d)) != 0) continue;
    		        visitedTime[nx][ny] = visitMark;
    		        dq.add(new int[]{nx, ny});
    		    }
		    }
		    
		    count += unreachable;
		}
		System.out.print(count);

	}
	
	public static void setBit(int r1, int c1, int r2, int c2) {
	    // 1 기준 2의 위치
	    if (r1 == r2) {
	        // 우
	        if (c1 < c2) {
	            roadBit[r1][c1] |= 1 << 3;
	            roadBit[r2][c2] |= 1 << 2;
	        }
	        // 좌
	        else {
	            roadBit[r1][c1] |= 1 << 2;
	            roadBit[r2][c2] |= 1 << 3;
	        }
	    } else {
	        // 하
	        if (r1 < r2) {
	            roadBit[r1][c1] |= 1 << 1;
	            roadBit[r2][c2] |= 1 << 0;
	        }
	        // 상
	        else {
	            roadBit[r1][c1] |= 1 << 0;
	            roadBit[r2][c2] |= 1 << 1;
	        }
	    }
	}
}
