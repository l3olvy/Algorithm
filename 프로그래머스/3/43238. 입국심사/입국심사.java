import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        long answer = 0;
        long start = 0;
        long end = times[times.length - 1] * (long) n;

        while (start <= end) {
            long sum = 0;
            long mid = (start + end) / 2;
            for (int i = 0; i < times.length; i++) {
                sum += mid / times[i];
            }
            if (sum < n) {
                start = mid + 1;
            } 
            else {
                end = mid - 1;
                answer = mid;
            }
        }
        
        return answer;
    }
}