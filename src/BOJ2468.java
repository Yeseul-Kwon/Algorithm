import java.io.*;
import java.util.*;

public class BOJ2468 {
    //문제
    //재난방재청에서는 많은 비가 내리는 장마철에 대비해서 다음과 같은 일을 계획하고 있다. 먼저 어떤 지역의 높이 정보를 파악한다. 그 다음에 그 지역에 많은 비가 내렸을 때 물에 잠기지 않는 안전한 영역이 최대로 몇 개가 만들어 지는 지를 조사하려고 한다. 이때, 문제를 간단하게 하기 위하여, 장마철에 내리는 비의 양에 따라 일정한 높이 이하의 모든 지점은 물에 잠긴다고 가정한다.
    //
    //어떤 지역의 높이 정보는 행과 열의 크기가 각각 N인 2차원 배열 형태로 주어지며 배열의 각 원소는 해당 지점의 높이를 표시하는 자연수이다.
    //물에 잠기지 않는 안전한 영역이라 함은 물에 잠기지 않는 지점들이 위, 아래, 오른쪽 혹은 왼쪽으로 인접해 있으며 그 크기가 최대인 영역을 말한다.
    //이와 같이 장마철에 내리는 비의 양에 따라서 물에 잠기지 않는 안전한 영역의 개수는 다르게 된다.
    //위의 예와 같은 지역에서 내리는 비의 양에 따른 모든 경우를 다 조사해 보면 물에 잠기지 않는 안전한 영역의 개수 중에서 최대인 경우는 5임을 알 수 있다.
    //어떤 지역의 높이 정보가 주어졌을 때, 장마철에 물에 잠기지 않는 안전한 영역의 최대 개수를 계산하는 프로그램을 작성하시오.
    //
    //입력
    //첫째 줄에는 어떤 지역을 나타내는 2차원 배열의 행과 열의 개수를 나타내는 수 N이 입력된다. N은 2 이상 100 이하의 정수이다. 둘째 줄부터 N개의 각 줄에는 2차원 배열의 첫 번째 행부터 N번째 행까지 순서대로 한 행씩 높이 정보가 입력된다. 각 줄에는 각 행의 첫 번째 열부터 N번째 열까지 N개의 높이 정보를 나타내는 자연수가 빈 칸을 사이에 두고 입력된다. 높이는 1이상 100 이하의 정수이다.
    //
    //출력
    //첫째 줄에 장마철에 물에 잠기지 않는 안전한 영역의 최대 개수를 출력한다.

    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[] dirX;
    static int[] dirY;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N + 1][N + 1];
        visited = new boolean[N + 1][N + 1];
        dirX = new int[]{0, -1, 0, 1};
        dirY = new int[]{-1, 0, 1, 0};

        int max = 0;
        int min = 100;
        ArrayList<Integer> inputList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                int input = Integer.parseInt(st.nextToken());
                map[i][j] = input;
                inputList.add(input);
                if (input > max) {
                    max = input;
                }
                if (input < min) {
                    min = input;
                }
            }
        }

        inputList.add(min - 1);

        int maxComponent = 0;
        for (int rainHeight : inputList) {
            visited = new boolean[N + 1][N + 1];
            int component = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] > rainHeight && !visited[i][j]) {
                        dfs(i, j, rainHeight);
                        component++;
                    }
                }
            }
            if (maxComponent < component) {
                maxComponent = component;
            }
        }
        System.out.println(maxComponent);
    }

    static void dfs(int curX, int curY, int h) {
        visited[curX][curY] = true;

        for (int move = 0; move < 4; move++) {
            int moveX = curX - dirX[move];
            int moveY = curY - dirY[move];
            if (moveX >= 0 && moveY >= 0 && map[moveX][moveY] > h && !visited[moveX][moveY]) {
                dfs(moveX, moveY, h);
            }
        }
    }
}
