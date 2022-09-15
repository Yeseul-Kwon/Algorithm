import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ1743 {
    //문제
    //코레스코 콘도미니엄 8층은 학생들이 3끼의 식사를 해결하는 공간이다. 그러나 몇몇 비양심적인 학생들의 만행으로 음식물이 통로 중간 중간에 떨어져 있다.
    //이러한 음식물들은 근처에 있는 것끼리 뭉치게 돼서 큰 음식물 쓰레기가 된다.
    //이 문제를 출제한 선생님은 개인적으로 이러한 음식물을 실내화에 묻히는 것을 정말 진정으로 싫어한다.
    //참고로 우리가 구해야 할 답은 이 문제를 낸 조교를 맞추는 것이 아니다.
    //통로에 떨어진 음식물을 피해가기란 쉬운 일이 아니다. 따라서 선생님은 떨어진 음식물 중에 제일 큰 음식물만은 피해 가려고 한다.
    //선생님을 도와 제일 큰 음식물의 크기를 구해서 “10ra"를 외치지 않게 도와주자.
    //
    //입력
    //첫째 줄에 통로의 세로 길이 N(1 ≤ N ≤ 100)과 가로 길이 M(1 ≤ M ≤ 100) 그리고 음식물 쓰레기의 개수 K(1 ≤ K ≤ N×M)이 주어진다.
    //그리고 다음 K개의 줄에 음식물이 떨어진 좌표 (r, c)가 주어진다.
    //좌표 (r, c)의 r은 위에서부터, c는 왼쪽에서부터가 기준이다. 입력으로 주어지는 좌표는 중복되지 않는다.
    //
    //출력
    //첫째 줄에 음식물 중 가장 큰 음식물의 크기를 출력하라.

    static int N;
    static int M;

    static int[][] map;
    static int[] dirX;
    static int[] dirY;
    static boolean[][] visited;
    static int componentSize;
    static ArrayList<Integer> componentList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        map = new int[N + 10][M + 10];
        visited = new boolean[N + 10][M + 10];
        dirX = new int[]{0, -1, 0, 1};
        dirY = new int[]{-1, 0, 1, 0};
        componentList = new ArrayList<>();

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            map[y][x] = 1;
        }

        for (int y = 1; y <= N; y++) {
            for (int x = 1; x <= M; x++) {
                if (map[y][x] == 1 && !visited[y][x]) {
                    componentSize = 1;
                    dfs(y, x);
                    componentList.add(componentSize);
                }
            }
        }
        System.out.println(Collections.max(componentList));
    }

    static void dfs(int curY, int curX) {
        visited[curY][curX] = true;

        for (int move = 0; move < 4; move++) {
            int moveY = curY - dirY[move];
            int moveX = curX - dirX[move];
            if ((moveY > 0) && (moveX > 0) && (map[moveY][moveX] == 1) && !visited[moveY][moveX]) {
                dfs(moveY, moveX);
                componentSize++;
            }
        }
    }
}
