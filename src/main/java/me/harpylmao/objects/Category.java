package me.harpylmao.objects;

import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import me.harpylmao.CommandManager;
import me.harpylmao.interfaces.Command;
import me.harpylmao.interfaces.CommandParams;

/**
 * Created by Ryzeon Project: JDA-CommandAPI Date: 06/06/2021 @ 22:23 Twitter: @Ryzeon_ 😎 Github:
 * github.ryzeon.me
 */
@Getter
public class Category {

  private static final Set<Category> categories = new HashSet<>();

  private final String name;

  public Category(String name) {
    this.name = name;
    categories.add(this);
  }

  public Set<Command> getCommands() {
    Set<Command> commands = new HashSet<>();
    for (Command command : CommandManager.INSTANCE.getCommands()) {
      CommandParams commandParams = CommandManager.INSTANCE
        .getParamsMap()
        .get(command);
      if (!commandParams.category().equalsIgnoreCase(name)) continue;
      commands.add(command);
    }
    return commands;
  }

  public static Category getCategory(String name) {
    for (Category category : categories) {
      if (category.getName().equalsIgnoreCase(name)) return category;
    }
    return null;
  }

  public static Set<Category> getCategories() {
    for (Command command : CommandManager.INSTANCE.getCommands()) {
      CommandParams commandParams = CommandManager.INSTANCE
        .getParamsMap()
        .get(command);
      if (getCategory(commandParams.category()) != null) continue;
      new Category(commandParams.category());
    }
    return categories;
  }
}
