import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ6603 {
    //로또

    //문제
    //독일 로또는 {1, 2, ..., 49}에서 수 6개를 고른다.
    //로또 번호를 선택하는데 사용되는 가장 유명한 전략은 49가지 수 중 k(k>6)개의 수를 골라 집합 S를 만든 다음 그 수만 가지고 번호를 선택하는 것이다.
    //예를 들어, k=8, S={1,2,3,5,8,13,21,34}인 경우 이 집합 S에서 수를 고를 수 있는 경우의 수는 총 28가지이다. ([1,2,3,5,8,13], [1,2,3,5,8,21], [1,2,3,5,8,34], [1,2,3,5,13,21], ..., [3,5,8,13,21,34])
    //집합 S와 k가 주어졌을 때, 수를 고르는 모든 방법을 구하는 프로그램을 작성하시오.
    //
    //입력
    //입력은 여러 개의 테스트 케이스로 이루어져 있다. 각 테스트 케이스는 한 줄로 이루어져 있다. 첫 번째 수는 k (6 < k < 13)이고, 다음 k개 수는 집합 S에 포함되는 수이다. S의 원소는 오름차순으로 주어진다.
    //입력의 마지막 줄에는 0이 하나 주어진다.
    //
    //출력
    //각 테스트 케이스마다 수를 고르는 모든 방법을 출력한다. 이때, 사전 순으로 출력한다.
    //각 테스트 케이스 사이에는 빈 줄을 하나 출력한다.
/* 8 1 2 3 5 8 13 21 34
8 1 2 3 5 8 13 21 34
0 */
    static int MAX = 49;
    static int k = 6;
    static ArrayList<Integer> S;
    static boolean[] visited;
    static boolean[] haveNum;
    static Stack<Integer> stack;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String input = br.readLine();
            if (input.equals("0")) {
                break;
            }

            S = new ArrayList<>();
            visited = new boolean[MAX];
            haveNum = new boolean[MAX];
            stack = new Stack<>();

            StringTokenizer st = new StringTokenizer(input, " ");

            int n = Integer.parseInt(st.nextToken());

            for (int i = 0; i < n; i++) {
                S.add(Integer.parseInt(st.nextToken()));
            }
            Collections.sort(S);
            dfs(0, 0);
            System.out.println();
        }
    }

    public static void dfs(int cnt, int num) {
        if (cnt == k) {
            for (int i = 0; i < k; i++) {
                System.out.print(stack.get(i) + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 0; i < S.size(); i++) {
            if (visited[i] || S.get(i) <= num) continue;
            stack.push(S.get(i));
            visited[i] = true;
            dfs(cnt + 1, S.get(i));
            stack.pop();
            visited[i] = false;
        }
    }
}
