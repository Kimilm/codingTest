package this_is_coding_test.ch07;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class SearchTest {

    private final Search search = new Search();

    @Test
    void 부품_찾기_test() {
        String result = search.부품_찾기(5, "8 3 7 9 2", 3, "5 7 9");
        String answer = "no yes yes";

        Assertions.assertThat(result).isEqualTo(answer);
    }

    @Test
    void 부품_찾기_2_test() {
        String result = search.부품_찾기_2(5, "8 3 7 9 2", 3, "5 7 9");
        String answer = "no yes yes";

        Assertions.assertThat(result).isEqualTo(answer);
    }
}