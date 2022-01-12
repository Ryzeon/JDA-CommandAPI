import me.harpylmao.interfaces.Command;
import me.harpylmao.interfaces.CommandParams;
import me.harpylmao.objects.CommandEvent;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

/**
 * Created by Ryzeon Project: JDA-CommandAPI Date: 06/06/2021 @ 23:12 Twitter: @Ryzeon_ 😎 Github:
 * github.ryzeon.me
 */

@CommandParams(
        name = "ping",
        category = "Test category",
        usage = """
                  A simple example command
                """,
        permissions = {Permission.ADMINISTRATOR},
        cooldown = 20_000L //Time in milliseconds
)
public class PingCommadExample implements Command {

  @Override
  public void execute(CommandEvent command, TextChannel textChannel, Member member, String[] args, GuildMessageReceivedEvent event) {
    command.reply("Pong!");
  }
}
