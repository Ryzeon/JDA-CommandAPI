package me.harpylmao.utils;

import java.text.DecimalFormat;
import java.time.Duration;

public class TimeUtil {

  public static String getRemainingFromMillis(long remaining) {
    Duration remainingTime = Duration.ofMillis(remaining);
    long hours = remainingTime.toHours();
    remainingTime = remainingTime.minusHours(hours);
    long minutes = remainingTime.toMinutes();
    remainingTime = remainingTime.minusMinutes(minutes);
    long seconds = remainingTime.getSeconds();

    return (
      hours +
      " hour" +
      (hours == 1 ? "" : "s") +
      " " +
      minutes +
      " minute" +
      (minutes == 1 ? "" : "s") +
      " " +
      seconds +
      " second" +
      (seconds == 1 ? "" : "s")
    );
  }

  public static String millisToSeconds(long millis) {
    return new DecimalFormat("#0.0").format(millis / 1000.0F);
  }
}
