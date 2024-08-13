/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// double b;
// char g;
// String var;
// long AB;
// a = sc.nextInt();                           // int 변수 1개 입력받는 예제
// b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
// g = sc.nextByte();                          // char 변수 1개 입력받는 예제
// var = sc.next();                            // 문자열 1개 입력받는 예제
// AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;                            
// double b = 1.0;               
// char g = 'b';
// String var = "ABCDEFG";
// long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
	static boolean[] isChecked = new boolean[9];
	static int[] p = new int[9];
	static int[] ku = new int[9];
	static int[] in = new int[9];
	static int winCnt = 0;
	static int loseCnt = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		
		final int T = Integer.parseInt(st.nextToken());
		
		int[] cards = new int[19];
		
		// 테스트 케이스 돌리기
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			cards = new int[19];
			
			isChecked = new boolean[9];
			ku = new int[9];
			in = new int[9];
			p = new int[9];
			winCnt = 0;
			loseCnt = 0;
			
			// 인영이 카드 구하기
			for (int j = 0; j < 9; j++) {
				int num = Integer.parseInt(st.nextToken());
				ku[j] = num;
				cards[num] = 1;
			}
			
			int idx = 0;
			for (int j = 1; j < 19; j++) {
				if (cards[j] == 0)
					in[idx++] = j;
			}
			
			perm(0);
			sb.append("#" + (i + 1) + " " + winCnt + " " + loseCnt + "\n");
			
		}
		System.out.println(sb);
	}
	
	// 인영이 카드 순열 구하기 (순서 O)
	public static void perm(int cnt) {
		if (cnt == 9) {
			int kuSum = 0;
			int inSum = 0;
			for (int i = 0; i < 9; i++) {
				if(ku[i] > p[i]) {
					kuSum += (ku[i] + p[i]);
				}
				else if (ku[i] < p[i]) {
					inSum += (ku[i] + p[i]);
				}
			}
			if (kuSum > inSum) winCnt++;
			else if (inSum > kuSum) loseCnt++;
		}
		
		for (int i = 0; i < 9; i++) {
			if (isChecked[i]) continue;
			
			isChecked[i] = true;
			p[cnt] = in[i];
			perm(cnt + 1);
			isChecked[i] = false;
		}
	}

}