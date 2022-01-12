package me.harpylmao.defaults;

import lombok.Getter;
import me.harpylmao.CommandManager;
import me.harpylmao.interfaces.Command;
import me.harpylmao.interfaces.CommandParams;
import me.harpylmao.objects.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.SelectionMenuEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.selections.SelectionMenu;
import net.dv8tion.jda.api.requests.restaction.MessageAction;

import java.awt.*;
import java.util.Locale;
import java.util.stream.Collectors;

@CommandParams(name = "help", usage = "usage")
@Getter
public class DefaultHelpCommand implements Command {

    @Override
    public void execute(
            CommandEvent command,
            TextChannel textChannel,
            Member member,
            String[] args,
            GuildMessageReceivedEvent event
    ) {
        EmbedBuilder embedBuilder = new EmbedBuilder()
                .setColor(Color.YELLOW)
                .setTitle("Panoramic Help Menus")
                .setDescription(
                        """
                                        **Main help:** If you want know all main commands
                                        please select _Main help_ in the options.
                                """)
                .setFooter(
                        "Information requested by " + member.getUser().getName(),
                        member.getUser().getEffectiveAvatarUrl()
                );
        textChannel
                .sendMessageEmbeds(embedBuilder.build())
                .setActionRow(getSelectionMenu())
                .queue();
    }

    private static SelectionMenu getSelectionMenu() {
        return SelectionMenu
                .create("menu:help")
                .setPlaceholder("Choose what help you need")
                .setRequiredRange(1, 1)
                .addOption("Main help", "main", "Shows you all main help of the bot.")
                .build();
    }

    public static class HelpCommandListeners extends ListenerAdapter {

        @Override
        public void onSelectionMenu(SelectionMenuEvent event) {
            if (event.getSelectionMenu().getId().equalsIgnoreCase("menu:help")) {
                event.getInteraction().deferEdit().complete();
                switch (event.getValues().get(0).toLowerCase(Locale.ROOT)) {
                    case "main" -> {
                        if (this.mainCommands().isEmpty()) return;
                        MessageAction mainMessage = event
                                .getTextChannel()
                                .sendMessageEmbeds(
                                        new EmbedBuilder()
                                                .setColor(Color.YELLOW)
                                                .setDescription(this.mainCommands())
                                                .build()
                                );

                        mainMessage.setActionRow(getSelectionMenu()).queue();
                    }
                }
            }
        }

        private String mainCommands() {
            return CommandManager.INSTANCE
                    .getCommands()
                    .stream()
                    .filter(baseCommand ->
                            CommandManager.INSTANCE
                                    .getParamsMap()
                                    .get(baseCommand)
                                    .category()
                                    .equalsIgnoreCase("main")
                    )
                    .map(s ->
                            " ➥ `" + CommandManager.INSTANCE.getParamsMap().get(s).name() + "`"
                    )
                    .collect(Collectors.joining("\n"));
        }
    }
}
