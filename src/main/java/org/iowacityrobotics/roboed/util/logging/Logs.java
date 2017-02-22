package org.iowacityrobotics.roboed.util.logging;

/**
 * Simple logging implementation.
 * @author Evan Geng
 */
public class Logs {

    private static LogLevel currentLevel = LogLevel.INFO;

    public static void setLevel(LogLevel level) {
        currentLevel = level;
    }

    public static void debug(String msg) {
        print(msg, LogLevel.DEBUG);
    }

    public static void debug(String format, Object... arguments) {
        print(format(format, arguments), LogLevel.DEBUG);
    }

    public static void debug(String msg, Throwable t) {
        print(format("{} ({})", msg, t.getMessage()), LogLevel.DEBUG);
        t.printStackTrace(System.out);
    }

    public static void info(String msg) {
        print(msg, LogLevel.INFO);
    }

    public static void info(String format, Object... arguments) {
        print(format(format, arguments), LogLevel.INFO);
    }

    public static void info(String msg, Throwable t) {
        print(format("{} ({})", msg, t.getMessage()), LogLevel.INFO);
        t.printStackTrace(System.out);
    }

    public static void warn(String msg) {
        print(msg, LogLevel.WARN);
    }

    public static void warn(String format, Object... arguments) {
        print(format(format, arguments), LogLevel.WARN);
    }

    public static void warn(String msg, Throwable t) {
        print(format("{} ({})", msg, t.getMessage()), LogLevel.WARN);
        t.printStackTrace(System.out);
    }

    public static void error(String msg) {
        print(msg, LogLevel.ERROR);
    }

    public static void error(String format, Object... arguments) {
        print(format(format, arguments), LogLevel.ERROR);
    }

    public static void error(String msg, Throwable t) {
        print(format("{} ({})", msg, t.getMessage()), LogLevel.ERROR);
        t.printStackTrace(System.out);
    }

    private static void print(String msg, LogLevel level) {
        if (currentLevel.hasLevel(level))
            System.out.printf("[%s] %s\n", level, msg);
    }

    private static String format(String formatArg, Object... args) {
        String format = formatArg;
        int ind, i = 0;
        while ((ind = format.indexOf("{}")) != -1 && i < args.length)
            format = format.substring(0, ind).concat(resolve(args[i++])).concat(format.substring(ind + 2, format.length()));
        return format;
    }

    private static String resolve(Object o) {
        if (o == null)
            return "<null>";
        return o.toString();
    }

}
