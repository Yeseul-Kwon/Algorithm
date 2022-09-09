import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ11724 {
    //문제
    //방향 없는 그래프가 주어졌을 때, 연결 요소 (Connected Component)의 개수를 구하는 프로그램을 작성하시오.
    //
    //입력
    //첫째 줄에 정점의 개수 N과 간선의 개수 M이 주어진다. (1 ≤ N ≤ 1,000, 0 ≤ M ≤ N×(N-1)/2) 둘째 줄부터 M개의 줄에 간선의 양 끝점 u와 v가 주어진다. (1 ≤ u, v ≤ N, u ≠ v) 같은 간선은 한 번만 주어진다.
    //
    //출력
    //첫째 줄에 연결 요소의 개수를 출력한다.

    static ArrayList<ArrayList<Integer>> graph;
    static Stack<Integer> stack;
    static boolean[] visited;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();
        stack = new Stack<>();
        visited = new boolean[n + 1];

        int m = Integer.parseInt(st.nextToken());
        int component = 0;

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        for (int i = 1; i <= n; i++) {
            if (visited[i]) continue;
            dfs(i);
            component++;
        }
        System.out.println(component);
    }

    public static void dfs(int cur) {
        visited[cur] = true;

        for (int i = 0; i < graph.get(cur).size(); i++) {
            int x = graph.get(cur).get(i);
            if (!visited[x]) {
                dfs(x);
            }
        }
    }
}
