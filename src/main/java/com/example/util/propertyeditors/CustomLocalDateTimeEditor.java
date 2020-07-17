package com.example.util.propertyeditors;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomLocalDateTimeEditor extends AbstractDateEditor<LocalDateTime> {
    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private final DateTimeFormatter dateTimeFormatter;

    public CustomLocalDateTimeEditor() {
        this(DATE_TIME_PATTERN);
    }

    public CustomLocalDateTimeEditor(boolean allowEmpty) {
        this(DATE_TIME_PATTERN, allowEmpty);
    }

    public CustomLocalDateTimeEditor(boolean allowEmpty, int exactDateLength) {
        this(DATE_TIME_PATTERN, allowEmpty, exactDateLength);
    }

    public CustomLocalDateTimeEditor(String pattern) {
        this.dateTimeFormatter = ofPattern(pattern);
    }

    public CustomLocalDateTimeEditor(String pattern, boolean allowEmpty) {
        super(allowEmpty);
        this.dateTimeFormatter = ofPattern(pattern);
    }

    public CustomLocalDateTimeEditor(String pattern, boolean allowEmpty, int exactDateLength) {
        super(allowEmpty, exactDateLength);
        this.dateTimeFormatter = ofPattern(pattern);
    }

    public CustomLocalDateTimeEditor(DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }

    public CustomLocalDateTimeEditor(DateTimeFormatter dateTimeFormatter, boolean allowEmpty) {
        super(allowEmpty);
        this.dateTimeFormatter = dateTimeFormatter;
    }

    public CustomLocalDateTimeEditor(DateTimeFormatter dateTimeFormatter, boolean allowEmpty, int exactDateLength) {
        super(allowEmpty, exactDateLength);
        this.dateTimeFormatter = dateTimeFormatter;
    }

    @Override
    protected LocalDateTime parse(String text) {
        return LocalDateTime.parse(text, dateTimeFormatter);
    }

    @Override
    protected String format(LocalDateTime dateTime) {
        return dateTimeFormatter.format(dateTime);
    }

    private DateTimeFormatter ofPattern(String pattern) {
        if (pattern == null || pattern.isEmpty()) {
            throw new IllegalArgumentException("Pattern is required");
        }
        return DateTimeFormatter.ofPattern(pattern);
    }

}
