package me.ryzeon.command.objects;

import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

/**
 * Created by Ryzeon
 * Project: JDA-CommandAPI
 * Date: 06/06/2021 @ 20:44
 * Twitter: @Ryzeon_ 😎
 * Github:  github.ryzeon.me
 */

@Getter
public class CommandEvent extends GuildMessageReceivedEvent {

    private final String[] args;

    private final String label;

    public CommandEvent(JDA jda, int responseNumber, Message message, CommandPreConstructor commandPreConstructor) {
        super(jda, responseNumber, message);
        this.args = commandPreConstructor.getArgs();
        this.label = commandPreConstructor.getLabel();
    }

    public void reply(Message message) {
        this.getMessage().reply(message).queue();
    }

    public void reply(String message) {
        this.getMessage().reply(message).queue();
    }
}
