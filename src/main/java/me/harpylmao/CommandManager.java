package me.harpylmao;

import me.harpylmao.handler.CommandHandler;
import me.harpylmao.interfaces.Command;
import lombok.Getter;
import lombok.Setter;
import me.harpylmao.interfaces.CommandParams;
import net.dv8tion.jda.api.JDA;
import org.reflections8.Reflections;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Ryzeon Project: JDA-CommandAPI Date: 06/06/2021 @ 22:23 Twitter: @Ryzeon_ 😎 Github:
 * github.ryzeon.me
 */

@Getter
@Setter
public class CommandManager {

  public static CommandManager INSTANCE;
  private final Logger logger = Logger.getLogger("CommandAPI");
  private final List<Command> commands = new ArrayList<>();
  private final HashMap<Command, CommandParams> paramsMap = new HashMap<>();
  private final JDA jda;
  private final String prefix;
  private Color color = Color.GREEN;
  private String noFoundMessage = "No hemos encontrado ningun comando para $1";
  private boolean sendMessageIfCommandNoFound = true;
  private String errorMessage =
    "Hemos encontrado un error mientras ejecutabamos el comando";

  public CommandManager(JDA jda, String prefix) {
    this.jda = jda;
    this.prefix = prefix;
    INSTANCE = this;
  }

  public void init() {
    jda.addEventListener(new CommandHandler(this));
    logger.info("Successfully init commands!");
  }

  public void registerCommands() {
    Reflections reflections = new Reflections();
    reflections
      .getSubTypesOf(Command.class)
      .forEach(clazz -> {
        try {
          Command command = clazz.newInstance();
          if (clazz.getAnnotation(CommandParams.class) == null) {
            throw new Exception("No params found on " + clazz.getName());
          } else {
            CommandParams commandParams = clazz.getAnnotation(
              CommandParams.class
            );
            commands.add(command);
            paramsMap.put(command, commandParams);
            System.out.println(
              "[JDA-CommandAPI] -> Registered " +
              command.getClass().getSimpleName() +
              " command."
            );
          }
        } catch (Exception e) {
          System.out.println("CommandException -> " + e.getMessage());
        }
      });
    System.out.println(
      "[JDA-CommandAPI] -> " +
      commands.size() +
      " of commands registereds successfully."
    );
  }

  public Command getCommandByNameOrAlias(String name) {
    for (Command command : commands) {
      CommandParams commandParams = paramsMap.get(command);
      if (commandParams.name() == null) {
        return null;
      }
      if (commandParams.name().equalsIgnoreCase(name)) return command;
      if (!Arrays.stream(commandParams.aliases()).toList().isEmpty()) {
        for (String alt : commandParams.aliases()) {
          if (alt.equalsIgnoreCase(name)) return command;
        }
      }
    }
    return null;
  }
}
