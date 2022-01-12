import javax.security.auth.login.LoginException;

import me.harpylmao.CommandManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;

/**
 * Created by Ryzeon Project: JDA-CommandAPI Date: 07/06/2021 @ 16:37 Twitter: @Ryzeon_ 😎 Github:
 * github.ryzeon.me
 */
public class Bot {

  public static void main(String[] args) {
    try {
      JDA jda = JDABuilder.createDefault("TOKEN").setStatus(OnlineStatus.ONLINE).build();
      CommandManager commandManager = new CommandManager(jda, "!");
      commandManager.registerCommands(); //Imports all commands that have the Command class implemented automatically
      commandManager.init(); // Register Commands
    } catch (LoginException e) {
      e.printStackTrace();
    }
  }
}
