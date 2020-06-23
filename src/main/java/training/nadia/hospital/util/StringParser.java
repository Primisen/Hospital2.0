package training.nadia.hospital.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringParser {

    //регулярки или split()??

    private static final String SURNAME_REGEX = "^\\w+";
    private static final String NAME_REGEX = "\\w+";

    private StringParser() {
    }

    public static String deriveSurname(String parseString) {

        return parse(parseString, SURNAME_REGEX);
    }

    public static String deriveName(String parseString) {

        return parse(parseString, NAME_REGEX);
    }

    private static String parse(String parseString, String regex) {

        String outputString = new String();

        //Pattern.UNICODE_CHARACTER_CLASS -- для поиска русских(и не только) слов
        Pattern pattern = Pattern.compile(regex, Pattern.UNICODE_CHARACTER_CLASS);

        Matcher matcher = pattern.matcher(parseString);


        while (matcher.find()) {
            outputString = matcher.group();
        }

        return outputString;
    }

//    public static void main(String[] args) {
//        String s = StringParser.deriveSurname("Будько Степан");
//        String n = StringParser.deriveName("Будько Степан");
//
//        System.out.println(s);
//        System.out.println(n);
//    }
}
