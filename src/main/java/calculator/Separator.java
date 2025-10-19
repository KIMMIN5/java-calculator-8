package calculator;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Separator {
    public static String[] separate(String input) throws IllegalArgumentException{
        String basicDelimiterRegex = "[,|:]";
        final String CUSTOMDELIMITERREGEX = "^//(.)\\\\n(.*)";
        String customDelimiter;
        String[] separatedString;

        Pattern pattern = Pattern.compile(CUSTOMDELIMITERREGEX);
        Matcher matcher = pattern.matcher(input);

        if(matcher.find()) {
            customDelimiter = matcher.group(1);
            if(customDelimiter.length() > 1) {
                throw new IllegalArgumentException("커스텀 구분자는 여러 개를 설정할 수 없습니다.");
            }
            basicDelimiterRegex += "|" + customDelimiter; // 예외#2-2: 기본구분자에 커스텀구분자 추가
            separatedString = matcher.group(2).split(basicDelimiterRegex);
            return separatedString;
        }
        separatedString = input.split(basicDelimiterRegex);
        return separatedString;
    }
}
