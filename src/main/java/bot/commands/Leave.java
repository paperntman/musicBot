package bot.commands;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;
import org.jetbrains.annotations.NotNull;

public class Leave {
    public Leave(@NotNull MessageReceivedEvent event, JDA jda) {
        final Guild guild = event.getGuild();
        final Member self = guild.getMember(jda.getSelfUser());
        final Member member = event.getMember();
        final TextChannel textChannel = event.getTextChannel();

        if(!self.getVoiceState().inAudioChannel()){
            textChannel.sendMessage("ㅅ발 들어가지도 않았는데 뭘 나가라 하냐").queue();
            return;
        }

        if(!member.getVoiceState().inAudioChannel()){
            textChannel.sendMessage("니가 뭔데 나가라 하냐").queue();
            return;
        }

        final AudioManager audioManager = guild.getAudioManager();
        audioManager.closeAudioConnection();
        textChannel.sendMessage("나갔다").queue();
    }
}
