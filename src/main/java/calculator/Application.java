package calculator;

import camp.nextstep.edu.missionutils.Console;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {

    public void Calculator() {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String userInput = Console.readLine();
        if(userInput.isEmpty()) {
            System.out.println("결과 : " + 0);
        }
        String[] separateString = separateInput(userInput);
        int[] numbers = extractNumbers(separateString);
        int sum = summateNumbers(numbers);

        System.out.println("결과 : " + sum);
    }

    private String[] separateInput(String inputString) throws IllegalArgumentException {
        String basicDelimiterRegex = ",|:";
        String customDelimiterRegex = "^//(.)\\\\n(.*)";

        Pattern pattern = Pattern.compile(customDelimiterRegex);
        Matcher matcher = pattern.matcher(inputString);
        if(matcher.find()) {
            String customDelimiter = matcher.group(1);
            if(customDelimiter.length() > 1) {
                throw new IllegalArgumentException("커스텀 구분자는 여러 개를 설정할 수 없습니다.");
            }
            basicDelimiterRegex += "|" + customDelimiter; // 예외#2-2: 기본구분자에 커스텀구분자 추가
            return matcher.group(2).split(basicDelimiterRegex);
        }

        return inputString.split(basicDelimiterRegex);
    }

    private int[] extractNumbers(String[] extractString) throws IllegalArgumentException {
        String numberRegex = "^-?\\d+$"; // 모든 정수를 나타내는 정규표현식
        int[] extractNumbers = new int[extractString.length];

        int number = 0;
        int sum = 0;

        for(int i=0; i<extractString.length; i++) {
            if (extractString[i].matches(numberRegex)) {
                number = Integer.parseInt(extractString[i]);
                if (number < 0) {
                    throw new IllegalArgumentException("예외#3-1: 음수는 입력할 수 없습니다.");
                }
                extractNumbers[i] = number;
            }

            if(!extractString[i].matches(numberRegex)) {
                throw new IllegalArgumentException("예외#3-2: 숫자가 아닌 문자는 입력할 수 없습니다.");
            }
        }
        return extractNumbers;
    }


    private int summateNumbers(int[] numbers) {
        int sum = 0;
        for(int n: numbers) {
            sum += n;
        }
        return sum;
    }

    public static void main(String[] args) {
        // TODO: 프로그램 구현
        Application application = new Application();
        application.Calculator();
    }
}