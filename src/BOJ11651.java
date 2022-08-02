import java.io.*;
import java.util.*;

public class BOJ11651 {
    //좌표 정렬하기2

    //문제
    //2차원 평면 위의 점 N개가 주어진다. 좌표를 y좌표가 증가하는 순으로, y좌표가 같으면 x좌표가 증가하는 순서로 정렬한 다음 출력하는 프로그램을 작성하시오.
    //
    //입력
    //첫째 줄에 점의 개수 N (1 ≤ N ≤ 100,000)이 주어진다. 둘째 줄부터 N개의 줄에는 i번점의 위치 xi와 yi가 주어진다. (-100,000 ≤ xi, yi ≤ 100,000) 좌표는 항상 정수이고, 위치가 같은 두 점은 없다.
    //
    //출력
    //첫째 줄부터 N개의 줄에 점을 정렬한 결과를 출력한다.

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

        int[][] array = new int[n][2];
        for (int i = 0; i < n; i++) {
            String[] tmp = bf.readLine().split(" ");
            array[i][0] = Integer.parseInt(tmp[0]);
            array[i][1] = Integer.parseInt(tmp[1]);
        }

        Arrays.sort(array, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] == o2[1]) {
                    return o1[0] - o2[0];
                } else {
                    return o1[1] - o2[1];
                }
            }
        });

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < n; i++) {
            bw.write(array[i][0] + " " + array[i][1] + "\n");
        }
        bw.flush();
        bw.close();
    }
}
