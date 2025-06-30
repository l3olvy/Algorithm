import java.util.*;

class Solution {
    static int parent[];
    public int solution(int n, int[][] computers) {
        HashSet<Integer> hs = new HashSet<Integer>();
        
        parent = new int[n + 1];
        
        for (int i = 0; i < n + 1; i++) {
            parent[i] = i;
        }
        
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (computers[r][c] == 1 && r != c) {
                    union(r + 1, c + 1);
                }
            } 
        }
        
        for (int i = 1; i <= n; i++) {
            findParent(i);
        }

        for (int i = 1; i < n + 1; i++) {
            hs.add(parent[i]);
        }
        return hs.size();
    }
    
    public static int findParent(int x) {
        if (parent[x] == x) return x;
        return parent[x] = findParent(parent[x]);
    }
    
    public static void union(int a, int b) {
        a = findParent(a);
        b = findParent(b);
        
        if (a < b) parent[b] = a;
        else parent[a] = b;
    }
}