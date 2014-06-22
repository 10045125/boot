package jp.co.answernet.boot.core.values;

/**
 * Created by yasuhiro on 2014/05/05.
 */
public enum Title {

  PRESIDENT("取締役社長"),
  VICE_PRESIDENT("副社長"),
  GENERAL_MANAGER("部長"),
  MANAGER("課長"),
  CHIEF_CLARK("主任"),
  GROUP_LEADER("グループリーダー"),
  LEADER("リーダー"),
  SUB_LEADER("サブリーダー"),
  STAFF("社員");

  private String value;

  private Title( String value ) {
    this.value = value;
  }
}
