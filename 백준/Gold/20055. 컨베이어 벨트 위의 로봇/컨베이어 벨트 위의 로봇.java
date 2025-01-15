import java.util.*;
import java.lang.*;
import java.io.*;

class Main
{
    static int N, K, result, zero, up, down, robotNum;
    static boolean isZero[];
    
    static class Belt {
        int A, robot;
        
        public Belt(int A, int robot) {
            this.A = A;
            this.robot = robot;
        }
    }
    
    static class Robot {
        int n, index;
        
        public Robot(int n, int index) {
            this.n = n;
            this.index = index;
        }
    }
    
	public static void main (String[] args) throws java.lang.Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		Belt[] belt = new Belt[2 * N + 1];
		isZero = new boolean[2 * N + 1];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 1; i <= 2 * N; i++) {
		    int a = Integer.parseInt(st.nextToken());
		    belt[i] = new Belt(a, 0);
		}
		
		result = 0;
		zero = 0;
		up = 1;
		down = N;
		robotNum = 1;
		
		ArrayDeque<Robot> robot = new ArrayDeque<Robot>();
		
		while (zero < K) {
		    // 단계 카운팅
		    result++;
		    
		    // 1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전
		    up = (up == 1) ? 2 * N : up - 1;
		    down = (down == 1) ? 2 * N : down - 1;

		    int tempRobotNum = 0;
		    
		    // 벨트가 회전했을 때 내리는 위치의 인덱스에 로봇이 있었다면
		    if (belt[down].robot != 0) {
		        belt[down].robot = 0;
		    }
		    
		    // 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동, 이동할 수 없다면 가만히
		    int size = robot.size();
		    for (int i = 0; i < size; i++) {
		        Robot r = robot.removeFirst();
		        
		        // 벨트 회전 시 내렸던 로봇이라면 제거
	            if (r.index == down) continue;
		        
		        // 벨트 회전 방향으로 한 칸 이동
		        int nextIndex = (r.index == 2 * N) ? 1 : r.index + 1;
		           
		        // 다음 칸으로 이동 가능하다면
	            if (belt[nextIndex].A > 0 && belt[nextIndex].robot == 0) {
		            belt[nextIndex].A--;
		            if (belt[nextIndex].A == 0 && !isZero[nextIndex]) {
		                zero++;
		                isZero[nextIndex] = true;
		            }
		            belt[r.index].robot--;
		            if (nextIndex == down) continue;
		    
		            belt[nextIndex].robot++;
		            r.index = nextIndex;
		        }
		        robot.addLast(r);
		    }
		    
		    // 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇 올리기
		    if (belt[up].A > 0) {
		        belt[up].A--;
		        if (belt[up].A == 0 && !isZero[up]) {
		            zero++;
		            isZero[up] = true;
		        }
		        belt[up].robot++;
		        robot.addLast(new Robot(++robotNum, up));
		        
		    }
		}
		
		System.out.print(result);
	
	}
}