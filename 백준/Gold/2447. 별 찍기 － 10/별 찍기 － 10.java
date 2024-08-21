import java.util.Scanner;

public class Main {
	
	static boolean[][] star;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int N = sc.nextInt();
		
		star = new boolean[N][N];
		makeStar(0, 0, N);

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (star[i][j])
					sb.append("*");
				else
					sb.append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static void makeStar(int x ,int y, int N) {
		if (N == 1) {
			star[x][y] = true;
			return;
		}
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if(i == 1 && j == 1)
					continue;
				else
					makeStar(x + i * (N / 3), y + j * (N / 3), N / 3);
			}
		}
	}

}
