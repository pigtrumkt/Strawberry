package Common;

import java.util.regex.Pattern;

public class StringUtils {
    private static final Pattern LTRIM = Pattern.compile("^\\s+");
    private static final Pattern RTRIM = Pattern.compile("\\s+$");
    private static final String EMPTY_STRING = "";
 
    public static String ltrim(String str) {
        return LTRIM.matcher(str).replaceAll(EMPTY_STRING);
    }
 
    public static String rtrim(String str) {
        return RTRIM.matcher(str).replaceAll(EMPTY_STRING);
    }
}
