package jp.co.answernet.boot.core.workstyle;

import jp.co.answernet.boot.core.member.Account;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

/**
 * Created by yasuhiro on 2014/06/18.
 */
@Data
@AllArgsConstructor
public class AttendanceReport {

  private Account account;

  private LocalDate begin;

  private LocalDate end;

  private List<TimeCard> attendanceList;

  public static AttendanceReport MonthlyReport( Account account, LocalDate month ) {
    int days = month.getMonth().maxLength();
    LocalDate from = month.withDayOfMonth(1);
    LocalDate to   = month.withDayOfMonth(days);
    List<TimeCard> reports = TimeCard.findByAccountAndOnWorkBetween(account, from, to);
    return new AttendanceReport(account, from, to, reports);
  }

}
