package com.example.util.propertyeditors;

import java.beans.PropertyEditorSupport;

public abstract class AbstractDateEditor<T> extends PropertyEditorSupport {
    protected static final int EXACT_DATE_LENGTH = -1;
    protected final boolean allowEmpty;
    protected final int exactDateLength;

    public AbstractDateEditor() {
        this.allowEmpty = true;
        this.exactDateLength = EXACT_DATE_LENGTH;
    }

    public AbstractDateEditor(boolean allowEmpty) {
        this.allowEmpty = allowEmpty;
        this.exactDateLength = EXACT_DATE_LENGTH;
    }

    public AbstractDateEditor(boolean allowEmpty, int exactDateLength) {
        this.allowEmpty = allowEmpty;
        this.exactDateLength = exactDateLength;
    }

    /**
     * Format the value, using the specified DateTimeFormatter.
     */
    @SuppressWarnings("unchecked")
    @Override
    public String getAsText() {
        T value = (T) super.getValue();
        if (value == null) {
            return null;
        }
        return format(value);
    }

    /**
     * Parse the given text, using the specified DateTimeFormatter.
     */
    @Override
    public void setAsText(String text) {
        if (text == null || text.length() == 0) {
            if (!allowEmpty) {
                throw new IllegalArgumentException("Date is required");
            }
            super.setValue(null);
            return;
        }
        if (exactDateLength >= 0 && text.length() != exactDateLength) {
            throw new IllegalArgumentException("Could not parse date: it is not exactly" + exactDateLength + "characters long");
        }
        super.setValue(parse(text));
    }

    abstract T parse(String text);

    abstract String format(T t);

}
