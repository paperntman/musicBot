package bot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;
import java.util.UUID;

public class Bot {

    public static JDA jda;
    public static UUID randomUUID;

    private Bot() throws LoginException {
        jda = JDABuilder.createDefault(Config.get("token"))
                .setActivity(Activity.playing("개발"))
                .enableCache(CacheFlag.VOICE_STATE)
                .addEventListeners(new Listener())
                .build();
    }

    public static void main(String[] args) throws LoginException {
        new Bot();
    }


}
