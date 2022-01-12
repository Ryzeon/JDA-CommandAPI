package me.harpylmao.handler;

import java.util.Arrays;
import java.util.List;
import me.harpylmao.CommandManager;
import me.harpylmao.interfaces.CommandParams;
import me.harpylmao.objects.CommandEvent;
import me.harpylmao.objects.CommandPreConstructor;
import me.harpylmao.utils.Cooldown;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class CommandHandler extends ListenerAdapter {

  private final CommandManager commandManager;
  private final Cooldown cooldown = new Cooldown();

  public CommandHandler(CommandManager commandManager) {
    this.commandManager = commandManager;
  }

  @Override
  public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
    if (event.getAuthor().isBot()) return;
    if (
      !event.getMessage().getContentRaw().startsWith(commandManager.getPrefix())
    ) return;
    if (event.getMessage().getContentRaw().equalsIgnoreCase("!")) return;
    CommandPreConstructor commandPreConstructor = new CommandPreConstructor(
      event.getMessage().getContentRaw(),
      commandManager.getPrefix(),
      commandManager
    );
    if (commandPreConstructor.getCommand() != null) {
      CommandParams commandParams = CommandManager.INSTANCE
        .getParamsMap()
        .get(commandPreConstructor.getCommand());
      try {
        if (!cooldown.hasExpired(event.getMember().getUser())) {
          event
            .getMessage()
            .reply(
              "You have a cooldown of " +
              cooldown.getTimeLeft(event.getMember().getUser())
            )
            .queue();
          return;
        }

        CommandEvent commandEvent = new CommandEvent(
          event.getJDA(),
          (int) event.getResponseNumber(),
          event.getMessage(),
          commandPreConstructor,
          0
        );

        if (commandParams.permissions().length > 0) {
          List<Permission> permissions = Arrays.asList(
            commandParams.permissions()
          );
          if (event.getMember().getPermissions().containsAll(permissions)) {
            commandPreConstructor
              .getCommand()
              .execute(
                commandEvent,
                event.getChannel(),
                event.getMember(),
                commandPreConstructor.getArgs(),
                event
              );
            new Cooldown(event.getMember().getUser(), commandParams.cooldown());
          } else {
            event
              .getMessage()
              .reply("You don't have the permissions to execute this command")
              .queue();
          }
        } else {
          commandPreConstructor
            .getCommand()
            .execute(
              commandEvent,
              event.getChannel(),
              event.getMember(),
              commandPreConstructor.getArgs(),
              event
            );
          new Cooldown(event.getMember().getUser(), commandParams.cooldown());
        }
      } catch (Exception exception) {
        commandManager
          .getLogger()
          .warning(
            "An error occurred while executing " +
            commandPreConstructor.getLabel()
          );
        event.getMessage().reply(commandManager.getErrorMessage()).queue();
        exception.printStackTrace();
      }
    } else {
      if (commandManager.isSendMessageIfCommandNoFound()) event
        .getMessage()
        .reply(
          commandManager
            .getNoFoundMessage()
            .replace("$1", commandPreConstructor.getLabel())
        )
        .queue();
    }
  }
}
