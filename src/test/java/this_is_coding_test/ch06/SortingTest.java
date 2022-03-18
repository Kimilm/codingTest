package this_is_coding_test.ch06;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class SortingTest {
    private final Sorting s = new Sorting();

    @Test
    void 위에서_아래로_test() {
        int[] result = s.위에서_아래로(3, "15 27 12");
        int[] answer = new int[]{27, 15, 12};

        Assertions.assertThat(result).containsExactly(answer);
    }

    @Test
    void 성적이_낮은_순서로_학생_출력하기_test() {
        String result = s.성적이_낮은_순서로_학생_출력하기(2, new String[]{"홍길동 95", "이순신 77"});
        String answer = "이순신 홍길동";

        Assertions.assertThat(result).isEqualTo(answer);
    }
}