package calculator;

import camp.nextstep.edu.missionutils.test.NsTest;
import jdk.jfr.Description;
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
            assertThat(output()).contains("결과 : 1");
        });
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("-1,2,3"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 예외_2_1_구분자_없음() {
        assertSimpleTest(() ->{
            run("123");
            assertThat(output()).contains("결과 : 123");
        });
    }

    @Test
    void 예외_2_2_복합_구분자() {
        assertSimpleTest(() -> {
            run("//;\\n1,2:3;4");
            assertThat(output()).contains("결과 : 10");
        });
    }

    @Test
    void 예외_3_1_음수() {
        assertSimpleTest(() -> {
            assertThatThrownBy(() -> runException("-1, 2, 3"))
                    .isInstanceOf(IllegalArgumentException.class);
        });
    }

    @Test
    void 예외_3_2_숫자없는_입력값() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("abc"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Description("README 내부로직 예외처리 테스트 코드")
    @Test
    void 예외_3_3_문자숫자_섞임() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1, a, 2"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 예외_3_4_구분자_여러개() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//;;;\n1;;;2;;;3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
