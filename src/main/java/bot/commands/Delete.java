package bot.commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class Delete {
    public Delete(@NotNull MessageReceivedEvent event) {
        final Member member = event.getMember();
        final MessageChannel channel = event.getChannel();

        if(!member.hasPermission(Permission.ADMINISTRATOR)){
            channel.sendMessage("니가 뭔데").queue();
            return;
        }

        for (Message message : channel.getHistory().retrievePast(100).complete()) {
            message.delete().complete();
        }

        final Message endMessage = channel.sendMessage("지웠다").complete();
        endMessage.delete().queueAfter(5, TimeUnit.SECONDS);
    }
}
