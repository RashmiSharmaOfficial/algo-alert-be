package com.personal.algoAlert.shared.helpers;

import com.personal.algoAlert.shared.constants.DateTimeFormatConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

@Service
@Slf4j
public class DateTimeConverter {

    public static final DateFormat getDateTimeFormat(){
        return new SimpleDateFormat(DateTimeFormatConstants.ISO);
    }

    public static final DateFormat getDateFormatter(){
        return new SimpleDateFormat(DateTimeFormatConstants.DATE);
    }

    public static final SimpleDateFormat getSdf(){
        return new SimpleDateFormat(DateTimeFormatConstants.TIME_HMMSS);
    }

    public static String currentDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return currentDateTime.format(formatter);
    }

    public static String addDaysToDateTime(String dateTime, int daysToAdd) {
        try {
            // Define the formatter
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            // Parse the input date string to LocalDateTime
            LocalDateTime localDateTime = LocalDateTime.parse(dateTime, formatter);

            // Add days
            LocalDateTime newDateTime = localDateTime.plusDays(daysToAdd);

            // Format the new date-time to string
            return newDateTime.format(formatter);
        } catch (Exception e) {
            log.error("Error occurred while adding days to date-time (%s)".formatted(dateTime), e);
            return null;
        }
    }

    public DateTimeConverter() {
        //to ensure timezone is set to utc
        getDateTimeFormat().setTimeZone(TimeZone.getTimeZone(DateTimeFormatConstants.UTC_TIMEZONE));
        getDateFormatter().setTimeZone(TimeZone.getTimeZone(DateTimeFormatConstants.UTC_TIMEZONE));
        getSdf().setTimeZone(TimeZone.getTimeZone(DateTimeFormatConstants.UTC_TIMEZONE));
    }

    public static Date convertToISO(String time) {
        try {
            if (time != null) {
                return getDateTimeFormat().parse(time);
            } else return null;
        } catch (Exception e) {
            log.error("Error occurred while converting time (%s) to ISO format".formatted(time), e);
            return null;
        }
    }

    public static Date convertToYYYYMMDD(String time) {
        try {
            if (time != null) {
                return getDateFormatter().parse(time);
            } else return null;
        } catch (Exception e) {
            log.error("Error occurred while converting time (%s) to YYYYMMDD format".formatted(time), e);
            return null;
        }
    }


    public static String covertTimeTo12Hr(String time) {
        try {
            if (time != null) {
                final Date dateObj = getSdf().parse(time);
                return new SimpleDateFormat(DateTimeFormatConstants.TIME_HHMMA).format(dateObj).toUpperCase();
            } else return null;
        } catch (Exception e) {
            log.error("Error occurred while converting time(%s) to 12Hr format".formatted(time), e);
            return null;
        }

    }
}
