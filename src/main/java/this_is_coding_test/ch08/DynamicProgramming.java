package this_is_coding_test.ch08;

import java.util.Arrays;

public class DynamicProgramming {

    /**
     * 난이도 중하
     * 제한) 시간: 1초, 메모리: 128MB
     * 1 <= x <= 30_000
     */
    public int 일로_만들기(int x) {
        int[] d = new int[x + 1];
        d[1] = 1;

        return makeOne(d, x) - 1;
    }

    public int makeOne(int[] d, int x) {
        if (d[x] != 0) {
            return d[x];
        }

        int[] array = new int[4];

        if (x % 5 == 0) {
            d[x / 5] = makeOne(d, x / 5);
            array[0] = d[x / 5];
        }

        if (x % 3 == 0) {
            d[x / 3] = makeOne(d, x / 3);
            array[1] = d[x / 3];
        }

        if (x % 2 == 0) {
            d[x / 2] = makeOne(d, x / 2);
            array[2] = d[x / 2];
        }

        d[x - 1] = makeOne(d, x - 1);
        array[3] = d[x - 1];

        int min = Arrays.stream(array).filter(value -> value != 0).min().orElseThrow();
        return min + 1;
    }

    /**
     * Bottom_Up 방식의 풀이
     */
    public int 일로_만들기_2(int x) {
        int[] d = new int[30_001];

        for (int i = 2; i < x + 1; i++) {
            // 현재의 수에서 1을 빼는 경우
            d[i] = d[i - 1] + 1;
            // 현재의 수가 2로 나누어 떨어지는 경우
            if (i % 2 == 0) {
                d[i] = Integer.min(d[i], d[i / 2] + 1);
            }
            // 현재의 수가 3으로 나누어 떨어지는 경우
            if (i % 3 == 0) {
                d[i] = Integer.min(d[i], d[i / 3] + 1);
            }
            // 현재의 수가 5로 나누어 떨어지는 경우
            if (i % 5 == 0) {
                d[i] = Integer.min(d[i], d[i / 5] + 1);
            }
        }

        return d[x];
    }

    /**
     * 난이도 중
     * 제한) 시간: 1초, 메모리: 128MB
     * 3 <= N <= 100
     * 0 <= K <= 1_000
     */
    public int 개미_전사(int n, String k) {
        int[] foods = Arrays.stream(k.split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] d = new int[n];

        d[0] = foods[0];
        d[1] = foods[1];
        d[2] = foods[0] + foods[2];

        for (int i = 3; i < n; ++i) {
            d[i] = foods[i] + Integer.max(d[i - 2], d[i - 3]);
        }

        return Integer.max(d[n - 1], d[n - 2]);
    }

    /**
     * 해당 문제의 점화식
     * 현재 위치 a(i)에 대해서
     * a(i-1)위치를 털면 현재 위치를 털 수 없음
     * a(i-2)위치를 털면 현재 위치를 털 수 있음
     * i번째 식량창고에 있는 식량의 양이 k(i)라고 했을 때
     * a(i) = max(a(i-1), a(i-2) + k(i))
     * (i-3)번째 이하의 식량창고에 대한 최적의 해는 고려할 필요가 없다.
     * (i-1)과 (i-2)를 구하는 과정에서 이미 계산되었기 때문
     */
    public int 개미_전사_2(int n, String k) {
        int[] array = Arrays.stream(k.split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] d = new int[100];

        // Bottom-up
        d[0] = array[0];
        d[1] = Integer.max(array[0], array[1]);

        for (int i = 2; i < n; ++i) {
            d[i] = Integer.max(d[i - 1], d[i - 2] + array[i]);
        }

        return d[n - 1];
    }

    /**
     * 난이도 중하
     * 제한) 시간: 1초, 메모리: 128MB
     * 1 <= N <= 1_000
     */
    public int 바닥_공사(int n) {
        int[] d = new int[n + 1];
        d[1] = 1;
        d[2] = 3;

        for (int i = 3; i < n + 1; ++i) {
            d[i] = d[i - 1] + d[i - 2] + 1;
        }

        return d[n];
    }

    /**
     * 위의 풀이는 점화식이 틀렸다.
     * (n-2) 길이가 이미 채워져 았는 경우
     * 덮개를 채우는 방법은 2가지 경우 -> 이 2가지는 서로 다른 방법
     * --_____
     * -/ / /
     * /_/_/
     * --_____                     _____
     * -/____/         +          /    /
     * /____/                    /____/
     * --_____
     * -/    /
     * /____/
     * ----------------------------------
     * --_____
     * -/ / /
     * /_/_/
     * --_____                     _____
     * -/____/         +          /____/
     * /____/                    /____/
     * --_____
     * -/    /
     * /____/
     * a(i) = a(i - 1) + a(i - 2) + a(i - 2)
     */
    public int 바닥_공사_2(int n) {
        int[] d = new int[1_001];
        d[1] = 1;
        d[2] = 3;

        for (int i = 3; i < n + 1; ++i) {
            d[i] = (d[i - 1] + 2 * d[i - 2]) % 796_976;
        }

        return d[n];
    }
}
