import java.util.*;
import java.lang.*;
import java.io.*;

class Main
{
    static int N, M, list[], cnt = 0;
    
    public static int findParent(int x) {
        int temp = x;
        while(list[temp] != temp){
            temp = list[temp];
        }
        list[x] = temp;
        return temp;
    }
    
    public static void makeUnion(int a, int b) {
        a = findParent(a);
        b = findParent(b);
        
        if (a < b) {
            list[b] = a;
        } else {
            list[a] = b;
        }
    }
    
    public static void main (String[] args) throws java.lang.Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        list = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = i;
        }

        
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            // 사이클 발생하면 끊음
            findParent(a);
            findParent(b);
            if (list[a] == list[b]) cnt++;
            else makeUnion(a, b);
        }

        HashSet<Integer> hs = new HashSet<>();
        
        for (int i = 1; i <= N; i++) {
            findParent(i);
        }
        
        for (int i = 1; i <= N; i++) {
            hs.add(list[i]);
        }
        
        System.out.print(hs.size() - 1 + cnt);
    }
}