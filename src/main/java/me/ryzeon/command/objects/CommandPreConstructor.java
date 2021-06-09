package me.ryzeon.command.objects;

import java.util.Arrays;
import lombok.Getter;
import me.ryzeon.command.CommandManager;
import me.ryzeon.command.interfaces.BaseCommand;

/**
 * Created by Ryzeon Project: JDA-CommandAPI Date: 06/06/2021 @ 21:24 Twitter: @Ryzeon_ 😎 Github:
 * github.ryzeon.me
 */
public class CommandPreConstructor {

  @Getter private final BaseCommand baseCommand;

  @Getter private final String label;

  @Getter private final String[] args;

  public CommandPreConstructor(String rawMessage, String prefix, CommandManager commandManager) {
    String[] argsWithOutPrefix = rawMessage.replaceFirst(prefix, "").split("\\s+");
    this.label = argsWithOutPrefix[0].toLowerCase();

    this.baseCommand = commandManager.getCommandByNameOrAlias(label);
    this.args = Arrays.copyOfRange(argsWithOutPrefix, 1, argsWithOutPrefix.length);
  }
}
