package com.example.util.propertyeditors;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CustomLocalTimeEditor extends AbstractDateEditor<LocalTime> {
    private static final String TIME_PATTERN = "HH:mm:ss";
    private final DateTimeFormatter dateTimeFormatter;

    public CustomLocalTimeEditor() {
        this(TIME_PATTERN);
    }

    public CustomLocalTimeEditor(boolean allowEmpty) {
        this(TIME_PATTERN, allowEmpty);
    }

    public CustomLocalTimeEditor(boolean allowEmpty, int exactDateLength) {
        this(TIME_PATTERN, allowEmpty, exactDateLength);
    }

    public CustomLocalTimeEditor(String pattern) {
        this.dateTimeFormatter = ofPattern(pattern);
    }

    public CustomLocalTimeEditor(String pattern, boolean allowEmpty) {
        super(allowEmpty);
        this.dateTimeFormatter = ofPattern(pattern);
    }

    public CustomLocalTimeEditor(String pattern, boolean allowEmpty, int exactDateLength) {
        super(allowEmpty, exactDateLength);
        this.dateTimeFormatter = ofPattern(pattern);
    }

    public CustomLocalTimeEditor(DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }

    public CustomLocalTimeEditor(DateTimeFormatter dateTimeFormatter, boolean allowEmpty) {
        super(allowEmpty);
        this.dateTimeFormatter = dateTimeFormatter;
    }

    public CustomLocalTimeEditor(DateTimeFormatter dateTimeFormatter, boolean allowEmpty, int exactDateLength) {
        super(allowEmpty, exactDateLength);
        this.dateTimeFormatter = dateTimeFormatter;
    }

    @Override
    protected LocalTime parse(String text) {
        return LocalTime.parse(text, dateTimeFormatter);
    }

    @Override
    protected String format(LocalTime dateTime) {
        return dateTimeFormatter.format(dateTime);
    }

    private DateTimeFormatter ofPattern(String pattern) {
        if (pattern == null || pattern.isEmpty()) {
            throw new IllegalArgumentException("Pattern is required");
        }
        return DateTimeFormatter.ofPattern(pattern);
    }

}
