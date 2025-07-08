import java.util.*;
import java.lang.*;
import java.io.*;

class Main
{
    static int N, H, O;
    static char arr[];
    
	public static void main (String[] args) throws java.lang.Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
	    N = Integer.parseInt(st.nextToken());
	    st = new StringTokenizer(br.readLine());
	    arr = st.nextToken().toCharArray();
	    
	    if (N % 3 != 0) {
	        System.out.print("mix");
	        return;
	    }
	    
	    for (int i = 0; i < arr.length; i++) {
	        if (arr[i] == 'H') H++;
	        else O++;
	        
	        if (O > H || H - O > (N / 3)) {
	            System.out.print("mix");
	            return;
	        }
	    }
	    
	    if (H != 2 * O) {
	        System.out.print("mix");
	    } else {
	        System.out.print("pure");
	    }
	}
}