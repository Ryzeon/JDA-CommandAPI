package me.harpylmao.interfaces;

import net.dv8tion.jda.api.Permission;

public @interface CommandParams {
  String name();

  String usage() default "";

  Permission[] permissions() default {};

  String[] aliases() default {};

  String category() default "General";

  long cooldown() default 0;
}
