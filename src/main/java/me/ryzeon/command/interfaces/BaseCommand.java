package me.ryzeon.command.interfaces;

import me.ryzeon.command.objects.CommandEvent;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.Collections;
import java.util.List;

/**
 * Created by Ryzeon
 * Project: JDA-CommandAPI
 * Date: 06/06/2021 @ 20:44
 * Twitter: @Ryzeon_ 😎
 * Github:  github.ryzeon.me
 */

public interface BaseCommand {

    void execute(CommandEvent command, TextChannel textChannel, Member member, String[] args);

    default String usage() {
        return "Put a usage with ```java@Override usage() { return : 'YOU USAGE'} ```";
    }

    default List<String> aliases() {
        return Collections.emptyList();
    }

    default String getName() {
        return this.getClass().getSimpleName();
    }

    default String category() {
        return "General";
    }
}
