package com.dingmao.platform.controller.editor;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.util.StringUtils;

public class DateEditor extends PropertyEditorSupport {


    private  final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private  final DateFormat TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private DateFormat dateFormat;
    private boolean isEmpty = true;

    public DateEditor() {
    }

    public DateEditor(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public DateEditor(DateFormat dateFormat, boolean isEmpty) {
        this.dateFormat = dateFormat;
        this.isEmpty = isEmpty;
    }

    /**
     * Parse the Date from the given text, using the specified DateFormat.
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (this.isEmpty && !StringUtils.hasText(text)) {
            setValue(null);
        } else {
            try {
                if(this.dateFormat != null)
                    setValue(this.dateFormat.parse(text));
                else {
                    if(text.contains(":"))
                        setValue(TIME_FORMAT.parse(text));
                    else
                        setValue(DATE_FORMAT.parse(text));
                }
            } catch (ParseException ex) {
                throw new IllegalArgumentException("Could not parse date: " + ex.getMessage(), ex);
            }
        }
    }

    /**
     * Format the Date as String, using the specified DateFormat.
     */
    @Override
    public String getAsText() {
        Date value = (Date) getValue();
        DateFormat dateFormat = this.dateFormat;
        if(dateFormat == null)
            dateFormat = TIME_FORMAT;
        return (value != null ? dateFormat.format(value) : "");
    }

}
