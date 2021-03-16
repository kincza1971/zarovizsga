package hu.nive.ujratervezes.zarovizsga.digitscounter;

import java.util.regex.Pattern;

public class DigitsCounter {

    public static final String NUMBER_EXP = "[0-9]";

    public int getCountOfDigits(String s) {
        if (s == null || s.isBlank()) {
            return 0;
        }
        StringBuilder sb = new StringBuilder(10);
        Pattern pattern = Pattern.compile(NUMBER_EXP);
        for (int i = 0; i < s.length(); i++) {
            String sub = s.substring(i, i + 1);
            if (pattern.matcher(sub).matches() & (sb.indexOf(sub) < 0)) {
                sb.append(sub);
            }
        }
        return sb.length();
    }
}
