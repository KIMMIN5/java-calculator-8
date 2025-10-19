package calculator;

import camp.nextstep.edu.missionutils.Console;

public class Calculator {

    private static final String NUMBERREGEX = "^-?\\d+$"; // 모든 정수를 나타내는 정규표현식
    private final int[] numbers;
    private int sum = 0;

    public Calculator(String[] parsedString) {
        this.numbers = this.extract(parsedString);
    }

    private int[] extract(String[] parsedString) {
        int[] extractedNumbers = new int[parsedString.length];
        for(int i=0; i<parsedString.length; i++) {
            if (parsedString[i].matches(NUMBERREGEX)) {
                int number = Integer.parseInt(parsedString[i]);
                if (number < 0) {
                    throw new IllegalArgumentException("예외#3-1: 음수는 입력할 수 없습니다.");
                }
                extractedNumbers[i] = number;
            }

            if(!parsedString[i].matches(NUMBERREGEX)) {
                throw new IllegalArgumentException("예외#3-2: 숫자가 아닌 문자는 입력할 수 없습니다.");
            }
        }
        return extractedNumbers;
    }

    public int summate() {
        for(int n: numbers) {
            sum += n;
        }
        return sum;
    }
}
