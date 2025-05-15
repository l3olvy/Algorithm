import java.util.*;

class Solution {
    
    static int map[][];
    static boolean isPuddles[][];
    static boolean visited[][];
    
    public int solution(int m, int n, int[][] puddles) {
        
        map = new int[m + 1][n + 1];
        isPuddles = new boolean[m + 1][n + 1];
        visited = new boolean[m + 1][n + 1];
        
        for (int i = 0; i < puddles.length; i++) {
            int r = puddles[i][0];
            int c = puddles[i][1];
            
            isPuddles[r][c] = true; // 물 웅덩이
        }
        
        map[1][1] = 1;
        dp(m, n);
          
        return map[m][n] % 1000000007;
    }
    
    public static int dp(int x, int y) {
        if (x <= 0 || y <= 0) return 0;
        if (isPuddles[x][y]) return 0;
        if (map[x][y] != 0) return map[x][y] % 1000000007;
        map[x][y] = dp(x - 1, y) + dp(x, y - 1);
        return map[x][y] % 1000000007;
    }
}