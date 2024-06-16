package org.elka.helper;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PrecisePatternMatcher {

    public static boolean cleanMatch(
            @NotNull String pattern,
            @NotNull String inputString
    ) {
        pattern = pattern.replaceAll("[\\s\\p{P}]", "");
        pattern = Pattern.quote(pattern);
        String regex = ".*" + pattern + ".*";
        Pattern compiledPattern = Pattern.compile(regex);
        Matcher matcher = compiledPattern.matcher(inputString);
        return matcher.matches();
    }

    public static boolean match(
            @NotNull String pattern,
            @NotNull String inputString
    ) {
        pattern = Pattern.quote(pattern);
        String regex = ".*" + pattern + ".*";
        Pattern compiledPattern = Pattern.compile(regex);
        Matcher matcher = compiledPattern.matcher(inputString);
        return matcher.matches();
    }

    public static boolean containsSpecialChars(String inputString) {
        return !inputString.matches("[a-zA-Z0-9]+");
    }
}
