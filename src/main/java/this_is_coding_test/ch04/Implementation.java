package this_is_coding_test.ch04;

public class Implementation {

    /**
     * 난이도 하
     * 시간제한 1초
     * 메모리제한 128MB
     * 1 <= N <= 100
     * 1 <= 이동 횟수 <= 100
     * x y 좌표를 공백으로 구분하여 출력
     */
    public String 상하좌우(int N, String travels) {
        int x = 1;
        int y = 1;
        String[] travelArray = travels.split(" ");

        for (String travel : travelArray) {
            if (travel.equals("L") && y > 1) {
                --y;
            } else if (travel.equals("R") && y < N) {
                ++y;
            } else if (travel.equals("U") && x > 1) {
                --x;
            } else if (travel.equals("D") && x < N) {
                ++x;
            }
        }

        return x + " " + y;
    }

    /**
     * 0 <= N <= 23
     * 00시 00분 00초 ~ N시 59분 59초 모든 시각중 3이 하나라도 포함되는 모든 경우의 수 출력
     */
    public int 시각(int N) {
        int hourHasThree = 60 * 60;
        int minuteHasThree = 15 * 60;
        int secondHasThree = 45 * 15;

        int answer = 0;

        for (int i = 0; i <= N; i++) {
            if (String.valueOf(i).contains("3"))
                answer += hourHasThree;
            else {
                answer += minuteHasThree + secondHasThree;
            }
        }

        return answer;
    }

}
