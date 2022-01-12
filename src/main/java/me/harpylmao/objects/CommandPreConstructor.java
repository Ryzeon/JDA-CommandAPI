package me.harpylmao.objects;

import java.util.Arrays;
import lombok.Getter;
import me.harpylmao.CommandManager;
import me.harpylmao.interfaces.Command;

/**
 * Created by Ryzeon Project: JDA-CommandAPI Date: 06/06/2021 @ 21:24 Twitter: @Ryzeon_ 😎 Github:
 * github.ryzeon.me
 */
public class CommandPreConstructor {

  @Getter
  private final Command command;

  @Getter
  private final String label;

  @Getter
  private final String[] args;

  public CommandPreConstructor(
    String rawMessage,
    String prefix,
    CommandManager commandManager
  ) {
    String[] argsWithOutPrefix = rawMessage
      .replaceFirst(prefix, "")
      .split("\\s+");
    this.label = argsWithOutPrefix[0].toLowerCase();

    this.command = commandManager.getCommandByNameOrAlias(label);
    this.args =
      Arrays.copyOfRange(argsWithOutPrefix, 1, argsWithOutPrefix.length);
  }
}
