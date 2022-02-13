package bot.commands;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.AudioChannel;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;
import org.jetbrains.annotations.NotNull;

public class ComeOn {
    public ComeOn(@NotNull MessageReceivedEvent event, JDA jda) {
        final Guild guild = event.getGuild();
        final Member self = guild.getMember(jda.getSelfUser());
        final Member member = event.getMember();
        final TextChannel textChannel = event.getTextChannel();

        if(self.getVoiceState().inAudioChannel()) {
            textChannel.sendMessage("ㅅ발 이미 들어가 있잖아").queue();
            return;
        }

        if(!member.getVoiceState().inAudioChannel()) {
            textChannel.sendMessage("ㅅ발 너나 들어가").queue();
            return;
        }

        final AudioChannel channel = member.getVoiceState().getChannel();
        final AudioManager audioManager = guild.getAudioManager();
        audioManager.openAudioConnection(channel);
        textChannel.sendMessage("들어갔다").queue();
    }
}
