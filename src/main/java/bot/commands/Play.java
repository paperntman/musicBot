package bot.commands;

import bot.lavaPlayer.PlayerManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.net.URI;
import java.net.URISyntaxException;

public class Play {
    public Play(MessageReceivedEvent event, JDA jda) {
        final TextChannel channel = event.getTextChannel();
        final Member self = event.getGuild().getMember(jda.getSelfUser());
        final GuildVoiceState selfVoiceState = self.getVoiceState();

        if(!selfVoiceState.inAudioChannel()){
            channel.sendMessage("ㅅ발 들어오지도 않았는데 틀라 하냐").queue();
            return;
        }

        final Member member = event.getMember();
        final GuildVoiceState memberVoiceState = member.getVoiceState();

        if(!memberVoiceState.inAudioChannel()){
            channel.sendMessage("ㅅ발 너나 들어와").queue();
            return;
        }

        if(!memberVoiceState.getChannel().equals(selfVoiceState.getChannel())){
            channel.sendMessage("ㅅ발 딴방이잖아").queue();
            return;
        }

        String url = event.getMessage().getContentRaw().split(" ")[0];
        if(!isUrl(url)) {
            url = "ytsearch: " + url;
        }

        PlayerManager.getInstance()
                .loadAndPlay(channel, url);

        event.getMessage().delete().queue();
    }

    private boolean isUrl(String url){
        try{
            new URI(url);
            return true;
        }catch (URISyntaxException e){
            return false;
        }
    }
}
