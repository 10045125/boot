import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.rolling.FixedWindowRollingPolicy
import ch.qos.logback.core.rolling.RollingFileAppender
import ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy

import static ch.qos.logback.classic.Level.TRACE

/**
 * Created by yasuhiro on 2014/02/22.
 */

//appender("STDOUT", ConsoleAppender) {
//    encoder(PatternLayoutEncoder) {
//        pattern = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
//    }
//}

appender("FILE", RollingFileAppender) {
    file = "/Users/yasuhiro/tmp/boot.debug.log"
    rollingPolicy(FixedWindowRollingPolicy) {
        fileNamePattern = "/Users/yasuhiro/tmp/boot.debug.log.%i.zip"
        minIndex = 0
        maxIndex = 99
    }
    triggeringPolicy(SizeBasedTriggeringPolicy) {
        maxFileSize = "10MB"
    }
    encoder(PatternLayoutEncoder) {
        pattern = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
    }
}
root(TRACE, ["FILE"])