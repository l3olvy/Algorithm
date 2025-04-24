import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int n = progresses.length;
        ArrayList<Integer> list = new ArrayList<Integer>();
        
        int[] arr = new int[n];
        
        for (int i = 0; i < n; i++) {
            if ((100 - progresses[i]) % speeds[i] == 0) {
                arr[i] = (100 - progresses[i]) / speeds[i];
            } else {
                arr[i] = (100 - progresses[i]) / speeds[i] + 1;
            }
        }

        int start = arr[0];
        int sum = 1;
        for (int i = 1; i < n; i++) {
            if (start < arr[i]) {
                list.add(sum);
                sum = 1;
                start = arr[i];
            } else {
                sum++;
            }
        }
        list.add(sum);
        
        int[] answer = new int[list.size()];
        
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }
}