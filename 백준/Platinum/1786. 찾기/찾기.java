import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String T = br.readLine();
		String P = br.readLine();
		
		int pArr[] = new int[P.length()];
		int count = 0;
		
		// 부분 일치 테이블 배열 만들기
		pArr[0] = 0;
		int j = 0;
		
		for (int i = 1; i < P.length();) {
			if (P.charAt(i) == P.charAt(j)) {
				j++;
				pArr[i] = j;
				i++;
			} else {
				if (j > 0) {
					j = pArr[j - 1];
				}else {
					pArr[i] = j;
					i++;
				}
			}
		}
		
		// KMP 알고리즘
		j = 0;
		for (int i = 0; i < T.length();) {
			
			if (T.charAt(i) == P.charAt(j)) {
				j++;
				i++;
			} else {
				if (j > 0) {
					j = pArr[j - 1];
				} else {
					i++;
				}
			}
			if (j == P.length()) {
				count++;
				sb.append((i - P.length() + 1) + " ");
				j = pArr[j - 1];
				continue;
			}
		}
		sb.insert(0, count + "\n");
		System.out.println(sb);
	}
}