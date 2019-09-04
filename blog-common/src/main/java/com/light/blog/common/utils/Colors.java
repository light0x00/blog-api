package com.light.blog.common.utils;


public class Colors {

    /**
     * \033[xxm   将决定后续字符串的颜色
     *
     * 例如： \033[32m RedText \033[34m BlueText"  输出：红、蓝两种颜色
     *
     * 字颜色:30—–37
     * 字背景颜色范围:40—–47
     *
     */
    public static String DEFAULT = "\033[0m";
    public static String RED = "\033[31m";
    public static String GREEN = "\033[32m";
    public static String YELLOW = "\033[33m";
    public static String BLUE = "\033[34m";
    public static String PINK = "\033[35m";
    public static String CYAN = "\033[36m";

    public static String red(String text) {
        return RED + text + DEFAULT;
    }

    public static String green(String text) {
        return GREEN + text + DEFAULT;
    }

    public static String yellow(String text) {
        return YELLOW + text + DEFAULT;
    }

    public static String blue(String text) {
        return BLUE + text + DEFAULT;
    }

    public static String pink(String text) {
        return PINK + text + DEFAULT;
    }

    public static String random(String text) {
        return "\033[" + randomNumber(31, 36) + "m" + text + DEFAULT;
    }

    private static int randomNumber(int s, int e) {
        return (int) (Math.random() * (e - s + 1)) + s;
    }


}
