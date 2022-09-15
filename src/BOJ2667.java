import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ2667 {
    //문제
    //정사각형 모양의 지도가 있다.
    //1은 집이 있는 곳을, 0은 집이 없는 곳을 나타낸다. 철수는 이 지도를 가지고 연결된 집의 모임인 단지를 정의하고, 단지에 번호를 붙이려 한다.
    //여기서 연결되었다는 것은 어떤 집이 좌우, 혹은 아래위로 다른 집이 있는 경우를 말한다. 대각선상에 집이 있는 경우는 연결된 것이 아니다.
    //지도를 입력하여 단지수를 출력하고, 각 단지에 속하는 집의 수를 오름차순으로 정렬하여 출력하는 프로그램을 작성하시오.
    //
    //입력
    //첫 번째 줄에는 지도의 크기 N(정사각형이므로 가로와 세로의 크기는 같으며 5≤N≤25)이 입력되고, 그 다음 N줄에는 각각 N개의 자료(0혹은 1)가 입력된다.
    //
    //출력
    //첫 번째 줄에는 총 단지수를 출력하시오. 그리고 각 단지내 집의 수를 오름차순으로 정렬하여 한 줄에 하나씩 출력하시오.

    static int N;
    static int[][] map;
    static int[] dirX;
    static int[] dirY;
    static boolean[][] visited;
    static int componentSize;
    static ArrayList<Integer> componentSizeList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N + 1][N + 1];
        dirX = new int[] {0, -1, 0, 1};
        dirY = new int[] {-1, 0, 1, 0};
        visited = new boolean[N + 1][N + 1];
        componentSizeList = new ArrayList<>();

        for (int y = 0; y < N; y++) {
            String[] tmp = br.readLine().split("");
            for (int x = 0; x < N; x++) {
                map[y][x] = Integer.parseInt(tmp[x]);
            }
        }

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (map[y][x] == 1 && !visited[y][x]) {
                    componentSize = 1;
                    dfs(y, x);
                    componentSizeList.add(componentSize);
                }
            }
        }

        Collections.sort(componentSizeList);
        System.out.println(componentSizeList.size());
        for (int i = 0; i < componentSizeList.size(); i++) {
            System.out.println(componentSizeList.get(i));
        }
    }

    static void dfs(int curY, int curX) {
        visited[curY][curX] = true;

        for (int move = 0; move < 4; move++) {
            int moveY = curY - dirY[move];
            int moveX = curX - dirX[move];

            if ((moveY >= 0) && (moveX >= 0) && (map[moveY][moveX] == 1) && !visited[moveY][moveX]) {
                dfs(moveY, moveX);
                componentSize++;
            }
        }
    }
}