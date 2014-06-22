package jp.co.answernet.boot.core.util;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

/**
 * Created by yasuhiro on 2014/06/12.
 */
public class TimeUtil {

  public static Timestamp now() {
    return toTimestamp(LocalDateTime.now());
  }

  public static Timestamp now( @NotNull ChronoUnit truncateTo ) {
    return toTimestamp(LocalDateTime.now().truncatedTo(truncateTo));
  }

  public static Timestamp toTimestamp( LocalDate localDate ) {
    return toTimestamp(localDate.atStartOfDay());
  }

  public static Timestamp toTimestamp( LocalDateTime localDateTime ) {
    return toTimestamp(localDateTime, ZoneId.systemDefault());
  }


  public static Timestamp toTimestamp( LocalDateTime localDateTime, ZoneId zoneId ) {
    ZonedDateTime zdt = localDateTime.atZone(ZoneId.systemDefault());
    Instant instant   = zdt.toInstant();
    return Timestamp.from(instant);
  }

  public static boolean isWeekDay( Temporal temporal ) {
    DayOfWeek dayOfWeek = DayOfWeek.from(temporal);
    return ! (dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY));
  }
}
