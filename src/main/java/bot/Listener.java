package bot;

import bot.commands.*;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import static bot.Bot.jda;

public class Listener extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if(!event.isFromGuild()) return;
        if(event.getAuthor().equals(jda.getSelfUser())) return;

        final Message message = event.getMessage();
        final String contentRaw = message.getContentRaw();

        if(contentRaw.endsWith("틀어봐")) {
            if(contentRaw.split(" ").length == 1){
                event.getChannel().sendMessage("ㅅ발 뭘 틀어").queue();
                return;
            }
            new Play(event,jda);
        } else switch(contentRaw){
            case "나가":
            case "나가봐":
                new Leave(event, jda); break;
            case "들어와": new ComeOn(event, jda); break;
            case "야": new Hey(event); break;
            case "지워": new Delete(event); break;
            default: new Chatting(event); break;
        }


    }
}
