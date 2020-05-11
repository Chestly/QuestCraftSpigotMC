package net.questcraft;

import java.util.HashMap;

public class ErrorMSG {
    private static final HashMap<Integer, String> messages = new HashMap<Integer, String>() {{
        put(2, "");
    }};
     public static String getErrorMSG(Integer code) {
        return messages.get(code);
    }
}
