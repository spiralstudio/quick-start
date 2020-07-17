package com.example.util.propertyeditors;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomLocalDateEditor extends AbstractDateEditor<LocalDate> {
    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private final DateTimeFormatter dateTimeFormatter;

    public CustomLocalDateEditor() {
        this(DATE_PATTERN);
    }

    public CustomLocalDateEditor(boolean allowEmpty) {
        this(DATE_PATTERN, allowEmpty);
    }

    public CustomLocalDateEditor(boolean allowEmpty, int exactDateLength) {
        this(DATE_PATTERN, allowEmpty, exactDateLength);
    }

    public CustomLocalDateEditor(String pattern) {
        this.dateTimeFormatter = ofPattern(pattern);
    }

    public CustomLocalDateEditor(String pattern, boolean allowEmpty) {
        super(allowEmpty);
        this.dateTimeFormatter = ofPattern(pattern);
    }

    public CustomLocalDateEditor(String pattern, boolean allowEmpty, int exactDateLength) {
        super(allowEmpty, exactDateLength);
        this.dateTimeFormatter = ofPattern(pattern);
    }

    public CustomLocalDateEditor(DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }

    public CustomLocalDateEditor(DateTimeFormatter dateTimeFormatter, boolean allowEmpty) {
        super(allowEmpty);
        this.dateTimeFormatter = dateTimeFormatter;
    }

    public CustomLocalDateEditor(DateTimeFormatter dateTimeFormatter, boolean allowEmpty, int exactDateLength) {
        super(allowEmpty, exactDateLength);
        this.dateTimeFormatter = dateTimeFormatter;
    }

    @Override
    protected LocalDate parse(String text) {
        return LocalDate.parse(text, dateTimeFormatter);
    }

    @Override
    protected String format(LocalDate dateTime) {
        return dateTimeFormatter.format(dateTime);
    }

    private DateTimeFormatter ofPattern(String pattern) {
        if (pattern == null || pattern.isEmpty()) {
            throw new IllegalArgumentException("Pattern is required");
        }
        return DateTimeFormatter.ofPattern(pattern);
    }

}
