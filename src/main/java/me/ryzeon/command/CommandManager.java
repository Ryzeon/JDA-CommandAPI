package me.ryzeon.command;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import me.ryzeon.command.handler.CommandHandler;
import me.ryzeon.command.interfaces.BaseCommand;
import net.dv8tion.jda.api.JDA;

/**
 * Created by Ryzeon Project: JDA-CommandAPI Date: 06/06/2021 @ 20:44 Twitter: @Ryzeon_ 😎 Github:
 * github.ryzeon.me
 */
public class CommandManager {

  public static CommandManager INSTANCE;

  @Getter private final Logger logger = Logger.getLogger("CommandAPI");

  @Getter private final List<BaseCommand> commands = new ArrayList<>();

  @Getter private final JDA jda;

  @Getter private final String prefix;

  @NonNull @Setter @Getter private Color color = Color.ORANGE;

  @Getter @Setter private String noFoundMessage = "We couldn't find a command for $1";

  @Getter @Setter private boolean sendMessageIfCommandNoFound = true;

  @Getter @Setter private String errorMessage = "An error occurred while executing this command.";

  public CommandManager(JDA jda, String prefix) {
    this.jda = jda;
    this.prefix = prefix;
    INSTANCE = this;
  }

  public void init() {
    jda.addEventListener(new CommandHandler(this));
    logger.info("Successfully init commands!");
  }

  /**
   * @param baseCommands provided a {@link BaseCommand} to can be register on {@link
   *     CommandManager#commands}
   */
  public void registerCommands(BaseCommand... baseCommands) {
    for (BaseCommand baseCommand : baseCommands) {
      registerCommand(baseCommand);
    }
  }

  /** @param baseCommand return a {@link CommandManager#commands} */
  public void registerCommand(BaseCommand baseCommand) {
    logger.info("Register -> " + baseCommand.getClass().getSimpleName());
    commands.add(baseCommand);
  }

  /**
   * @param name assign on {@link BaseCommand}
   * @return find all on {@link CommandManager#commands}
   * @see me.ryzeon.command.objects.CommandEvent
   */
  public BaseCommand getCommandByNameOrAlias(String name) {
    for (BaseCommand baseCommand : commands) {
      if (baseCommand.getName().equalsIgnoreCase(name)) return baseCommand;
      if (!baseCommand.aliases().isEmpty()) {
        for (String alt : baseCommand.aliases()) {
          if (alt.equalsIgnoreCase(name)) return baseCommand;
        }
      }
    }
    return null;
  }
}
