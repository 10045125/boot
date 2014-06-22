package test.jp.co.answernet.boot.core.team.time;

import jp.co.answernet.boot.core.util.TimeUtil;
import org.junit.Test;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Created by yasuhiro on 2014/06/11.
 */
public class TimeUtilTest {

  @Test
  public void localDateTime_To_Timestamp() {
    LocalDateTime sut = LocalDateTime.of(2014, 03, 15, 03, 15, 30);
    ZonedDateTime zdt = sut.atZone(ZoneId.systemDefault());
    Instant instant   = zdt.toInstant();
    Timestamp actual  = Timestamp.from(instant);
    assert actual.toLocalDateTime().equals(sut);
  }


  @Test
  public void localDateTime_To_Timestamp_with_TimeUtil() {
    LocalDateTime sut = LocalDateTime.of(2014, 03, 15, 03, 15, 30);
    Timestamp actual  = TimeUtil.toTimestamp(sut);
    assert actual.toLocalDateTime().equals(sut);
  }


  @Test
  public void month_truncate() {
    LocalDate month = LocalDate.of(2014, 03, 15);
    LocalDate startOfMonth = month.withDayOfMonth(1);
    assert startOfMonth.equals(LocalDate.of(2014, 03, 01));

    LocalDate endOfMonth = month.withDayOfMonth(month.getMonth().maxLength());
    assert endOfMonth.equals(LocalDate.of(2014, 03, 31));

  }
}
