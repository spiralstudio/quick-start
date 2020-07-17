package com.example.util;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.function.Function;

public final class StringUtils {
    public static final String EMPTY = "";
    public static final String NULL = null;
    public static final String LINE_BREAK = "[\n\r]";

    private StringUtils() {
    }

    public static boolean isNull(CharSequence text) {
        return text == null;
    }

    public static boolean isNotNull(CharSequence text) {
        return text != null;
    }

    public static boolean isEmpty(CharSequence text) {
        return text == null || text.length() == 0;
    }

    public static boolean isNotEmpty(CharSequence text) {
        return !isEmpty(text);
    }

    public static boolean isBlank(CharSequence text) {
        if (isEmpty(text)) {
            return true;
        }
        for (int i = 0, len = text.length(); i < len; ++i) {
            if (!Character.isWhitespace(text.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotBlank(CharSequence text) {
        return isNotEmpty(text) && !isBlank(text);
    }

    public static int getLength(CharSequence text) {
        if (text == null) {
            return 0;
        }
        return text.length();
    }

    public static String trim(String text) {
        return text == null ? null : text.trim();
    }

    public static String trimToEmpty(String text) {
        return text == null ? EMPTY : text.trim();
    }

    public static String trimToNull(String text) {
        String trimmed = trim(text);
        return isEmpty(trimmed) ? null : trimmed;
    }

    public static String trimLeft(String value) {
        if (isEmpty(value)) {
            return value;
        }
        int len = value.length();
        int st = 0;
        while ((st < len) && (value.charAt(st) <= ' ')) {
            st++;
        }
        if (st == len) {
            return EMPTY;
        }
        return st > 0 ? value.substring(st, len) : value;
    }

    public static String trimRight(String value) {
        if (isEmpty(value)) {
            return value;
        }
        int len = value.length();
        while ((0 < len) && (value.charAt(len - 1) <= ' ')) {
            len--;
        }
        if (len == 0) {
            return EMPTY;
        }
        return len < value.length() ? value.substring(0, len) : value;
    }

    public static String remove(String text, String target) {
        if (isEmpty(text)) {
            return text;
        }
        return text.replace(target, EMPTY);
    }

    public static String removeFirst(String text, String regex) {
        if (isEmpty(text)) {
            return text;
        }
        return text.replaceFirst(regex, EMPTY);
    }

    public static String removeAll(String text, String regex) {
        if (isEmpty(text)) {
            return text;
        }
        return text.replaceAll(regex, EMPTY);
    }

    public static String parse(byte[] bytes, Charset charset) {
        if (bytes == null) {
            return null;
        }
        return new String(bytes, charset);
    }

    public static String parse(byte[] bytes) {
        return parse(bytes, StandardCharsets.UTF_8);
    }

    public static byte[] getBytes(String text, Charset charset) {
        return text.getBytes(charset);
    }

    public static byte[] getBytes(String text) {
        return text.getBytes(StandardCharsets.UTF_8);
    }

    public static String join(CharSequence delimiter, Object... elements) {
        Objects.requireNonNull(delimiter, "Delimiter is required");
        if (elements == null || elements.length == 0) {
            return EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        int lastIndex = elements.length - 1;
        for (int i = 0; i < lastIndex; i++) {
            sb.append(elements[i]).append(delimiter);
        }
        sb.append(elements[lastIndex]);
        return sb.toString();
    }

    public static <T> String join(CharSequence delimiter, Function<T, ?> mapper, T... elements) {
        Objects.requireNonNull(delimiter, "Delimiter is required");
        Objects.requireNonNull(mapper, "Mapper is required");
        if (elements == null || elements.length == 0) {
            return EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        int lastIndex = elements.length - 1;
        for (int i = 0; i < lastIndex; i++) {
            sb.append(mapper.apply(elements[i])).append(delimiter);
        }
        sb.append(mapper.apply(elements[lastIndex]));
        return sb.toString();
    }

    public static String join(CharSequence delimiter, Iterable<?> elements) {
        Objects.requireNonNull(delimiter, "Delimiter is required");
        if (elements == null) {
            return EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        for (Object o : elements) {
            sb.append(o).append(delimiter);
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - delimiter.length());
        }
        return sb.toString();
    }

    public static <T> String join(CharSequence delimiter, Function<T, ?> mapper, Iterable<T> elements) {
        Objects.requireNonNull(delimiter, "Delimiter is required");
        Objects.requireNonNull(mapper, "Mapper is required");
        if (elements == null) {
            return EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        for (T t : elements) {
            sb.append(mapper.apply(t)).append(delimiter);
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - delimiter.length());
        }
        return sb.toString();
    }

}
