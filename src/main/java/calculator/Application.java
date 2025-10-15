package calculator;

import camp.nextstep.edu.missionutils.Console;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {

    public void Calculator() {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String userInput = Console.readLine();
        String[] separateString = separateInput(userInput);
        int[] numbers = extractNumbers(separateString);
        int sum = summateNumbers(numbers);

        System.out.println("결과 : " + sum);
    }

    private String[] separateInput(String inputString) {
        String basicDelimiterRegex = ",|:";
        String customDelimiterRegex = "^//(.)\\\\n(.*)";

        Pattern pattern = Pattern.compile(customDelimiterRegex);
        Matcher matcher = pattern.matcher(inputString);
        if(matcher.find()) {
            String customDelimiter = matcher.group(1);
            basicDelimiterRegex += "|" + customDelimiter; // 기본구분자에 커스텀구분자 추가
            return matcher.group(2).split(basicDelimiterRegex);
        }

        else { // 예외처리 #1 구분자가 없을 시
            return inputString.split(basicDelimiterRegex);
        }
    }

    private int[] extractNumbers(String[] extractString) throws IllegalArgumentException {
        String numberRegex = "^-?\\d+$"; // 모든 정수를 나타내는 정규표현식
        int[] extractNumbers = new int[extractString.length];
        if(extractString.length == 1 && extractString[0].isEmpty()) { // 예외처리 #2 추출 할 숫자가 없을 시(빈문자열)
            return new int[0];
        }
        else {
            int number = 0;
            int sum = 0;

            for(int i=0; i<extractString.length; i++) {
                if (extractString[i].matches(numberRegex)) {
                    number = Integer.parseInt(extractString[i]);
                    if(number < 0) {
                        throw new IllegalArgumentException("음수는 입력할 수 없습니다.");
                    }
                    extractNumbers[i] = number;
                }
                else if(extractNumbers.length == 1 && extractNumbers[i] == 0) {
                    throw new IllegalArgumentException("추출 할 양수가 없습니다.");
                }
            }
            return extractNumbers;
        }
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