import java.util.*;
import java.lang.*;
import java.io.*;

class Main
{
    static int H, W, map[][], sum;

	public static void main (String[] args) throws java.lang.Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		map = new int[H][W];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < W; i++) {
		    int h = Integer.parseInt(st.nextToken());
		    sum += h;
		    for (int j = H - h; j < H; j++) {
		        map[j][i] = 1;
		    }
		}
		
		for (int i = 0; i < H; i++) {
		    int y = 0;
		    while (y < W) {
		        if (map[i][y] == 0) {
		            map[i][y] = 1;
		            sum++;
		            y++;
		        }
		        else break;
		    }
		    
		    if (y >= W - 1) continue;
		    else {
		        y = W - 1;
		        while (y >= 0) {
    		        if (map[i][y] == 0) {
    		            map[i][y] = 1;
    		            sum++;
    		            y--;
    		        }
    		        else break;
		        }   
		    }
		}
		
		System.out.print(H * W - sum);

	}
}
