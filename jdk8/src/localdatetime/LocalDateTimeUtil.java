package localdatetime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Date;

public class LocalDateTimeUtil {

    public static final String YYYY = "yyyy";
    public static final String YYYYMM = "yyyyMM";
    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String YYYYMMDDHH = "yyyyMMddHH";
    public static final String YYYYMMDDHHMM = "yyyyMMddHHmm";
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String YYYY_MM = "yyyy-MM";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_HH = "yyyy-MM-dd HH";
    public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    private static final String BASE_TIME_FORMAT = "[yyyyMMddHHmmss][yyyyMMddHHmm][yyyyMMddHH][yyyyMMdd][yyyyMM][yyyy]" +
            "[[-][/][.]MM][[-][/][.]dd][ ][HH][[:][.]mm][[:][.]ss][[:][.]SSS]";

    public static void main(String[] args) {

        // 测试链式调用
        System.out.println(builder().parse("2019-09-25").atStartOfMonth().plusHours(1).toDate());

        // 测试format
        System.out.println(format(LocalDateTime.now(), YYYY_MM_DD_HH_MM_SS));

        // 测试解析时间
        System.out.println(parse("2019"));
        System.out.println(parse("201907"));
        System.out.println(parse("20190726"));
        System.out.println(parse("2019072615"));
        System.out.println(parse("201907261546"));
        System.out.println(parse("20190726154628"));

        System.out.println(parse("2019-07"));
        System.out.println(parse("2019-07-26"));
        System.out.println(parse("2019-07-26 15"));
        System.out.println(parse("2019-07-26 15:47"));
        System.out.println(parse("2019-07-26 15:47:26"));
        System.out.println(parse("2019-07-26 15:47:26:156"));

        System.out.println(parse("2019/07"));
        System.out.println(parse("2019/07/26"));
        System.out.println(parse("2019/07/26 15"));
        System.out.println(parse("2019/07/26 15:47"));
        System.out.println(parse("2019/07/26 15:47:58"));
        System.out.println(parse("2019/07/26 15:47:58:147"));

        System.out.println(parse("2019.07"));
        System.out.println(parse("2019.07.26"));
        System.out.println(parse("2019.07.26 15"));
        System.out.println(parse("2019.07.26 15.48"));
        System.out.println(parse("2019.07.26 15.48.49"));
        System.out.println(parse("2019.07.26 15.48.49.123"));

        System.out.println(parse("07-26"));
        System.out.println(parse("07-26 15:49:12"));

        // 测试传入自定义时间格式
        System.out.println(parseByPattern("26-07-2019", "dd-MM-yyyy"));

        // 测试获取开始时间的方法
        LocalDateTime time = LocalDateTime.now();
        System.out.println(atStartOfSecond(time));
        System.out.println(atStartOfMinute(time));
        System.out.println(atStartOfHour(time));
        System.out.println(atStartOfDay(time));
        System.out.println(atStartOfWeek(time));
        System.out.println(atStartOfMonth(time));
        System.out.println(atStartOfYear(time));

        // 测试获取时间间隔的方法
        System.out.println(formatDuration(LocalDateTime.of(2018, 6, 25, 15, 0, 0), time));

        // 测试来回转换
        System.out.println(toDate(time));
        System.out.println(fromDate(new Date()));

        System.out.println(toInstant(time));
        System.out.println(fromInstant(Instant.now()));

        System.out.println(toTimestamp(time));
        System.out.println(fromTimestamp(Instant.now().toEpochMilli()));
    }

