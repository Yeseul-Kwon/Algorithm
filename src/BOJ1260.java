import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1260 {
    //DFS와 BFS

    //문제
    //그래프를 DFS로 탐색한 결과와 BFS로 탐색한 결과를 출력하는 프로그램을 작성하시오. 단, 방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문하고, 더 이상 방문할 수 있는 점이 없는 경우 종료한다. 정점 번호는 1번부터 N번까지이다.
    //
    //입력
    //첫째 줄에 정점의 개수 N(1 ≤ N ≤ 1,000), 간선의 개수 M(1 ≤ M ≤ 10,000), 탐색을 시작할 정점의 번호 V가 주어진다. 다음 M개의 줄에는 간선이 연결하는 두 정점의 번호가 주어진다. 어떤 두 정점 사이에 여러 개의 간선이 있을 수 있다. 입력으로 주어지는 간선은 양방향이다.
    //
    //출력
    //첫째 줄에 DFS를 수행한 결과를, 그 다음 줄에는 BFS를 수행한 결과를 출력한다. V부터 방문된 점을 순서대로 출력하면 된다.

    static boolean[] check;
    static ArrayList<ArrayList<Integer>> A = new ArrayList<>(); // LinkedList
    static int n;   // 노드의 개수
    static int m;   // 간선의 개수
    static int start;   // 루트노드

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());

        check = new boolean[n + 1];

        for (int i = 0; i <= n; i++) {
            A.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            // 무방향 그래프이기 때문에 양쪽 노드에 모두 값을 넣어준다.
            A.get(x).add(y);
            A.get(y).add(x);
        }

        // 자식이 여러개라면 노드 번호가 작은 것 먼저 방문하기 위해 오름차순 정렬
        for (int i = 0; i <= n; i++) {
            Collections.sort(A.get(i));
        }

        dfs(start);
        System.out.println();
        check = new boolean[n + 1];
        bfs(start);
    }

    // 재귀함수로 구현한 DFS
    public static void dfs(int x) {
        check[x] = true;
        System.out.print(x + " ");

        for (int i = 0; i < A.get(x).size(); i++) {
            int y = A.get(x).get(i);
            if (!check[y]) {
                dfs(y);
            }
        }
    }

    // Queue로 구현한 BFS
    public static void bfs(int v) {
        Queue<Integer> queue = new LinkedList<>();
        check[v] = true;
        queue.offer(v);

        while(!queue.isEmpty()){
            int x = queue.poll();
            System.out.print(x + " ");
            for(int i=0; i<A.get(x).size(); i++){
                int y = A.get(x).get(i);
                if(!check[y]){
                    check[y] = true;
                    queue.offer(y);
                }
            }
        }
    }
}
