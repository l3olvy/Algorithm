import java.util.*;
import java.lang.*;
import java.io.*;

// N개의 타이어 -> i번째 타이어 점수 S(i)
// 양 팀 / 각 타이어마다 끌어올 사람 수 배정
// 각 타이어에 대해 더 많은 사람을 보낸 팀이 S(i)만큼 점수 get
// 사람 수 같으면 양팀 모두 점수 X

class Main
{
    static int N, M, dp[][], sum;
    static Node arr[];
    
    public static class Node {
        int score, num;
        
        public Node(int score, int num) {
            this.score = score;
            this.num = num;
        }
    }
    
	public static void main (String[] args) throws java.lang.Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new Node[N + 1];
		dp = new int[N + 1][M + 1];
		sum = 0;
		for (int i = 1; i <= N; i++) {
		    st = new StringTokenizer(br.readLine());
		    int a = Integer.parseInt(st.nextToken());
		    int b = Integer.parseInt(st.nextToken());
		    arr[i] = new Node(a, b);
		    sum += a;
		}

		for (int i = 1; i <= N; i++) {
		    for (int j = 1; j <= M; j++) {
		        
		        if (j >= arr[i].num) {
		            dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - arr[i].num] + arr[i].score);
		        }
		        
		        int winNum = arr[i].num + 1;
		        int winScore = arr[i].score * 2;
		        
		        if (j >= winNum) {
		            dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - winNum] + winScore);
		        }
		    }
		}

		if (dp[N][M] == sum) {
		    System.out.print("D");
		} else if (dp[N][M] > sum) {
		    System.out.print("W");
		} else {
		    System.out.print("L");
		}
	}
}