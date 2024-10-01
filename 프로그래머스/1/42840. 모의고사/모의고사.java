import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        
        int[] p1 = {1, 2, 3, 4, 5};
        int[] p2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] p3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        
        int[] answer = new int[3];
        
        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == p1[i % 5]) answer[0]++;
            if (answers[i] == p2[i % 8]) answer[1]++;
            if (answers[i] == p3[i % 10]) answer[2]++;
        }
        
        List<Integer> result = new LinkedList<Integer>();
        
        int max = 0;
        max = Math.max(answer[0], Math.max(answer[1], answer[2]));
        
        for (int i = 0; i < 3; i++) {
            if (max == answer[i]) result.add(i + 1);
        }
        
        int[] resultArr = new int[result.size()];
        
        for (int i = 0; i < result.size(); i++) {
            resultArr[i] = result.get(i);
        }

        return resultArr;
    }
}