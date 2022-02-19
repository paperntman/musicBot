package bot.commands;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class Chatting {
    Logger logger = Logger.getLogger("권력");

    public Chatting(@NotNull MessageReceivedEvent event) {
        final String contentRaw = event.getMessage().getContentRaw();
        final Member member = event.getMember();
        final Guild guild = event.getGuild();

        Stream<String> badWords = Arrays.asList("씨발", "새끼", "시발", "애미", "애비", "병신", "ㅅㅂ", "ㅄ", "ㅂㅅ", "ㅅㄲ", "ㅆㅂ", "ㅗ", "썅", "년", "좆", "씨바", "지랄", "ㅈㄹ", "씹", "^^ㅣ발", "야발").stream();
        if(badWords.anyMatch(str -> {return contentRaw.replaceAll(" ", "").contains(str);})){
            event.getMessage().delete().queue();
            if(member.isOwner()){
                event.getChannel().sendMessage("욕 쓰지 마").complete().delete().queueAfter(5, TimeUnit.SECONDS);
                return;
            }
            member.timeoutFor(Duration.ofSeconds(10)).queue();
            event.getChannel().sendMessageFormat("%s 님께선 비속어를 사용한 죄로 10초동안 타임아웃 되셨습니다. (%s)", member.getUser(), contentRaw).complete().delete().queueAfter(10, TimeUnit.SECONDS);
        }

        if(contentRaw.contains("ㅋㅋㅋ")){
            event.getChannel().sendMessage("ㅋㅋㅋㅋㅋㅋㅋㅋ").queue();
            return;
        }

        if(Arrays.asList("뭐해", "뭐함", "뭐하는중").contains(contentRaw)){
            event.getChannel().sendMessage(Arrays.asList("몰라", "개발", "밥먹는중", "너 집 찾아가는중", "컴퓨터 수리중", "칼 가는중").get(new Random().nextInt(5))).queue();
            return;
        }




    }
}
