package bot.commands;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.utils.concurrent.Task;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.stream.Stream;

import static bot.Bot.randomUUID;

public class Chatting {
    Logger logger = Logger.getLogger("권력");

    public Chatting(@NotNull MessageReceivedEvent event) {
        final String contentRaw = event.getMessage().getContentRaw();
        final Member member = event.getMember();
        final Guild guild = event.getGuild();

        Stream<String> badWords = Arrays.asList("씨발", "새끼", "시발", "애미", "애비", "병신", "ㅅㅂ", "ㅄ", "ㅂㅅ", "ㅅㄲ", "ㅆㅂ").stream();
        if(badWords.anyMatch(str -> {return contentRaw.contains(str);})){
            event.getMessage().delete().queue();
            if(member.isOwner()){
                event.getChannel().sendMessage("욕 쓰지 마").queue();
                return;
            }
            member.timeoutFor(Duration.ofSeconds(10)).queue();
            event.getChannel().sendMessageFormat("%s 님께선 비속어를 사용한 죄로 10초동안 타임아웃 되셨습니다. (%s)", member.getUser(), contentRaw).complete().delete().queueAfter(10, TimeUnit.SECONDS);
        }

        if(contentRaw.contains("ㅋㅋㅋ")){
            event.getChannel().sendMessage("ㅋㅋㅋㅋㅋㅋㅋㅋ").queue();
            return;
        }

        if(contentRaw.equalsIgnoreCase("권력내놔")){
            randomUUID = UUID.randomUUID();
            logger.info(randomUUID.toString());
            return;
        }

        if(contentRaw.equalsIgnoreCase(randomUUID.toString())){
            final Task<List<Member>> membersWithRoles = guild.findMembersWithRoles();
        }


    }
}
