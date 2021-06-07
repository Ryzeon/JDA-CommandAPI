import me.ryzeon.command.interfaces.BaseCommand;
import me.ryzeon.command.objects.CommandEvent;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

/**
 * Created by Ryzeon
 * Project: JDA-CommandAPI
 * Date: 06/06/2021 @ 23:12
 * Twitter: @Ryzeon_ 😎
 * Github:  github.ryzeon.me
 */

public class PingCommadExample implements BaseCommand {

    @Override
    public String usage() {
        return "A simple example command";
    }

    @Override
    public String category() {
        return "Test Category";
    }

    @Override
    public void execute(CommandEvent command, TextChannel textChannel, Member member, String[] args) {
        command.reply("Pong!");
    }
}
