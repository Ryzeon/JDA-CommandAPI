package me.ryzeon.command.objects;

import lombok.Getter;
import me.ryzeon.command.CommandManager;
import me.ryzeon.command.interfaces.BaseCommand;

import java.util.HashSet;
import java.util.Set;

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

    public Set<BaseCommand> getCommands() {
        Set<BaseCommand> commands = new HashSet<>();
        for (BaseCommand baseCommand : CommandManager.INSTANCE.getCommands()) {
            if (!baseCommand.category().equalsIgnoreCase(name)) continue;
            commands.add(baseCommand);
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
        for (BaseCommand baseCommand : CommandManager.INSTANCE.getCommands()) {
            if (getCategory(baseCommand.category()) != null) continue;
            new Category(baseCommand.category());
        }
        return categories;
    }
}
