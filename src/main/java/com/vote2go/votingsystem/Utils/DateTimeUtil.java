package com.vote2go.votingsystem.Utils;

import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm";
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

    public static final LocalDate MIN_DATE = LocalDate.of(1, 1, 1);
    public static final LocalDate MAX_DATE = LocalDate.of(3000, 1,1);

    public static final LocalTime  DEFAULT_VOTE_DEADELINE_TIME = LocalTime.of(11, 0, 0);
    private static LocalTime deadLineVoteTime = DEFAULT_VOTE_DEADELINE_TIME;

    private DateTimeUtil(){
    }

    public static <T extends Comparable<? super T>> boolean isBetween(T value, T start, T end){
        return value.compareTo(start) >= 0 && value.compareTo(end)<=0;
    }

    public static String toString(LocalDateTime idt){
        return idt == null ? "" : idt.format(DATE_TIME_FORMATTER);
    }

    public static LocalDate parseLocalDate(String str) {
        return StringUtils.isEmpty(str) ? null : LocalDate.parse(str);
    }

    public static LocalDateTime parseLocalDateTime(String str) {
        return parseLocalDateTime(str, DATE_TIME_FORMATTER);
    }

    public static LocalDateTime parseLocalDateTime(String str, DateTimeFormatter formatter) {
        return StringUtils.isEmpty(str) ? LocalDateTime.now() : LocalDateTime.parse(str, formatter);
    }

    public static LocalTime getDeadlineVoteTime() {
        return deadLineVoteTime;
    }

    public static void setDeadlineVoteTime(LocalTime deadlineVoteTime) {
        DateTimeUtil.deadLineVoteTime = deadlineVoteTime;
    }

}
