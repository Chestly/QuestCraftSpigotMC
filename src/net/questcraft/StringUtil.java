package net.questcraft;

import java.util.HashMap;

public class StringUtil {
    public HashMap<String, String> stringToMap(String str) {
        System.out.println("string to map the str is this: " + str);
        if (!str.equalsIgnoreCase("")) {
            HashMap<String, String> map = new HashMap<>();
            String[] items = str.split(", ");
            for (String item : items) {
                String[] parts = item.split("; ");
                String key ;
                String value;
                switch (parts.length) {
                    case (1):
                        key = parts[0].trim();
                        value = "";
                        map.put(key, value);
                        break;
                    case (2):
                        key = parts[0].trim();
                        value = parts[1].trim();
                        map.put(key, value);
                        break;
                }
            }
            return map;
        } else {
            return new HashMap<>();
        }
    }
    public String mapToString(HashMap<String, String> map) {
        if (map != null) {
            String stringedMap = "";
            int i = 0;
            for (String key : map.keySet()) {
                if (i++ == map.keySet().size() - 1) {
                    stringedMap += key + "; " + map.get(key);
                } else {
                    stringedMap += key + "; " + map.get(key) + ", ";
                }
            }
            return stringedMap;
        } else {
            return "";
        }
    }
    public boolean containsValue(HashMap<String, String> map, String value) {
        for (String key : map.keySet()) {
            if (value.equalsIgnoreCase(map.get(key))) {
                return true;
            }
        }
        return false;
    }
    public HashMap<String, String> mapToLowerCase(HashMap<String, String> map) {
        HashMap<String, String> returnMap = new HashMap<>();
        for (String key : map.keySet()) {
            returnMap.put(key.toLowerCase(), map.get(key).toLowerCase());
        }
        return returnMap;
    }
}
