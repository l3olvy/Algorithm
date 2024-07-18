import java.util.*;

public class Main {

  public static boolean[] visited;
  public static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();

  public static int count = 0;

  // dfs 함수 정의
  public static void dfs(int x) {
    visited[x] = true;
    
    for (int i = 0; i < graph.get(x).size(); i++) {
      int y = graph.get(x).get(i);
      if (!visited[y]) {
        dfs(y);
        count++;
      }
    }
  }
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    
    int comNum = sc.nextInt();
    int n = sc.nextInt();
    visited = new boolean[comNum + 1];

    // 그래프 초기화
    for (int i = 0; i < comNum + 1; i++) {
      graph.add(new ArrayList<Integer>());
    }

    // 그래프 입력값 저장
    for (int i = 0; i < n; i++) {
      int a = sc.nextInt();
      int b = sc.nextInt();

      graph.get(a).add(b);
      graph.get(b).add(a);
    }

    dfs(1);
    System.out.print(count);
  }
}