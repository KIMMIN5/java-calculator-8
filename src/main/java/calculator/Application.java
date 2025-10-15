package calculator;

import camp.nextstep.edu.missionutils.Console;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {

    public void Calculator() {
        System.out.print("덧셈할 문자열을 입력하세요: ");
        String userInput = Console.readLine();
        String[] separateString = separateInput(userInput);
        int[] numbers = extractNumbers(separateString);
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
        String numberRegex = "[0-9]+";
        int[] extractNumbers = new int[extractString.length];
        if(extractString.length == 0) { // 예외처리 #2 추출 할 숫자가 없을 시(빈문자열)
            return new int[0];
        }
        else {
            int number = 0;
            int sum = 0;

            for(int i=0; i<extractString.length; i++) {
                if (extractString[i].matches(numberRegex)) {
                    number = Integer.parseInt(extractString[i]);
                    extractNumbers[i] = number;
                    System.out.print(number);
                }
                else {
                    throw new IllegalArgumentException("예외처리 #3: 추출 할 양수가 없습니다.");
                }
            }
            return extractNumbers;
        }
    }

    public static void main(String[] args) {
        // TODO: 프로그램 구현
        Application application = new Application();
        application.Calculator();
    }
}