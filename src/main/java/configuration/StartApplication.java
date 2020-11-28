package configuration;

import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Slf4j
public class StartApplication {

    @lombok.SneakyThrows
    public static void run() {

        final String token = getToken();

        JDA jda = JDABuilder.createDefault(token).build();
        jda.getPresence().setStatus(OnlineStatus.ONLINE);

        //TODO: jeśli będą róże sytemy to będziemy podmieniać name
        jda.getPresence().setActivity(Activity.of(Activity.ActivityType.STREAMING, "CoC "));
    }

    /**
     * Create file in resources name token.txt.txt and put inside token.txt value
     * <p>
     * eg.
     * token.txt=123tokenValue123
     *
     * @return token.txt
     */
    static String getToken() {
        Properties property = new Properties();
        try (FileReader in = new FileReader("src/main/resources/token.txt")) {
            property.load(in);
        } catch (IOException e) {
            log.info("bad path to token.txt");
            return "";
        }
        return property.getProperty("token");
    }
}
