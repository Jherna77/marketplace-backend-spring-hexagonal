package com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper.helper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for formatting and parsing dates.
 * It provides methods to convert between LocalDateTime and String representations of dates.
 */
@Mapper(componentModel = "spring")
public class DateMapper {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yy");

    @Named("formatDate")
    public String formatDate(LocalDateTime date) {
        return date != null ? date.format(FORMATTER) : null;
    }

    @Named("parseDate")
    public LocalDateTime parseDate(String date) {
        return date != null
            ? LocalDateTime.of(
                java.time.LocalDate.parse(date, FORMATTER),
                java.time.LocalTime.MIDNIGHT)
            : null;
    }
}
