package com.lcl.galaxy.springmvc.utils;

import org.springframework.core.convert.converter.Converter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateConverter implements Converter<String, LocalDate> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public LocalDate convert(String dateStr) {
        LocalDate date = LocalDate.parse(dateStr, formatter);
        return date;
    }
}
