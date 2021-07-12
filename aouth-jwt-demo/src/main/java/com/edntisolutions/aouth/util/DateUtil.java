package com.edntisolutions.aouth.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateUtil {

    public static LocalDateTime dateToLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static Date localDateTimeToDate(LocalDateTime dateToBeConverted) {
        return Date.from(dateToBeConverted.atZone(ZoneId.systemDefault()).toInstant());
    }

}
