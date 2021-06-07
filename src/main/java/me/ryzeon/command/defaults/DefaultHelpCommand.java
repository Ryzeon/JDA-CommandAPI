package me.ryzeon.command.defaults;

import me.ryzeon.command.CommandManager;
import me.ryzeon.command.interfaces.BaseCommand;
import me.ryzeon.command.objects.Category;
import me.ryzeon.command.objects.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Ryzeon
 * Project: JDA-CommandAPI
 * Date: 06/06/2021 @ 21:01
 * Twitter: @Ryzeon_ 😎
 * Github:  github.ryzeon.me
 */

public class DefaultHelpCommand implements BaseCommand {

    @Override
    public void execute(CommandEvent command, TextChannel textChannel, Member member, String[] args) {
        if (args.length == 0) {
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setColor(CommandManager.INSTANCE.getColor());
            List<String> description = new ArrayList<>();
            for (Category category : Category.getCategories()) {
                StringBuilder builder = new StringBuilder();
                description.add("__** " + category.getName() + "**__");
                for (BaseCommand baseCommand : category.getCommands()) {
                    builder.append("`").append(baseCommand.getName()).append("`");
                }
                description.add(builder.toString());
            }
            embedBuilder.setFooter("For detailed information on a command use " + CommandManager.INSTANCE.getPrefix() + "help [command]");
            embedBuilder.setDescription(String.join("\n", description));
            textChannel.sendMessage(embedBuilder.build()).queue();
            return;
        }
        String label = args[0];
        BaseCommand baseCommand = CommandManager.INSTANCE.getCommandByNameOrAlias(label);
        if (baseCommand != null) {
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setColor(CommandManager.INSTANCE.getColor());
            embedBuilder.setTitle("**" + label + " Info**");
            embedBuilder.addField("Usage:", baseCommand.usage(), false);
            embedBuilder.addField("Aliases: ", (baseCommand.aliases().isEmpty() ? "No available aliases" : String.join(", ", baseCommand.aliases())), false);
            command.getMessage().reply(embedBuilder.build()).queue();
        } else {
            command.reply("No command was found with " + label);
        }
    }

    @Override
    public String usage() {
        return "Get available commands";
    }

    @Override
    public List<String> aliases() {
        return Collections.singletonList("ayuda");
    }

    @Override
    public String getName() {
        return "help";
    }
}
