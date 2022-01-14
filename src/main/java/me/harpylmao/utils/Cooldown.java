package me.harpylmao.utils;

import lombok.Data;
import lombok.Getter;
import me.harpylmao.interfaces.Command;
import net.dv8tion.jda.api.entities.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Data
public class Cooldown {

  @Getter
  private static Map<Map<String, Command>, Map<User, Long>> timeMap;

  private UUID uniqueId = UUID.randomUUID();

  private long start = System.currentTimeMillis();

  private long expire;

  private boolean notified;

  public Cooldown() {
    timeMap = new HashMap<>();
  }

  public Cooldown(String commandName, Command command, User user, long duration) {
    this.expire = this.start + duration;

    if (duration == 0) {
      this.notified = true;
    }

    Map<User, Long> userLongMap = new HashMap<>();
    userLongMap.put(user, expire);

    Map<String, Command> commandMap = new HashMap<>();
    commandMap.put(commandName, command);

    timeMap.put(commandMap, userLongMap);
  }

  public long getPassed(User user) {
    if (timeMap.get(user) != null) {
      for (Map.Entry<Map<String, Command>, Map<User, Long>> mapEntry : timeMap.entrySet()) {
        return System.currentTimeMillis() - mapEntry.getValue().get(user);
      }
    }
    return 0;
  }

  public long getRemaining(String commandName, Command command, User user) {
    for (Map.Entry<Map<String, Command>, Map<User, Long>> mapEntry : timeMap.entrySet()) {
      if (mapEntry.getValue().get(user) != null) {
        for (Map.Entry<String, Command> mapEntry1 : mapEntry.getKey().entrySet()) {
          if (mapEntry1.getKey().equalsIgnoreCase(commandName) && mapEntry1.getValue().equals(command)) {
            return mapEntry.getValue().get(user) - System.currentTimeMillis();
          }
        }
      }
    }
    return 0;
  }

  public boolean hasExpired(String commandName, Command command, User user) {
    for (Map.Entry<Map<String, Command>, Map<User, Long>> mapEntry : timeMap.entrySet()) {
      if (mapEntry.getValue().get(user) != null) {
        for (Map.Entry<String, Command> mapEntry1 : mapEntry.getKey().entrySet()) {
          if (mapEntry1.getKey().equalsIgnoreCase(commandName) && mapEntry1.getValue().equals(command)) {
            return System.currentTimeMillis() - mapEntry.getValue().get(user) >= 0;
          }
        }
      }
    }
    return true;
  }

  public String getTimeLeft(String commandName, Command command, User user) {
    if (this.getRemaining(commandName, command, user) >= 60_000) {
      return TimeUtil.getRemainingFromMillis(this.getRemaining(commandName, command, user));
    } else {
      return TimeUtil.millisToSeconds(this.getRemaining(commandName, command, user));
    }
  }
}
