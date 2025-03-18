import java.util.*;
import java.io.*;

class Main {
    static int N, M, K;
    static long[] arr, segmentTree;

    public static void main(String[] args) throws java.lang.Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new long[N];
        segmentTree = new long[N * 4];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Long.parseLong(st.nextToken());
        }

        init(0, N - 1, 1);

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) {
                long diff = c - arr[b - 1]; 
                arr[b - 1] = c;  
                update(0, N - 1, 1, b - 1, diff);
            } else {
                sb.append(sum(0, N - 1, 1, b - 1, (int)c - 1)).append("\n");
            }
        }

        System.out.println(sb);
    }

    public static long init(int start, int end, int node) {
        if (start == end) {
            return segmentTree[node] = arr[start];
        }

        int mid = (start + end) / 2;
        return segmentTree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
    }

    public static void update(int start, int end, int node, int index, long diff) {
        if (index < start || index > end) return;

        segmentTree[node] += diff;
        if (start == end) return;

        int mid = (start + end) / 2;
        update(start, mid, node * 2, index, diff);
        update(mid + 1, end, node * 2 + 1, index, diff);
    }

    public static long sum(int start, int end, int node, int left, int right) {
        if (left > end || right < start) return 0;
        if (left <= start && end <= right) return segmentTree[node];

        int mid = (start + end) / 2;
        return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
    }
}
