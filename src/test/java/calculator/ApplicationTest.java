package calculator;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    @Test
    void 커스텀_구분자_사용() {
        assertSimpleTest(() -> {
            run("//;\\n1");
            assertThat(output()).contains("결과: 1");
        });
    }

    @Test
    void 예외1_구분자_없음() {
        assertSimpleTest(() ->{
            run("1");
            assertThat(output().contains("1"));
        });
    }

    @Test
    void 예외2_빈문자열() {
        assertSimpleTest(() -> {
            run("");
            assertThat(output().contains("0"));
        });
    }

    @Test
    void 예외3_숫자없는_입력값() {
        assertSimpleTest(() -> {
            assertThatIllegalArgumentException().isThrownBy(() -> run("abc"));
        });
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("-1,2,3"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
