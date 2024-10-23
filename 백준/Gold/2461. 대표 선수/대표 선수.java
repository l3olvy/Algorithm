import java.util.*;
import java.io.*;
public class Main {

	static int N, M, map[][], index[], result = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ArrayList<int[]> list = new ArrayList<int[]>();
		index = new int[N];
		for (int i = 0; i < N; i ++) {
			list.add(new int[M]);
		}
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				list.get(r)[c] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(list.get(r));
		}
		
		
		while (true) {
			int max = 0;
			int min = Integer.MAX_VALUE;
			int minIdx = 0;
			
			for (int r = 0; r < N; r++) {
				int king = list.get(r)[index[r]];
				if (king < min) {
					min = king;
					minIdx = r;
				}
				if (king > max) {
					max = king;
				}
				
			}
			result = Math.min(result, max - min);
			if (++index[minIdx] >= M) break;
		}
		
		System.out.println(result);
	}
}