    /**
     * 根据pattern格式化时间
     *
     * @param localDateTime localDateTime
     * @param pattern       pattern
     * @return String
     */
    public static String format(LocalDateTime localDateTime, String pattern) {
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 【推荐】解析常用时间字符串，支持,并不局限于以下形式：
     * [yyyy][yyyy-MM][yyyy-MM-dd][yyyy-MM-dd HH][yyyy-MM-dd HH:mm][yyyy-MM-dd HH:mm:ss][yyyy-MM-dd HH:mm:ss:SSS]
     * [yyyy][yyyy/MM][yyyy/MM/dd][yyyy/MM/dd HH][yyyy/MM/dd HH:mm][yyyy/MM/dd HH:mm:ss][yyyy/MM/dd HH:mm:ss:SSS]
     * [yyyy][yyyy.MM][yyyy.MM.dd][yyyy.MM.dd HH][yyyy.MM.dd HH.mm][yyyy.MM.dd HH.mm.ss][yyyy.MM.dd HH.mm.ss.SSS]
     * [yyyy][yyyyMM][yyyyMMdd][yyyyMMddHH][yyyyMMddHHmm][yyyyMMddHHmmss]
     * [MM-dd]
     * 不支持yyyyMMddHHmmssSSS，因为本身DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")就不支持这个形式
     *
     * @param timeString timeString
     * @return LocalDateTime
     */
    public static LocalDateTime parse(String timeString) {
        return LocalDateTime.parse(timeString, getDateTimeFormatterByPattern(BASE_TIME_FORMAT));
    }

    /**
     * 根据传进来的pattern返回LocalDateTime，自动补齐
     *
     * @param timeString timeString
     * @param pattern    pattern
     * @return LocalDateTime
     */
    public static LocalDateTime parseByPattern(String timeString, String pattern) {
        return LocalDateTime.parse(timeString, getDateTimeFormatterByPattern(pattern));
    }

    /**
     * 自由解析时间的基础
     *
     * @param pattern pattern
     * @return DateTimeFormatter
     */
    private static DateTimeFormatter getDateTimeFormatterByPattern(String pattern) {
        return new DateTimeFormatterBuilder()
                .appendPattern(pattern)
                .parseDefaulting(ChronoField.YEAR_OF_ERA, LocalDateTime.now().getYear())
                .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
                .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                .parseDefaulting(ChronoField.NANO_OF_SECOND, 0)
                .toFormatter();
    }

    public static LocalDate toLocalDate(LocalDateTime localDateTime) {
        return localDateTime.toLocalDate();
    }

    public static LocalDateTime fromLocalDate(LocalDate localDate) {
        return localDate.atStartOfDay();
    }

    public static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime fromDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static long toTimestamp(LocalDateTime localDateTime) {
        return toInstant(localDateTime).toEpochMilli();
    }

    public static LocalDateTime fromTimestamp(long timestamp) {
        return fromInstant(Instant.ofEpochMilli(timestamp));
    }

    public static Instant toInstant(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant();
    }

    public static LocalDateTime fromInstant(Instant instant) {
        return instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static LocalDateTime atStartOfSecond(LocalDateTime localDateTime) {
        return localDateTime.withNano(0);
    }

    public static LocalDateTime atStartOfMinute(LocalDateTime localDateTime) {
        return atStartOfSecond(localDateTime).withSecond(0);
    }

    public static LocalDateTime atStartOfHour(LocalDateTime localDateTime) {
        return atStartOfMinute(localDateTime).withMinute(0);
    }

    public static LocalDateTime atStartOfDay(LocalDateTime localDateTime) {
        return atStartOfHour(localDateTime).withHour(0);
    }

    public static LocalDateTime atStartOfWeek(LocalDateTime localDateTime) {
        return atStartOfDay(localDateTime).minusDays(localDateTime.getDayOfWeek().getValue() - 1);
    }

    public static LocalDateTime atStartOfMonth(LocalDateTime localDateTime) {
        return atStartOfDay(localDateTime).withDayOfMonth(1);
    }

    public static LocalDateTime atStartOfYear(LocalDateTime localDateTime) {
        return atStartOfMonth(localDateTime).withMonth(1);
    }

    /**
     * 获得形如：XXX 天 XXX 小时 XXX 分 XXX 秒 XXX 毫秒 的格式化后的时间间隔
     * 如只想获得统一单位的时间间隔，请直接用 Duration
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return String
     */
    public static String formatDuration(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime.compareTo(endTime) > 0) {
            return "出错啦 ! 起始时间大于结束时间";
        }
        Duration duration = Duration.between(startTime, endTime);
        StringBuilder output = new StringBuilder();
        long day = duration.toDays();
        if (day > 0) {
            output.append(day).append(" 天 ");
            duration = duration.minusDays(day);
        }
        long hour = duration.toHours();
        if (hour > 0) {
            output.append(hour).append(" 小时 ");
            duration = duration.minusHours(hour);
        }
        long minute = duration.toMinutes();
        if (minute > 0) {
            output.append(minute).append(" 分 ");
            duration = duration.minusMinutes(minute);
        }
        long second = duration.getSeconds();
        if (second > 0) {
            output.append(second).append(" 秒 ");
            duration = duration.minusSeconds(second);
        }
        output.append(duration.toMillis()).append(" 毫秒");
        return output.toString();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(LocalDateTime localDateTime) {
        return new Builder(localDateTime);
    }

    public static class Builder {

        private LocalDateTime builderTime;

        public Builder() {
            this.builderTime = LocalDateTime.now();
        }

        public Builder(LocalDateTime localDateTime) {
            this.builderTime = localDateTime;
        }

        public Builder now() {
            this.builderTime = LocalDateTime.now();
            return this;
        }

        public Builder parse(String timeString) {
            this.builderTime = LocalDateTimeUtil.parse(timeString);
            return this;
        }

        public Builder parseByPattern(String timeString, String pattern) {
            this.builderTime = LocalDateTimeUtil.parseByPattern(timeString, pattern);
            return this;
        }

        public Builder fromLocalDate(LocalDate localDate) {
            this.builderTime = LocalDateTimeUtil.fromLocalDate(localDate);
            return this;
        }

        public Builder fromDate(Date date) {
            this.builderTime = LocalDateTimeUtil.fromDate(date);
            return this;
        }

        public Builder fromTimestamp(long timestamp) {
            this.builderTime = LocalDateTimeUtil.fromTimestamp(timestamp);
            return this;
        }

        public Builder fromInstant(Instant instant) {
            this.builderTime = LocalDateTimeUtil.fromInstant(instant);
            return this;
        }

        public Builder withYear(int year) {
            this.builderTime = this.builderTime.withYear(year);
            return this;
        }

        public Builder withMonth(int month) {
            this.builderTime = this.builderTime.withMonth(month);
            return this;
        }

        public Builder withDayOfMonth(int dayOfMonth) {
            this.builderTime = this.builderTime.withDayOfMonth(dayOfMonth);
            return this;
        }

        public Builder withDayOfYear(int dayOfYear) {
            this.builderTime = this.builderTime.withDayOfYear(dayOfYear);
            return this;
        }

        public Builder withHour(int hour) {
            this.builderTime = this.builderTime.withHour(hour);
            return this;
        }

        public Builder withMinute(int minute) {
            this.builderTime = this.builderTime.withMinute(minute);
            return this;
        }

        public Builder withSecond(int second) {
            this.builderTime = this.builderTime.withSecond(second);
            return this;
        }

        public Builder withNano(int nanoOfSecond) {
            this.builderTime = this.builderTime.withNano(nanoOfSecond);
            return this;
        }

        public Builder plusYears(long years) {
            this.builderTime = this.builderTime.plusYears(years);
            return this;
        }

        public Builder plusMonths(long months) {
            this.builderTime = this.builderTime.plusMonths(months);
            return this;
        }

        public Builder plusWeeks(long weeks) {
            this.builderTime = this.builderTime.plusWeeks(weeks);
            return this;
        }

        public Builder plusDays(long days) {
            this.builderTime = this.builderTime.plusDays(days);
            return this;
        }

        public Builder plusHours(long hours) {
            this.builderTime = this.builderTime.plusHours(hours);
            return this;
        }

        public Builder plusMinutes(long minutes) {
            this.builderTime = this.builderTime.plusMinutes(minutes);
            return this;
        }

        public Builder plusSeconds(long seconds) {
            this.builderTime = this.builderTime.plusSeconds(seconds);
            return this;
        }

        public Builder plusNanos(long nanos) {
            this.builderTime = this.builderTime.plusNanos(nanos);
            return this;
        }

        public Builder minusYears(long years) {
            this.builderTime = this.builderTime.minusYears(years);
            return this;
        }

        public Builder minusMonths(long months) {
            this.builderTime = this.builderTime.minusMonths(months);
            return this;
        }

        public Builder minusWeeks(long weeks) {
            this.builderTime = this.builderTime.minusWeeks(weeks);
            return this;
        }

        public Builder minusDays(long days) {
            this.builderTime = this.builderTime.minusDays(days);
            return this;
        }

        public Builder minusHours(long hours) {
            this.builderTime = this.builderTime.minusHours(hours);
            return this;
        }

        public Builder minusMinutes(long minutes) {
            this.builderTime = this.builderTime.minusMinutes(minutes);
            return this;
        }

        public Builder minusSeconds(long seconds) {
            this.builderTime = this.builderTime.minusSeconds(seconds);
            return this;
        }

        public Builder minusNanos(long nanos) {
            this.builderTime = this.builderTime.minusNanos(nanos);
            return this;
        }

        public Builder atStartOfSecond() {
            this.builderTime = this.builderTime.withNano(0);
            return this;
        }

        public Builder atStartOfMinute() {
            this.atStartOfSecond();
            this.builderTime = this.builderTime.withSecond(0);
            return this;
        }

        public Builder atStartOfHour() {
            this.atStartOfMinute();
            this.builderTime = this.builderTime.withMinute(0);
            return this;
        }

        public Builder atStartOfDay() {
            this.atStartOfHour();
            this.builderTime = this.builderTime.withHour(0);
            return this;
        }

        public Builder atStartOfWeek() {
            this.atStartOfDay();
            this.builderTime = this.builderTime.minusDays(this.builderTime.getDayOfWeek().getValue() - 1);
            return this;
        }

        public Builder atStartOfMonth() {
            this.atStartOfDay();
            this.builderTime = this.builderTime.withDayOfMonth(1);
            return this;
        }

        public Builder atStartOfYear(LocalDateTime localDateTime) {
            this.atStartOfDay();
            this.builderTime = this.builderTime.withMonth(1);
            return this;
        }

        public String format(String pattern) {
            return LocalDateTimeUtil.format(this.builderTime, pattern);
        }

        public LocalDate toLocalDate() {
            return this.builderTime.toLocalDate();
        }


        public Date toDate() {
            return Date.from(this.builderTime.atZone(ZoneId.systemDefault()).toInstant());
        }


        public long toTimestamp() {
            return this.toInstant().toEpochMilli();
        }

        public Instant toInstant() {
            return this.builderTime.atZone(ZoneId.systemDefault()).toInstant();
        }

        public LocalDateTime toLocalDateTime() {
            return this.builderTime;
        }

        public LocalDateTime build() {
            return this.builderTime;
        }
    }
}



