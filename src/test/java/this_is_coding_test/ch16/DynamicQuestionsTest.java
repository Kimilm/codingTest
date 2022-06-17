package this_is_coding_test.ch16;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DynamicQuestionsTest {

    private final DynamicQuestions dq = new DynamicQuestions();

    @Test
    void 금광() {
        int[] result = dq.금광(new String[]{
                "2",
                "3 4",
                "1 3 3 2 2 1 4 1 0 6 4 7",
                "4 4",
                "1 3 1 5 2 2 4 1 5 0 2 3 0 6 1 2"
        });
        int[] answer = {19, 16};

        Assertions.assertThat(result).containsExactly(answer);
    }
}