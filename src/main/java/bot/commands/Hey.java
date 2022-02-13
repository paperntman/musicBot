package bot.commands;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Hey {
    public Hey(MessageReceivedEvent event) {
        final TextChannel textChannel = event.getTextChannel();

        textChannel.sendMessage("뭐").queue();
    }
}
