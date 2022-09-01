import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ15649 {
    //N과 M (1)

    //문제
    //자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
    //1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
    //
    //입력
    //첫째 줄에 자연수 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 8)
    //
    //출력
    //한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력한다. 중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다.
    //수열은 사전 순으로 증가하는 순서로 출력해야 한다.

    static final int MAX = 8;
    static int n;
    static int m;
    static int[] arr;
    static boolean[] visited;
    static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[MAX];
        visited = new boolean[MAX];

        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
            visited[i] = false;
        }
        dfs(0);
    }

    public static void dfs(int cnt) {
        if (cnt == m) {
            for (int i = 0; i < m; i++) {
                System.out.print(stack.get(i) + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            stack.push(arr[i]);
            visited[i] = true;
            dfs(cnt + 1);
            stack.pop();
            visited[i] = false;
        }
    }
}

