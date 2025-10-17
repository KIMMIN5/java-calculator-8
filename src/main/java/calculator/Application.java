package calculator;

import camp.nextstep.edu.missionutils.Console;

public class Application {

    public void Calculator() {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = Console.readLine();
        if(input.isEmpty()) {
            System.out.println("결과 : " + 0);
        }

        String[] parsedString = Separator.separate(input);

        Calculator calculator = new Calculator(parsedString);
        int sum = calculator.summate();

        System.out.println("결과 : " + sum);
    }

    public static void main(String[] args) {
        // TODO: 프로그램 구현
        Application application = new Application();
        application.Calculator();
    }
}