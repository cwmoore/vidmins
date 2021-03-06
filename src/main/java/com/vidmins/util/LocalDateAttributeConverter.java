package com.vidmins.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.time.LocalDate;

// TODO: use Hibernate 5 built in converters
/**
 * Convert from sqlDate to LocalDate. This will be used by Hibernate.
 * Class based on: http://www.thoughts-on-java.org/persist-localdate-localdatetime-jpa/
 *
 * @author pwaite
 * @author cwmoore
 */
@Converter(autoApply=true)// eliminates the need to add  @Convert(converter = LocalDateAttributeConverter on each attribute)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDate locDate) {
        return (locDate == null ? null : Date.valueOf(locDate));
    }

    @Override
    public LocalDate convertToEntityAttribute(Date sqlDate) {
        return (sqlDate == null ? null : sqlDate.toLocalDate());
    }
}