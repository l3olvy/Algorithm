import java.util.*;
import java.lang.*;
import java.io.*;

class Main
{
    static int N, K, map[][], list[];
	public static void main (String[] args) throws java.lang.Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N + 1][K + 1];
        list = new int[N + 1];
        
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            list[i] = Integer.parseInt(st.nextToken());
            map[i][0] = 1;
        }
        
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                if (j >= list[i]) {
                    map[i][j] = map[i - 1][j] + map[i][j - list[i]];
                }
                else {
                    map[i][j] = map[i - 1][j];
                }
            }
        }
        
        // for (int i = 0; i <= N; i++) {
        //     System.out.println(Arrays.toString(map[i]));
        // }
        
        System.out.println(map[N][K]);

	}
}
