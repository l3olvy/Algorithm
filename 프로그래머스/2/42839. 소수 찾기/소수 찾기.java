import java.util.*;

class Solution {
    // static int order[];
    static char[] nums;
    static boolean visited[];
    static int num;
    static char[] order;
    static HashSet<Integer> set = new HashSet<Integer>();
    static int max = 0;
    
    public int solution(String numbers) {
        
        nums = numbers.toCharArray();
        visited = new boolean[numbers.length()];
        
        for (int i = 1; i <= nums.length; i++) {
            order = new char[i];
            num = i;
            perm(0);
        }
        
        boolean arr[] = new boolean[max + 1];
        
        Arrays.fill(arr, true);
        arr[0] = false;
        arr[1] = false;
        
        for (int i = 2; i <= Math.sqrt(max); i++) {
            if (arr[i] == true) {
                int j = 2;
                while (i * j <= max) {
                    arr[i * j] = false;
                    j++;
                }
            }
        }
        
        int answer = 0;
        for (int n : set) if (arr[n]) answer++;

        return answer;
    }
    
    public void perm(int cnt) {
        String s = "";
        if (cnt == num) {
            for (int i = 0; i < num; i++) s += order[i];
            int n = Integer.parseInt(s);
            max = Math.max(max, n);
            set.add(n);
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                order[cnt] = nums[i];
                perm(cnt + 1);
                visited[i] = false;
            }
        }
    }
} 