package jp.co.answernet.boot.core.util;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.TimestampType;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;

/**
 * Created by yasuhiro on 2014/06/11.
 */
public class LocalDateTimeType implements UserType {

  @Override
  public int[] sqlTypes() {
    return new int[]{Types.TIMESTAMP, Types.TIMESTAMP_WITH_TIMEZONE};
  }

  @Override
  public Class returnedClass() {
    return LocalDateTimeType.class;
  }

  @Override
  public boolean equals( Object x, Object y ) throws HibernateException {
    if (! (x instanceof LocalDateTime) ) {
      throw new IllegalArgumentException("x not instanceof LocalDataTime.");
    }
    if (! (y instanceof LocalDateTime) ) {
      throw new IllegalArgumentException("y not instanceof LocalDataTime.");
    }
    return ((LocalDateTime)x).equals((LocalDateTime)y);
  }

  @Override
  public int hashCode( Object x ) throws HibernateException {
    if (! (x instanceof LocalDateTime) ) {
      throw new IllegalArgumentException("x not instanceof LocalDataTime.");
    }
    return ((LocalDateTime)x).hashCode();
  }

  @Override
  public Object nullSafeGet( ResultSet rs, String[] names, SessionImplementor session, Object owner ) throws HibernateException, SQLException {
    Timestamp timestamp = rs.getTimestamp(names[0]);
    timestamp.toLocalDateTime();
    return null;
  }

  @Override
  public void nullSafeSet( PreparedStatement st, Object value, int index, SessionImplementor session ) throws HibernateException, SQLException {

  }

  @Override
  public Object deepCopy( Object value ) throws HibernateException {
    return null;
  }

  @Override
  public boolean isMutable() {
    return false;
  }

  @Override
  public Serializable disassemble( Object value ) throws HibernateException {
    return null;
  }

  @Override
  public Object assemble( Serializable cached, Object owner ) throws HibernateException {
    return null;
  }

  @Override
  public Object replace( Object original, Object target, Object owner ) throws HibernateException {
    return null;
  }
}
