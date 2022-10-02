import java.util.*;
import java.io.*;

public class BOJ7562 {
    //문제
    //체스판 위에 한 나이트가 놓여져 있다. 
    //나이트가 한 번에 이동할 수 있는 칸은 아래 그림에 나와있다.
    //나이트가 이동하려고 하는 칸이 주어진다. 
    //나이트는 몇 번 움직이면 이 칸으로 이동할 수 있을까?
    //
    //입력
    //입력의 첫째 줄에는 테스트 케이스의 개수가 주어진다.
    //각 테스트 케이스는 세 줄로 이루어져 있다. 
    //첫째 줄에는 체스판의 한 변의 길이 l(4 ≤ l ≤ 300)이 주어진다. 
    //체스판의 크기는 l × l이다. 체스판의 각 칸은 두 수의 쌍 {0, ..., l-1} × {0, ..., l-1}로 나타낼 수 있다. 
    //둘째 줄과 셋째 줄에는 나이트가 현재 있는 칸, 나이트가 이동하려고 하는 칸이 주어진다.
    //
    //출력
    //각 테스트 케이스마다 나이트가 최소 몇 번만에 이동할 수 있는지 출력한다.

    static class Pair {
        int first, second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    static int stoi(String s) {
        return Integer.parseInt(s);
    }

    static boolean[][] visited;
    static int l;
    static int targetX;
    static int targetY;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int k = stoi(br.readLine());

        while (k-- > 0) {
            l = stoi(br.readLine());

            visited = new boolean[l + 1][l + 1];
            result = 0;

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int startY = stoi(st.nextToken());
            int startX = stoi(st.nextToken());

            st = new StringTokenizer(br.readLine(), " ");
            targetY = stoi(st.nextToken());
            targetX = stoi(st.nextToken());

            bfs(startY, startX);
            System.out.println(result);
        }
    }

    public static void bfs(int y, int x) {
        if (y == targetY && x == targetX) return;
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(y, x));
        visited[y][x] = true;

        int[] dirX = new int[]{-1, 1, -2, -2, -1, 1, 2, 2};
        int[] dirY = new int[]{-2, -2, -1, 1, 2, 2, -1, 1};

        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            for (int j = 0; j < queueSize; j++) {
                Pair pair = queue.poll();

                for (int i = 0; i < 8; i++) {
                    int nextY = dirY[i] + pair.first;
                    int nextX = dirX[i] + pair.second;

                    if ((nextY >= 0 && nextX >= 0) && (nextY < l && nextX < l)) {
                        if (nextY == targetY && nextX == targetX) {
                            result++;
                            return;
                        }
                        if (!visited[nextY][nextX]) {
                            queue.add(new Pair(nextY, nextX));
                            visited[nextY][nextX] = true;
                        }
                    }
                }
            }
            result++;
        }
    }
}