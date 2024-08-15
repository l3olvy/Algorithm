import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	// (x, y)좌표를 묶어 나타내기 위한 Node 클래스
	static class Node {
		int x;
		int y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 입력값 받기
		final int C = Integer.parseInt(st.nextToken());
		final int R = Integer.parseInt(st.nextToken());
		final int REC = 2 * (C + R);
		st = new StringTokenizer(br.readLine());
		final int N = Integer.parseInt(st.nextToken());
		Deque<Node> q = new LinkedList<>();
		
		// 상점의 위치 좌표와 동근이의 위치 좌표 q에 삽입
		for (int i = 0; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (a == 1) 
				q.add(new Node(0, b));
			else if (a == 2)
				q.add(new Node(R, b));
			else if (a == 3)
				q.add(new Node(b, 0));
			else
				q.add(new Node(b, C));
			
		}

		// 동근이의 위치 (x, y)
		Node n = q.pollLast();
		int dongX = n.x;
		int dongY = n.y;
		
		int result = 0;
		
		while (!q.isEmpty()) {
			n = q.poll();
			int shopX = n.x;
			int shopY = n.y;
			int temp = 0;
			// 같은 행이면 x값의 차이 or y값의 차이 더하기
			if (shopX == dongX && (dongX == 0 || dongX == R)) {
				result += Math.abs(dongY - shopY);
			} else if (shopY == dongY && (dongY == 0 || dongY == C)) {
				result += Math.abs(dongX - shopX);
			}
			else {
				// 반대편에 있다면 
				if (Math.abs(shopX - dongX) == R) {
					temp = dongY + shopY + R;
					result += Math.min(temp, REC - temp);
				}
				else if (Math.abs(shopY - dongY) == C) {
					temp = dongX + shopX + C;
					result += Math.min(temp, REC - temp);
				}
				// 대각선에 있다면
				else {
					temp = Math.abs(dongX - shopX) + Math.abs(dongY - shopY);
					result += Math.min(temp, REC - temp);
				}
			}
		}
		System.out.println(result);
	}
}