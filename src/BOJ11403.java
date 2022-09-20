import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ11403 {
    //문제
    //가중치 없는 방향 그래프 G가 주어졌을 때, 모든 정점 (i, j)에 대해서, i에서 j로 가는 경로가 있는지 없는지 구하는 프로그램을 작성하시오.
    //
    //입력
    //첫째 줄에 정점의 개수 N (1 ≤ N ≤ 100)이 주어진다. 둘째 줄부터 N개 줄에는 그래프의 인접 행렬이 주어진다. i번째 줄의 j번째 숫자가 1인 경우에는 i에서 j로 가는 간선이 존재한다는 뜻이고, 0인 경우는 없다는 뜻이다. i번째 줄의 i번째 숫자는 항상 0이다.
    //
    //출력
    //총 N개의 줄에 걸쳐서 문제의 정답을 인접행렬 형식으로 출력한다. 정점 i에서 j로 가는 경로가 있으면 i번째 줄의 j번째 숫자를 1로, 없으면 0으로 출력해야 한다.

    static ArrayList<ArrayList<Integer>> map;
    static boolean[] visited;
    static Stack<Integer> stack;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new ArrayList<>();
        visited = new boolean[N + 1];
        stack = new Stack<>();

        for (int i = 0; i < N; i++) {
            map.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    map.get(i).add(j);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(dfs(i, j) + " ");
                stack.clear();
                visited = new boolean[N];
            }
            System.out.println();
        }
    }

    public static int dfs(int cur, int target) {
        stack.push(cur);
        visited[cur] = true;

        for (int i = 0; i < map.get(cur).size(); i++) {
            int next = map.get(cur).get(i);
            if (next == target) {
                return 1;
            }
            if (!visited[next]) {
                if (dfs(next, target) == 1) {
                    return 1;
                }
            }
        }
        stack.pop();
        return 0;
    }
}