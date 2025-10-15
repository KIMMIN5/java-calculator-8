package calculator;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {

    public void startCalculator() {
        System.out.print("덧셈할 문자열을 입력하세요: ");
        String userInput = Console.readLine();
        separateInput(userInput);
    }

    public String separateInput(String inputString) {
        String basicDelimiterRegex = ",|:";
        String customDelimiterRegex = "//(.)\\\\n(.*)";
        String extractString = "";

        Pattern pattern = Pattern.compile(customDelimiterRegex);
        Matcher matcher = pattern.matcher(inputString);
        if(matcher.find()) {
            String customDelimiter = matcher.group(1);
            basicDelimiterRegex += "|" + customDelimiter; // 기본구분자에 커스텀구분자 추가
            extractString = Arrays.toString(matcher.group(2).split(basicDelimiterRegex));
        }

        else { // 예외처리 #1 구분자가 없을 시
            extractString = Arrays.toString(inputString.split(basicDelimiterRegex));
        }
        System.out.println("extractString: " + extractString);
        return extractString;
    }

    public static void main(String[] args) {
        // TODO: 프로그램 구현
        Application application = new Application();
        application.startCalculator();
    }
}
