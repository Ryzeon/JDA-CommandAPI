package me.harpylmao.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.Data;
import lombok.Getter;
import net.dv8tion.jda.api.entities.User;

@Data
public class Cooldown {

  @Getter
  private static Map<User, Long> timeMap;

  private UUID uniqueId = UUID.randomUUID();

  private long start = System.currentTimeMillis();

  private long expire;

  private boolean notified;

  public Cooldown() {
    timeMap = new HashMap<>();
  }

  public Cooldown(User user, long duration) {
    this.expire = this.start + duration;

    if (duration == 0) {
      this.notified = true;
    }

    timeMap.put(user, expire);
  }

  public long getPassed(User user) {
    if (timeMap.get(user) != null) {
      return System.currentTimeMillis() - timeMap.get(user);
    }
    return 0;
  }

  public long getRemaining(User user) {
    if (timeMap.get(user) != null) {
      return timeMap.get(user) - System.currentTimeMillis();
    }
    return 0;
  }

  public boolean hasExpired(User user) {
    if (timeMap.get(user) != null) {
      return System.currentTimeMillis() - timeMap.get(user) >= 0;
    }
    return true;
  }

  public String getTimeLeft(User user) {
    if (this.getRemaining(user) >= 60_000) {
      return TimeUtil.getRemainingFromMillis(this.getRemaining(user));
    } else {
      return TimeUtil.millisToSeconds(this.getRemaining(user));
    }
  }
}
