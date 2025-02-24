import java.util.*;
import java.lang.*;
import java.io.*;

class Main
{
    static String strA, strB, strC;
    static long A, B, C;
	public static void main (String[] args) throws java.lang.Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
        strA = st.nextToken();
        strB = st.nextToken();
        strC = st.nextToken();
        
        if (strA.equals("?")) {
            B = (long) Math.pow(Integer.parseInt(strB), 2);
            C = (long) Math.pow(Integer.parseInt(strC), 2);
            A = C - B;
            solve(A);
        }
        else if (strB.equals("?")) {
            A = (long) Math.pow(Integer.parseInt(strA), 2);
            C = (long) Math.pow(Integer.parseInt(strC), 2);
            B = C - A;
            solve(B);
        }
        else {
            A = (long) Math.pow(Integer.parseInt(strA), 2);
            B = (long) Math.pow(Integer.parseInt(strB), 2);
            C = A + B;
            solve(C);
        }
	}
	
	public static void solve(Long X) {
	    int front = 1;
	    long X2 = X;
	    for (long i = (long)Math.sqrt(X); i > 1; i--) {
	        if ((X2 % (long) Math.pow(i, 2)) == 0) {
	            front *= i;
	            X2 /= (long) Math.pow(i, 2);
	            if (X2 == 1) {
	                System.out.println(front);
	                return;
	            }
	        }
	    }
	    
	    if (front == 1) {
	        System.out.println("\\sqrt{" + X2 + "}");
	    } else {
	        System.out.println(front + " \\cdot \\sqrt{" + X2 + "}");
	    }
	}

}
