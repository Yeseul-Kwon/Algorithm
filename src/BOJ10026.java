import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ10026 {
    //문제
    //적록색약은 빨간색과 초록색의 차이를 거의 느끼지 못한다. 따라서, 적록색약인 사람이 보는 그림은 아닌 사람이 보는 그림과는 좀 다를 수 있다.
    //크기가 N×N인 그리드의 각 칸에 R(빨강), G(초록), B(파랑) 중 하나를 색칠한 그림이 있다. 그림은 몇 개의 구역으로 나뉘어져 있는데, 구역은 같은 색으로 이루어져 있다.
    //또, 같은 색상이 상하좌우로 인접해 있는 경우에 두 글자는 같은 구역에 속한다. (색상의 차이를 거의 느끼지 못하는 경우도 같은 색상이라 한다)
    //예를 들어, 그림이 아래와 같은 경우에
    //RRRBB
    //GGBBB
    //BBBRR
    //BBRRR
    //RRRRR
    //적록색약이 아닌 사람이 봤을 때 구역의 수는 총 4개이다. (빨강 2, 파랑 1, 초록 1) 하지만, 적록색약인 사람은 구역을 3개 볼 수 있다. (빨강-초록 2, 파랑 1)
    //그림이 입력으로 주어졌을 때, 적록색약인 사람이 봤을 때와 아닌 사람이 봤을 때 구역의 수를 구하는 프로그램을 작성하시오.
    //
    //입력
    //첫째 줄에 N이 주어진다. (1 ≤ N ≤ 100)
    //둘째 줄부터 N개 줄에는 그림이 주어진다.
    //
    //출력
    //적록색약이 아닌 사람이 봤을 때의 구역의 개수와 적록색약인 사람이 봤을 때의 구역의 수를 공백으로 구분해 출력한다.

    static char[][] map;
    static boolean[][] visited;
    static int[] dirX;
    static int[] dirY;
    static int componentSize;
    static ArrayList<Integer> componentList;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new char[N + 1][N + 1];
        visited = new boolean[N + 1][N + 1];
        dirX = new int[]{0, -1, 0, 1};
        dirY = new int[]{-1, 0, 1, 0};
        componentList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            char[] temp = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                map[i][j] = temp[j];
            }
        }

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (!visited[y][x]) {
                    dfs1(y, x);
                    componentSize++;
                }
            }
        }

        componentList.add(componentSize);
        visited = new boolean[N + 1][N + 1];
        componentSize = 0;
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (!visited[y][x]) {
                    dfs2(y, x);
                    componentSize++;
                }
            }
        }
        componentList.add(componentSize);

        for (Integer size : componentList) {
            System.out.print(size + " ");
        }
    }

    public static void dfs1(int curY, int curX) {
        visited[curY][curX] = true;

        for (int move = 0; move < 4; move++) {
            int moveY = curY + dirY[move];
            int moveX = curX + dirX[move];
            if (moveY >= 0 && moveX >= 0 && map[moveY][moveX] == map[curY][curX] && !visited[moveY][moveX]) {
                dfs1(moveY, moveX);
            }
        }
    }

    public static void dfs2(int curY, int curX) {
        visited[curY][curX] = true;

        for (int move = 0; move < 4; move++) {
            int moveY = curY + dirY[move];
            int moveX = curX + dirX[move];
            if (map[curY][curX] == 'R' || map[curY][curX] == 'G') {
                if (moveY >= 0 && moveX >= 0 && (map[moveY][moveX] == 'R' || map[moveY][moveX] == 'G') && !visited[moveY][moveX]) {
                    dfs2(moveY, moveX);
                }
            } else if (map[curY][curX] == 'B') {
                if (moveY >= 0 && moveX >= 0 && map[curY][curX] == map[moveY][moveX] && !visited[moveY][moveX]) {
                    dfs2(moveY, moveX);
                }
            }
        }
    }
}

