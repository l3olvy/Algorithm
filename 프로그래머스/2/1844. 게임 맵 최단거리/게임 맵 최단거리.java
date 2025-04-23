import java.util.*;

class Solution {
    static int n, m;
    static boolean visited[][];
    // 상, 하, 좌, 우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public static class Node {
        int x, y;
        
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public int solution(int[][] maps) {
        n = maps.length;
        m = maps[0].length;
        visited = new boolean[n][m];
        
        bfs(0, 0, maps);
        
        if (!visited[n - 1][m - 1]) return -1;
        else return maps[n - 1][m - 1];
    }
    
    public static void bfs(int x, int y, int[][] maps) {
        visited[x][y] = true;
        Queue<Node> q = new LinkedList<Node>();
        q.offer(new Node(x, y));
        
        while (!q.isEmpty()) {
            Node node = q.poll();
            
            
            if (node.x == n - 1 && node.y == m - 1) return;
            
            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
                
                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if (maps[nx][ny] == 0) continue;
                if (visited[nx][ny]) continue;
                visited[nx][ny] = true;
                maps[nx][ny] += maps[node.x][node.y];
                q.offer(new Node(nx, ny));
            }
        }
    }
}