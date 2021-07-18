package github.fushaolei.wpserver.utils;

import java.util.List;

public class TextUtil {

    public static boolean isInList(List<String> dataList, String data) {
        for (String s : dataList) {
            if (s.contains(data) || data.contains(s)) {
                return true;
            }
        }
        return false;
    }
}
