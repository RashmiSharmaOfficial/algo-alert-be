package com.personal.algoAlert.shared.constants;

public class DateTimeFormatConstants {

    public static final String ISO = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final String ISO_MILLIS = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static final String DATE = "yyyy-MM-dd";
    public static final String TIME_HMMSS="H:mm:ss";
    public static final String TIME_HHMMA="hh:mm a";
    public static final String UTC_TIMEZONE="UTC";
    public static final String CLICKHOUSE_WRITE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS";


    private DateTimeFormatConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
