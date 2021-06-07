import me.ryzeon.command.CommandManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;

import javax.security.auth.login.LoginException;

/**
 * Created by Ryzeon
 * Project: JDA-CommandAPI
 * Date: 07/06/2021 @ 16:37
 * Twitter: @Ryzeon_ 😎
 * Github:  github.ryzeon.me
 */

public class Bot {

    public static void main(String[] args) {
        try {
            JDA jda = JDABuilder.createDefault("TOKEN").setStatus(OnlineStatus.ONLINE).build();
            CommandManager commandManager = new CommandManager(jda, "!");
            commandManager.registerCommands(
                    new PingCommadExample()
            );
            commandManager.init(); // Register Commands
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }
}
