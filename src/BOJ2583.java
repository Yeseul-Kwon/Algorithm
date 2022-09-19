import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ2583 {
    //문제
    //눈금의 간격이 1인 M×N(M,N≤100)크기의 모눈종이가 있다.
    //이 모눈종이 위에 눈금에 맞추어 K개의 직사각형을 그릴 때, 이들 K개의 직사각형의 내부를 제외한 나머지 부분이 몇 개의 분리된 영역으로 나누어진다.
    //M, N과 K 그리고 K개의 직사각형의 좌표가 주어질 때, K개의 직사각형 내부를 제외한 나머지 부분이 몇 개의 분리된 영역으로 나누어지는지,
    //그리고 분리된 각 영역의 넓이가 얼마인지를 구하여 이를 출력하는 프로그램을 작성하시오.
    //
    //입력
    //첫째 줄에 M과 N, 그리고 K가 빈칸을 사이에 두고 차례로 주어진다. M, N, K는 모두 100 이하의 자연수이다.
    //둘째 줄부터 K개의 줄에는 한 줄에 하나씩 직사각형의 왼쪽 아래 꼭짓점의 x, y좌표값과 오른쪽 위 꼭짓점의 x, y좌표값이 빈칸을 사이에 두고 차례로 주어진다.
    //모눈종이의 왼쪽 아래 꼭짓점의 좌표는 (0,0)이고, 오른쪽 위 꼭짓점의 좌표는(N,M)이다. 입력되는 K개의 직사각형들이 모눈종이 전체를 채우는 경우는 없다.
    //
    //출력
    //첫째 줄에 분리되어 나누어지는 영역의 개수를 출력한다. 둘째 줄에는 각 영역의 넓이를 오름차순으로 정렬하여 빈칸을 사이에 두고 출력한다.

    static int[][] map;
    static boolean[][] visited;
    static int[] dirX;
    static int[] dirY;
    static int componentSize;
    static ArrayList<Integer> componentList;
    static int M;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        M = Integer.parseInt(st.nextToken());   // Y
        N = Integer.parseInt(st.nextToken());   // X
        int K = Integer.parseInt(st.nextToken());

        map = new int[M + 10][N + 10];
        visited = new boolean[M + 10][N + 10];
        dirX = new int[]{0, -1, 0, 1};
        dirY = new int[]{-1, 0, 1, 0};

        componentList = new ArrayList<>();

        for (int y = 0; y < M; y++) {
            for (int x = 0; x < N; x++) {
                map[y][x] = 1;
            }
        }
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());


            for (int y = y1; y < y2; y++) {
                for (int x = x1; x < x2; x++) {
                    map[y][x] = 0;
                }
            }
        }

        for (int y = 0; y < M; y++) {
            for (int x = 0; x < N; x++) {
                if (map[y][x] == 1 && !visited[y][x]) {
                    componentSize = 0;
                    dfs(y, x);
                    componentList.add(componentSize);
                }
            }
        }
        Collections.sort(componentList);
        System.out.println(componentList.size());
        for (Integer size : componentList) {
            System.out.print(size + " ");
        }
    }

    public static void dfs(int curY, int curX) {
        visited[curY][curX] = true;
        componentSize++;

        for (int move = 0; move < 4; move++) {
            int moveY = curY + dirY[move];
            int moveX = curX + dirX[move];
            if (moveY >= 0 && moveX >= 0 && map[moveY][moveX] == 1 && !visited[moveY][moveX]) {
                dfs(moveY, moveX);
            }
        }
    }
}
