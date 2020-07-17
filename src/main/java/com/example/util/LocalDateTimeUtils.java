package com.example.util;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjusters;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class LocalDateTimeUtils {
    private static final ConcurrentMap<String, DateTimeFormatter> formatterMap = new ConcurrentHashMap<>();
    private static final DateTimeFormatter dateFormatter = ofPattern(DatePattern.DATE);
    private static final DateTimeFormatter timeFormatter = ofPattern(DatePattern.TIME);
    private static final DateTimeFormatter dateTimeFormatter = ofPattern(DatePattern.DATE_TIME);

    private LocalDateTimeUtils() {
    }

    public static DateTimeFormatter ofPattern(String pattern) {
        if (pattern == null || pattern.isEmpty()) {
            throw new IllegalArgumentException("Pattern is required");
        }
        return formatterMap.computeIfAbsent(pattern, DateTimeFormatter::ofPattern);
    }

    public static LocalDateTime datetime() {
        return LocalDateTime.now();
    }

    public static LocalDate date() {
        return LocalDate.now();
    }

    public static LocalTime time() {
        return LocalTime.now();
    }

    public static LocalDate date(LocalDateTime dateTime) {
        return dateTime.toLocalDate();
    }

    public static LocalTime time(LocalDateTime dateTime) {
        return dateTime.toLocalTime();
    }

    public static long timestamp() {
        return System.currentTimeMillis();
    }

    public static long timestamp(LocalDateTime dateTime) {
        return dateTime.toInstant(OffsetDateTime.now().getOffset()).toEpochMilli();
    }

    public static String now() {
        return dateTimeFormatter.format(LocalDateTime.now());
    }

    public static String format(LocalDateTime dateTime) {
        return dateTimeFormatter.format(dateTime);
    }

    public static String format(LocalDateTime dateTime, String pattern) {
        return ofPattern(pattern).format(dateTime);
    }

    public static String format(LocalDate date) {
        return dateFormatter.format(date);
    }

    public static String format(LocalDate date, String pattern) {
        return ofPattern(pattern).format(date);
    }

    public static String format(LocalTime time) {
        return timeFormatter.format(time);
    }

    public static String format(LocalTime time, String pattern) {
        return ofPattern(pattern).format(time);
    }

    public static String format(long timestamp) {
        return dateTimeFormatter.format(parseDateTime(timestamp));
    }

    public static String format(long timestamp, String pattern) {
        return ofPattern(pattern).format(parseDateTime(timestamp));
    }

    public static String formatDateTime(String s, String parsePattern, String formatPattern) {
        return format(parseDateTime(s, parsePattern), formatPattern);
    }

    public static String formatDate(String s, String parsePattern, String formatPattern) {
        return format(parseDate(s, parsePattern), formatPattern);
    }

    public static String formatTime(String s, String parsePattern, String formatPattern) {
        return format(parseTime(s, parsePattern), formatPattern);
    }

    public static LocalDateTime parseDateTime(String s) {
        return LocalDateTime.parse(s, dateTimeFormatter);
    }

    public static LocalDateTime parseDateTime(String s, String pattern) {
        return LocalDateTime.parse(s, ofPattern(pattern));
    }

    public static LocalDateTime parseDateTime(long timestamp) {
        return Instant.ofEpochMilli(timestamp).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static LocalDate parseDate(String s) {
        return LocalDate.parse(s, dateFormatter);
    }

    public static LocalDate parseDate(String s, String pattern) {
        return LocalDate.parse(s, ofPattern(pattern));
    }

    public static LocalDate parseDate(long timestamp) {
        return Instant.ofEpochMilli(timestamp).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalTime parseTime(String s) {
        return LocalTime.parse(s, timeFormatter);
    }

    public static LocalTime parseTime(String s, String pattern) {
        return LocalTime.parse(s, ofPattern(pattern));
    }

    public static LocalTime parseTime(long timestamp) {
        return Instant.ofEpochMilli(timestamp).atZone(ZoneId.systemDefault()).toLocalTime();
    }

    public static LocalDateTime startOfDay(LocalDate date) {
        return LocalDateTime.of(date, LocalTime.MIN);
    }

    public static LocalDateTime endOfDay(LocalDate date) {
        return LocalDateTime.of(date, LocalTime.MAX);
    }

    public static LocalDateTime startOfDay(LocalDateTime dateTime) {
        return LocalDateTime.of(dateTime.toLocalDate(), LocalTime.MIN);
    }

    public static LocalDateTime endOfDay(LocalDateTime dateTime) {
        return LocalDateTime.of(dateTime.toLocalDate(), LocalTime.MAX);
    }

    public static LocalDateTime plusYears(LocalDateTime dateTime, long amount) {
        return dateTime.plusYears(amount);
    }

    public static LocalDateTime plusMonths(LocalDateTime dateTime, long amount) {
        return dateTime.plusMonths(amount);
    }

    public static LocalDateTime plusWeeks(LocalDateTime dateTime, long amount) {
        return dateTime.plusWeeks(amount);
    }

    public static LocalDateTime plusDays(LocalDateTime dateTime, long amount) {
        return dateTime.plusDays(amount);
    }

    public static LocalDateTime plusHours(LocalDateTime dateTime, long amount) {
        return dateTime.plusHours(amount);
    }

    public static LocalDateTime plusMinutes(LocalDateTime dateTime, long amount) {
        return dateTime.plusMinutes(amount);
    }

    public static LocalDateTime plusSeconds(LocalDateTime dateTime, long amount) {
        return dateTime.plusSeconds(amount);
    }

    public static LocalDateTime plusMilliseconds(LocalDateTime dateTime, long amount) {
        return dateTime.plus(amount, ChronoUnit.MILLIS);
    }

    public static LocalDateTime plusMicroseconds(LocalDateTime dateTime, long amount) {
        return dateTime.plus(amount, ChronoUnit.MICROS);
    }

    public static LocalDateTime plusNanoseconds(LocalDateTime dateTime, long amount) {
        return dateTime.plus(amount, ChronoUnit.NANOS);
    }

    public static LocalDate plusYears(LocalDate date, long amount) {
        return date.plusYears(amount);
    }

    public static LocalDate plusMonths(LocalDate date, long amount) {
        return date.plusMonths(amount);
    }

    public static LocalDate plusWeeks(LocalDate date, long amount) {
        return date.plusWeeks(amount);
    }

    public static LocalDate plusDays(LocalDate date, long amount) {
        return date.plusDays(amount);
    }

    public static LocalTime plusHours(LocalTime time, long amount) {
        return time.plusHours(amount);
    }

    public static LocalTime plusMinutes(LocalTime time, long amount) {
        return time.plusMinutes(amount);
    }

    public static LocalTime plusSeconds(LocalTime time, long amount) {
        return time.plusSeconds(amount);
    }

    public static LocalTime plusMilliseconds(LocalTime time, long amount) {
        return time.plus(amount, ChronoUnit.MILLIS);
    }

    public static LocalTime plusMicroseconds(LocalTime time, long amount) {
        return time.plus(amount, ChronoUnit.MICROS);
    }

    public static LocalTime plusNanoseconds(LocalTime time, long amount) {
        return time.plus(amount, ChronoUnit.NANOS);
    }

    public static LocalDateTime adjustFirstDayOfMonth(LocalDateTime dateTime) {
        return dateTime.with(TemporalAdjusters.firstDayOfMonth());
    }

    public static LocalDate adjustFirstDayOfMonth(LocalDate date) {
        return date.with(TemporalAdjusters.firstDayOfMonth());
    }

    public static LocalDateTime adjustLastDayOfMonth(LocalDateTime dateTime) {
        return dateTime.with(TemporalAdjusters.lastDayOfMonth());
    }

    public static LocalDate adjustLastDayOfMonth(LocalDate date) {
        return date.with(TemporalAdjusters.lastDayOfMonth());
    }

    public static long differenceOfYear(Temporal temporal1Inclusive, Temporal temporal2Exclusive) {
        return ChronoUnit.YEARS.between(temporal1Inclusive, temporal2Exclusive);
    }

    public static long differenceOfMonth(Temporal temporal1Inclusive, Temporal temporal2Exclusive) {
        return ChronoUnit.MONTHS.between(temporal1Inclusive, temporal2Exclusive);
    }

    public static long differenceOfWeek(Temporal temporal1Inclusive, Temporal temporal2Exclusive) {
        return ChronoUnit.WEEKS.between(temporal1Inclusive, temporal2Exclusive);
    }

    public static long differenceOfDay(Temporal temporal1Inclusive, Temporal temporal2Exclusive) {
        return ChronoUnit.DAYS.between(temporal1Inclusive, temporal2Exclusive);
    }

    public static long differenceOfHour(Temporal temporal1Inclusive, Temporal temporal2Exclusive) {
        return ChronoUnit.HOURS.between(temporal1Inclusive, temporal2Exclusive);
    }

    public static long differenceOfMinute(Temporal temporal1Inclusive, Temporal temporal2Exclusive) {
        return ChronoUnit.MINUTES.between(temporal1Inclusive, temporal2Exclusive);
    }

    public static long differenceOfSecond(Temporal temporal1Inclusive, Temporal temporal2Exclusive) {
        return ChronoUnit.SECONDS.between(temporal1Inclusive, temporal2Exclusive);
    }

    public static long differenceOfMillisecond(Temporal temporal1Inclusive, Temporal temporal2Exclusive) {
        return ChronoUnit.MILLIS.between(temporal1Inclusive, temporal2Exclusive);
    }

    public static long differenceOfMicrosecond(Temporal temporal1Inclusive, Temporal temporal2Exclusive) {
        return ChronoUnit.MICROS.between(temporal1Inclusive, temporal2Exclusive);
    }

    public static long differenceOfNanosecond(Temporal temporal1Inclusive, Temporal temporal2Exclusive) {
        return ChronoUnit.NANOS.between(temporal1Inclusive, temporal2Exclusive);
    }

    public static int dayOfYear(LocalDateTime dateTime) {
        return dateTime.getDayOfYear();
    }

    public static int dayOfYear(LocalDate date) {
        return date.getDayOfYear();
    }

    public static int dayOfMonth(LocalDateTime dateTime) {
        return dateTime.getDayOfMonth();
    }

    public static int dayOfMonth(LocalDate date) {
        return date.getDayOfMonth();
    }

    public static int dayOfWeek(LocalDateTime dateTime) {
        return dateTime.getDayOfWeek().getValue();
    }

    public static int dayOfWeek(LocalDate date) {
        return date.getDayOfWeek().getValue();
    }

    public static int lastDayOfMonth(LocalDateTime dateTime) {
        return adjustLastDayOfMonth(dateTime).getDayOfMonth();
    }

    public static int lastDayOfMonth(LocalDate date) {
        return adjustLastDayOfMonth(date).getDayOfMonth();
    }

    public static boolean isWeekend(LocalDateTime dateTime) {
        DayOfWeek dayOfWeek = dateTime.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }

    public static boolean isWeekend(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }

}
