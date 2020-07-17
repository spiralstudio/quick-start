package com.example.util.propertyeditors;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomDateEditor extends AbstractDateEditor<Date> {
    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private final DateFormat dateFormat;

    public CustomDateEditor() {
        this(DATE_TIME_PATTERN);
    }

    public CustomDateEditor(boolean allowEmpty) {
        this(DATE_TIME_PATTERN, allowEmpty);
    }

    public CustomDateEditor(boolean allowEmpty, int exactDateLength) {
        this(DATE_TIME_PATTERN, allowEmpty, exactDateLength);
    }

    public CustomDateEditor(String pattern) {
        this.dateFormat = ofPattern(pattern);
    }

    public CustomDateEditor(String pattern, boolean allowEmpty) {
        super(allowEmpty);
        this.dateFormat = ofPattern(pattern);
    }

    public CustomDateEditor(String pattern, boolean allowEmpty, int exactDateLength) {
        super(allowEmpty, exactDateLength);
        this.dateFormat = ofPattern(pattern);
    }

    public CustomDateEditor(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public CustomDateEditor(DateFormat dateFormat, boolean allowEmpty) {
        super(allowEmpty);
        this.dateFormat = dateFormat;
    }

    public CustomDateEditor(DateFormat dateFormat, boolean allowEmpty, int exactDateLength) {
        super(allowEmpty, exactDateLength);
        this.dateFormat = dateFormat;
    }

    @Override
    protected Date parse(String text) {
        try {
            return dateFormat.parse(text);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Could not parse date", e);
        }
    }

    @Override
    protected String format(Date dateTime) {
        return dateFormat.format(dateTime);
    }

    private DateFormat ofPattern(String pattern) {
        if (pattern == null || pattern.isEmpty()) {
            throw new IllegalArgumentException("Pattern is required");
        }
        return new SimpleDateFormat(pattern);
    }

}
